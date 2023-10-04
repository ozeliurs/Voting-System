package shared;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface UserRepository extends Remote {
    /**
     * @param candidates
     */
    public void vote(Map<Candidate, Integer> candidates) throws HasAlreadyVotedException, RemoteException;

    /**
     * @param studentId
     * @param passwordHash
     */
    public void checkCredentials(String studentId, String passwordHash) throws BadCredentialsException, RemoteException;



}
