package server;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import shared.Candidate;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class UserRepository extends UnicastRemoteObject implements shared.UserRepository {
    private transient List<User> users;

    protected UserRepository(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void vote(Map<Candidate, Integer> candidates) throws HasAlreadyVotedException, RemoteException {

    }

    @Override
    public void checkCredentials(String studentId, String passwordHash) throws BadCredentialsException, RemoteException {
    }

    public void importCandidates(String filename) {

    }
}
