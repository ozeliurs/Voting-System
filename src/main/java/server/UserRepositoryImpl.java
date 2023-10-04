package server;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import shared.Candidate;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl extends UnicastRemoteObject implements shared.UserRepository {
    private transient List<User> users;

    protected UserRepositoryImpl(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void vote(Map<Candidate, Integer> candidates) throws HasAlreadyVotedException, RemoteException {

    }

    @Override
    public void checkCredentials(String studentId, String passwordHash) throws BadCredentialsException, RemoteException {
    }

    public void importUsers(String file) {

    }
}
