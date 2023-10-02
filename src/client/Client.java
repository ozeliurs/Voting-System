package client;

import shared.UserRepository;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client implements Serializable {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        UserRepository userRepository = (UserRepository) Naming.lookup("rmi://localhost:2001/userRepository");

        userRepository.checkCredentials("123456", "123456");

    }
}