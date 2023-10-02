package server;

import shared.Candidate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        // Set Se
        //System.setProperty("java.rmi.server.hostname", "IPADDRESS");


        // Create Registry
        Registry registry = LocateRegistry.createRegistry(2001);

        // Create and Bind Objects
        UserRepository userRepository = new UserRepository(10001);
        Naming.rebind("rmi://localhost:2001/userRepository", userRepository);
    }
}
