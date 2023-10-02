package server;

import shared.Candidate;

import java.util.Map;

public class User implements shared.User{

    private String studentId;

    private String passwordHash;

    public User(String studentId, String passwordHash) {
        this.studentId = studentId;
        this.passwordHash = passwordHash;
    }

    @Override
    public void vote(Map<Candidate, Integer> candidates) {

    }

    @Override
    public void checkCredentials(String studentId, String passwordHash) {

    }
}
