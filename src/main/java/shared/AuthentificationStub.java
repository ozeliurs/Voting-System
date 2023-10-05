package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthentificationStub extends Remote {

    String getStudentId() throws RemoteException;
    String getPasswordHash() throws RemoteException;

}
