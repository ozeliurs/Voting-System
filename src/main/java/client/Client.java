package client;

import exceptions.BadCredentialsException;
import exceptions.UserNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import shared.AuthentificationStub;
import shared.Vote;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client implements Serializable {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, UserNotFoundException, BadCredentialsException {
        // Load remote objects
        Vote vote = (Vote) Naming.lookup("rmi://localhost:2001/vote");

        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.println("=== Login ===");

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        AuthentificationStub authentificationStub = new AuthentificationStubImpl(studentId);

        int otp = vote.authenticate(authentificationStub);

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
                    System.exit(0);
                    return;
            }
        }
    }

    public static void vote(Vote vote, String studentId, int otp) throws RemoteException {
        Scanner scanner = new Scanner(System.in);

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
    }

    public static void results(Vote vote) throws RemoteException {
        vote.getResults().forEach((candidate, integer) -> System.out.println(candidate + ": " + integer));
    }
}