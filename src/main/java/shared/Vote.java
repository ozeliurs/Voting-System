package shared;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface Vote extends Remote {
    int checkCredentials(String username, String passwordHash) throws UserNotFoundException, BadCredentialsException, RemoteException;
    List<Candidate> getCandidates() throws RemoteException;
}