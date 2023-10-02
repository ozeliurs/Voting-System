package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        //System.setProperty("java.rmi.server.hostname", "IPADDRESS");
        System.out.println("Debut main\n");
        ObjectDistant objDist = new ObjectDistant(10001);
        Registry registery = LocateRegistry.createRegistry(2001);
        Naming.rebind("rmi://localhost:2001/objDist", objDist);

    }


}
