package server;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;
import shared.Vote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VoteImpl extends UnicastRemoteObject implements Vote {

    private Date start;
    private Date end;
    public transient CandidateRepository candidateRepo;
    public transient UserRepository userRepo;

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
