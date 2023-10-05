package server;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import exceptions.UserNotFoundException;
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
    public int checkCredentials(String studentId, String passwordHash) throws UserNotFoundException, BadCredentialsException, RemoteException {
        System.out.println("checkCredentials");
        System.out.println("studentId: " + studentId);
        System.out.println("passwordHash: " + passwordHash);

        User user = users.stream().filter(u -> u.getStudentId().equals(studentId)).findFirst().orElseThrow(UserNotFoundException::new);

        if (user.checkPasswordHash(passwordHash)) {
            return user.generatePin();
        } else {
            throw new BadCredentialsException();
        }
    }

    public void importUsers(String file) {

    }
}
