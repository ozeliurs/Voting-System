package server;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users;

    public UserRepository(List<User> users) {
        super();
        this.users = users;
    }

    public int checkCredentials(String studentId, String passwordHash) throws UserNotFoundException, BadCredentialsException {
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

    public static UserRepository fromFile(String file) throws FileNotFoundException {
        List<User> users = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        br.lines().forEach(line -> {
            String[] parts = line.split(",");
            users.add(new User(parts[0], parts[1]));
        });

        return new UserRepository(users);
    }
}
