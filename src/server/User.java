package server;

public class User {

    private String studentId;

    private String passwordHash;

    public User(String studentId, String passwordHash) {
        this.studentId = studentId;
        this.passwordHash = passwordHash;
    }
}
