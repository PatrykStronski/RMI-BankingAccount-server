package Pkg;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class MainServ {
    private static String path = "//localhost/StringStorage";
    public static void main(String argv[]) {
        System.setProperty("java.rmi.server.hostname","127.0.0.1");
        StringStorage srv = null;
        Registry reg = null;
        try {
            reg = LocateRegistry.createRegistry(8082);
            try {
                reg.bind(path, new StringStorage(reg));
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}