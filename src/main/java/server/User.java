package server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

public class User {

    private String studentId;

    private String passwordHash;

    private int pin;

    public User(String studentId, String passwordHash) {
        this.studentId = studentId;
        this.passwordHash = passwordHash;
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean checkPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return this.passwordHash.equals(new String(md.digest(password.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    public int generatePin() {
        pin =  new SecureRandom().nextInt(10000);
        return pin;
    }

    public boolean checkPin(int pin) {
        return this.pin == pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(studentId, user.studentId) && Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, passwordHash);
    }
}
