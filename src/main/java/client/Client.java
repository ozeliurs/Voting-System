package client;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import shared.Vote;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client implements Serializable {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        // Load remote objects
        Vote vote = (Vote) Naming.lookup("rmi://localhost:2001/vote");

        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.println("=== Login ===");

        System.out.print("login: ");
        String login = scanner.nextLine();

        System.out.print("password: ");
        String password = scanner.nextLine();
        String hashedPassword = DigestUtils.sha256Hex(password);

        int pin;

        try {
            pin = vote.checkCredentials(login, hashedPassword);
            System.out.println("=== Logged in ===");
        } catch (BadCredentialsException e) {
            System.out.println("=== Bad credentials ===");
            return;
        } catch (UserNotFoundException e) {
            System.out.println("=== User not found ===");
            return;
        }

        // Vote
        System.out.println("=== Vote ===");
        System.out.print("Your PIN: ");
        System.out.println(pin);
        System.out.println("TODO");
    }
}