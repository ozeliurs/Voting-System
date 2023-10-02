package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjectDistant extends UnicastRemoteObject implements Distant {


    public ObjectDistant(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void echo() throws RemoteException{
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
