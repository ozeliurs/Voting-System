package shared;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import exceptions.UserNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface UserRepository extends Remote {
    public void vote(Map<Candidate, Integer> candidates) throws HasAlreadyVotedException, RemoteException;

    public int checkCredentials(String studentId, String passwordHash) throws UserNotFoundException, BadCredentialsException, RemoteException;
}
