package client;

import exceptions.BadCredentialsException;
import shared.UserRepository;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client implements Serializable {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        // Load remote objects
        UserRepository userRepository = (UserRepository) Naming.lookup("rmi://localhost:2001/userRepository");

        // Login
        System.out.println("=== Login ===");

        System.out.print("login: ");
        String login = System.console().readLine();

        System.out.print("password: ");
        String password = System.console().readLine();

        // Hash password
        //SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
        //password = encoder.encode(password);

        try {
            userRepository.checkCredentials(login, password);
            System.out.println("=== Logged in ===");
        } catch (BadCredentialsException e) {
            System.out.println("=== Bad credentials ===");
            return;
        }

        // Vote
        System.out.println("=== Vote ===");
        System.out.println("TODO");
    }
}