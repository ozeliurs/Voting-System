package server;

import java.security.SecureRandom;
import java.util.Objects;

public class User {

    private final String studentId;

    private final String passwordHash;

    private int otp;

    public User(String studentId, String passwordHash) {
        this.studentId = studentId;
        this.passwordHash = passwordHash;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean checkPasswordHash(String passwordHash) {
        return this.passwordHash.equals(passwordHash);
    }

    public int generateOTP() {
        otp =  new SecureRandom().nextInt(10000);
        return otp;
    }

    public boolean checkOTP(int otp) {
        return this.otp == otp;
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
