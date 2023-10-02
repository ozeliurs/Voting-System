package client;

import server.Distant;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client implements Serializable {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Distant distant = (Distant) Naming.lookup("rmi://localhost:2001/objDist");
        System.out.println("Avant echo\n");
        distant.echo();
        System.out.println("Apres echo\n");
    }
}