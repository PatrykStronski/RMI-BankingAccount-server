package Pkg;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MasterBus extends Remote {
    public String registerUser(String login, String password) throws RemoteException;
    public String authorize(String login, String password)throws RemoteException;
    public int depositText(String text, String token) throws RemoteException;
    public String fetchText(int nmb, String token)throws RemoteException;
    public boolean logOff(String Token)throws RemoteException;
}
