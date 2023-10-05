package server;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        // Set Se
        //System.setProperty("java.rmi.server.hostname", "IPADDRESS");


        // Create Registry
        Registry registry = LocateRegistry.createRegistry(2001);

        Scanner scanner = new Scanner(System.in);

        // Start a Vote
        VoteImpl voteImpl = new VoteImpl(10001);
        Naming.rebind("rmi://localhost:2001/vote", voteImpl);

        System.out.print("Enter the candidates file: ");
        voteImpl.importCandidates(scanner.nextLine());

        System.out.print("Enter the users file: ");
        voteImpl.importUsers(scanner.nextLine());

        System.out.print("Enter the start date (dd/mm/yyyy): ");
        voteImpl.setStart(scanner.nextLine());

        System.out.print("Enter the end date (dd/mm/yyyy): ");
        voteImpl.setEnd(scanner.nextLine());

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. List candidates");
            System.out.println("2. Add candidate");
            System.out.println("3. List users");
            System.out.println("4. Add user");
            System.out.println("5. Exit");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    voteImpl.getCandidates().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Enter the candidate name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter the candidate surname: ");
                    String surname = scanner.nextLine();

                    System.out.print("Enter the candidate pitch: ");
                    String pitch = scanner.nextLine();

                    voteImpl.candidateRepo.candidates.add(new CandidateImpl(voteImpl.candidateRepo.candidates.size() + 1, name, surname, pitch));
                    voteImpl.candidateRepo.save();
                    break;
                case 3:
                    voteImpl.userRepo.users.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter the user name: ");
                    String userName = scanner.nextLine();

                    System.out.print("Enter the user password: ");
                    String userPassword = scanner.nextLine();
                    String hashedPassword = DigestUtils.sha256Hex(userPassword);

                    voteImpl.userRepo.users.add(new User(userName, hashedPassword));
                    voteImpl.userRepo.save();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid action");
            }
        }
    }
}
