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

            // We show the user it's OTP because Vella said it 👿
            System.out.println("Your OTP is: " + otp);

            System.out.println("=== Logged in ===");
        } catch (BadCredentialsException e) {
            System.out.println("=== Bad credentials ===");
            return;
        } catch (UserNotFoundException e) {
            System.out.println("=== User not found ===");
            return;
        }

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Vote");
            System.out.println("2. Results");
            System.out.println("3. Exit");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    vote(vote, studentId, otp);
                    break;
                case 2:
                    results(vote);
                    break;
                case 3:
                default:
                    return;
            }
        }
    }

    public static void vote(Vote vote, String studentId, int otp) throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        BallotImpl ballot = new BallotImpl();
        ballot.addCandidates(vote.getCandidates());

        // We ask th OTP because it is asked not because we need it 👿
        System.out.println("Enter OTP: ");
        Integer n = scanner.nextInt();

        System.out.println("You're about to vote for the following candidates:");
        ballot.forEach((candidate, integer) -> System.out.println(candidate));

        System.out.println("For each candidate, enter the number of votes you want to give them from 0 to " + ballot.size() + " (inclusive)");
        ballot.forEach((candidate, integer) -> {
            System.out.print(candidate + ": ");
            int votes = scanner.nextInt();
            ballot.put(candidate, votes);
        });

        vote.vote(ballot, studentId, otp);
    }

    public static void results(Vote vote) throws RemoteException {
        vote.getResults().forEach((candidate, integer) -> System.out.println(candidate + ": " + integer));
    }
}