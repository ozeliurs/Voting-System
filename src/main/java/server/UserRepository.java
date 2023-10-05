package server;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<User> users;
    private String filepath;

    public UserRepository(String filepath) throws FileNotFoundException {
        this.filepath = filepath;
        users = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filepath));

        br.lines().forEach(line -> {
            String[] parts = line.split(",");
            users.add(new User(parts[0], parts[1]));
        });
    }

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
            return user.generateOTP();
        } else {
            throw new BadCredentialsException();
        }
    }

    public void save() throws IOException {
        // Write to a csv file
        StringBuilder csv = new StringBuilder();
        users.forEach(user -> {
            csv.append(user.getStudentId()).append(",");
            csv.append(user.getPasswordHash()).append("\n");
        });

        // Write to file
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(csv.toString());
        writer.close();
    }

    public User findUser(String studentId) {
        return users.stream().filter(u -> u.getStudentId().equals(studentId)).findFirst().orElse(null);
    }
}
