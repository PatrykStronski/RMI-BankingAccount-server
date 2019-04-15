package Pkg;

import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class UsrMn extends UnicastRemoteObject implements UsrFx {
    public static final long serialVersionUID = 102L;
    private ArrayList<User> users = new ArrayList<User>();
    public UsrMn() throws RemoteException {
        super();
    }
    public String authenticate(String usr, String passwd){
        for (User u : this.users) {
            if (u.login == usr && u.pass == passwd) {
                return createToken();
            }
        }
        return "#";
    };
    public boolean registerUser(String usr, String passwd){
        for(User u: this.users){
            if(u.login==usr){
                return false;
            }
        }
        this.users.add(new User(usr,passwd));
        return true;
    };

    private String createToken(){
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
