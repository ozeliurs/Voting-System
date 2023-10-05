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

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("password: ");
        String password = scanner.nextLine();
        String hashedPassword = DigestUtils.sha256Hex(password);

        int otp;

        try {
            otp = vote.checkCredentials(studentId, hashedPassword);
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
        BallotImpl ballot = new BallotImpl();
        ballot.addCandidates(vote.getCandidates());

        System.out.println("You're about to vote for the following candidates:");
        ballot.forEach((candidate, integer) -> System.out.println(candidate));

        System.out.println("For each candidate, enter the number of votes you want to give them from 0 to " + ballot.size() + " (inclusive)");
        ballot.forEach((candidate, integer) -> {
            System.out.print(candidate + ": ");
            int votes = scanner.nextInt();
            ballot.put(candidate, votes);
        });

        vote.vote(ballot, studentId, otp);

        System.out.println("=== Voted ===");

    }
}