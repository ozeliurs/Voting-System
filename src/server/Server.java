package server;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException, FileNotFoundException {
        // Set Se
        //System.setProperty("java.rmi.server.hostname", "IPADDRESS");


        // Create Registry
        Registry registry = LocateRegistry.createRegistry(2001);

        Scanner scanner = new Scanner(System.in);

        // Create and Bind Objects
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl(10001);
        Naming.rebind("rmi://localhost:2001/userRepositoryImpl", userRepositoryImpl);

        userRepositoryImpl.importUsers("users.txt");

        // Start a Vote
        VoteImpl vote = new VoteImpl();

        System.out.println("Loading candidates from file...");
        vote.importCandidates("candidates.txt");

        System.out.print("Enter the start date (dd/mm/yyyy): ");
        vote.setStart(scanner.nextLine());

        System.out.print("Enter the end date (dd/mm/yyyy): ");
        vote.setEnd(scanner.nextLine());
    }
}
