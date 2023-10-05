package shared;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface Vote extends Remote {
    int checkCredentials(String username, String passwordHash) throws UserNotFoundException, BadCredentialsException, RemoteException;
    List<Candidate> getCandidates() throws RemoteException;

    int authenticate(AuthentificationStub authentificationStub) throws RemoteException, UserNotFoundException, BadCredentialsException;

    void vote(Ballot ballot, String studentId, int otp) throws RemoteException;

    Map<Candidate, Integer> getResults() throws RemoteException;
}