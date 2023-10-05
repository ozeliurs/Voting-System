package client;

import org.apache.commons.codec.digest.DigestUtils;
import shared.AuthentificationStub;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class AuthentificationStubImpl extends UnicastRemoteObject implements AuthentificationStub, Serializable {
    String studentId;
    public AuthentificationStubImpl(String studentId) throws RemoteException {
        super();
        this.studentId = studentId;
    }

    @Override
    public String getStudentId() throws RemoteException {
        return this.studentId;
    }

    @Override
    public String getPasswordHash() throws RemoteException {
        System.out.print("Password: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        return DigestUtils.sha256Hex(password);
    }
}
