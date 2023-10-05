package server;

import exceptions.BadCredentialsException;
import exceptions.TimeExeededException;
import exceptions.UserNotFoundException;
import org.apache.commons.lang3.tuple.Pair;
import shared.AuthentificationStub;
import shared.Ballot;
import shared.Candidate;
import shared.Vote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Logger;

public class VoteImpl extends UnicastRemoteObject implements Vote {

    private Date start;
    private Date end;
    public transient CandidateRepository candidateRepo;
    public transient UserRepository userRepo;

    private Logger logger = Logger.getLogger(VoteImpl.class.getName());

    private final Map<User, Pair<Date, Ballot>> votes = new HashMap<>();

    protected VoteImpl(int port) throws RemoteException {
        super(port);
    }


    // ===== RMI methods =====
    @Override
    public int checkCredentials(String username, String passwordHash) throws UserNotFoundException, BadCredentialsException, RemoteException {
        return userRepo.checkCredentials(username, passwordHash);
    }

    @Override
    public List<shared.Candidate> getCandidates() throws RemoteException {
        return candidateRepo.getCandidates();
    }

    @Override
    public int authenticate(AuthentificationStub authentificationStub) throws RemoteException, UserNotFoundException, BadCredentialsException {
        System.out.println("Getting User");
        String studentId = authentificationStub.getStudentId();
        System.out.println("Getting Password");
        String passwordHash = authentificationStub.getPasswordHash();

        return userRepo.checkCredentials(studentId, passwordHash);
    }

    @Override
    public void vote(Ballot ballot, String studentId, int otp) throws RemoteException, BadCredentialsException, TimeExeededException {
        if (votingForbidden()) {
            logger.warning("Voting period is over");
            throw new TimeExeededException();
        }

        User user = userRepo.findUser(studentId);

        if (user == null) {
            logger.warning("User not found");
            throw new BadCredentialsException();
        }

        if (!user.checkOTP(otp)) {
            logger.warning("OTP is incorrect");
            throw new BadCredentialsException();
        }

        if (votes.containsKey(user)) {
            logger.warning("User already voted");
        }

        logger.info("User " + user.getStudentId() + " voted");
        Pair<Date, Ballot> p = Pair.of(new Date(), ballot);
        votes.put(user, p);

        if (votes.size() == userRepo.users.size()) {
            logger.info("All users have voted");
            printResults();
        }
    }

    @Override
    public Map<Candidate, Integer> getResults() throws RemoteException {
        Map<Candidate, Integer> results = new HashMap<>();

        for (Pair<Date, Ballot> ballot : votes.values()) {
            for (Map.Entry<Candidate, Integer> entry : ballot.getRight().entrySet()) {
                Candidate candidate = entry.getKey();

                if (results.containsKey(candidate)) {
                    results.put(candidate, results.get(candidate) + entry.getValue());
                } else {
                    results.put(candidate, entry.getValue());
                }
            }
        }

        logger.info("Results: " + results);
        return results;
    }

    // ===== Getters and setters =====

    private void printResults() throws RemoteException {
        Map<Candidate, Integer> results = getResults();
        System.out.println("Results:");
        results.forEach((candidate, integer) -> System.out.println(candidate + ": " + integer));
    }

    public void importCandidates(String filepath) throws IOException {
        candidateRepo = new CandidateRepository(filepath);
    }

    public void importUsers(String filepath) throws FileNotFoundException {
        userRepo = new UserRepository(filepath);
    }

    public void setStart(String start) {
        this.start = parseDate(start);
        if (this.end != null && this.start.after(this.end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    public void setEnd(String end) {
        this.end = parseDate(end);
        if (this.start != null && this.start.after(this.end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        if (votingForbidden()) {
            throw new IllegalArgumentException("End date must be in the future");
        }
    }

    public boolean votingForbidden() {
        Date now = new Date();
        return !now.after(start) || !now.before(end);
    }

    private static Date parseDate(String date) {
        String[] dateParts = date.split("/");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(dateParts[1]));
        cal.set(Calendar.YEAR, Integer.parseInt(dateParts[2]));
        return cal.getTime();
    }
}
