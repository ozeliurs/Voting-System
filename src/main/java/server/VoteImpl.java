package server;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;
import shared.Ballot;
import shared.Candidate;
import shared.Vote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class VoteImpl extends UnicastRemoteObject implements Vote {

    private Date start;
    private Date end;
    public transient CandidateRepository candidateRepo;
    public transient UserRepository userRepo;

    private Map<User, Ballot> votes = new HashMap<>();

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
    public void vote(Ballot ballot, String studentId, int otp) throws RemoteException {
        User user = userRepo.findUser(studentId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!user.checkOTP(otp)) {
            throw new IllegalArgumentException("OTP is incorrect");
        }

        if (votes.containsKey(user)) {
            throw new IllegalArgumentException("User has already voted");
        }

        votes.put(user, ballot);
    }

    @Override
    public Map<Candidate, Integer> getResults() throws RemoteException {
        Map<Candidate, Integer> results = new HashMap<>();

        for (Ballot ballot : votes.values()) {
            for (Map.Entry<Candidate, Integer> entry : ballot.entrySet()) {
                Candidate candidate = entry.getKey();

                if (results.containsKey(candidate)) {
                    results.put(candidate, results.get(candidate) + entry.getValue());
                } else {
                    results.put(candidate, entry.getValue());
                }
            }
        }

        return results;
    }

    // ===== Getters and setters =====

    public void importCandidates(String filepath) throws IOException {
        candidateRepo = new CandidateRepository(filepath);
    }

    public void importUsers(String filepath) throws FileNotFoundException {
        userRepo = new UserRepository(filepath);
    }

    public Date getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = parseDate(start);
        if (this.end != null && this.start.after(this.end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = parseDate(end);
        if (this.start != null && this.start.after(this.end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
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
