package Pkg;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StringStorage extends UnicastRemoteObject implements MasterBus {
    public static final long serialVersionUID = 101L;
    private UsrFx usr;
    private StorageFx str;
    private ArrayList<Token> tokens = new ArrayList<Token>();
    public StringStorage(Registry reg) throws RemoteException {
        super();
        UsrMn u = new UsrMn();
        Storage s = new Storage();
        reg.rebind("//localhost/usr",u);
        reg.rebind("//localhost/str",s);
        try {
            this.usr = (UsrFx) reg.lookup("//localhost/usr");
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        try {
            this.str = (StorageFx) reg.lookup("//localhost/str");
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public String registerUser(String login, String password){
        boolean repl = this.usr.registerUser(login,password);
        if(repl){
            return "user registered";
        } else {
            return "Pkg.User not registered";
        }
    };

    public String authorize(String login, String password){
        String token = this.usr.authenticate(login,password);
        if(token == "#"){
            return null;
        } else {
            this.tokens.add(new Token(token,login));
            return token;
        }
    };
    public int depositText(String text, String token){
        int tokenId = seekToken(token);
        if(tokenId>-1){
           return this.str.addStr(this.tokens.get(tokenId).user,text);
        } else {
            return -1;
        }
    };
    public String fetchText(int nmb, String token){
        int tokenId = seekToken(token);
        if(tokenId>-1 && nmb>-1){
            return this.str.getStr(this.tokens.get(tokenId).user,nmb);
        } else {
            return null;
        }

    };
    public boolean logOff(String token){
        int tokenId = seekToken(token);
        if(tokenId>-1){
            this.tokens.remove(tokenId);
            return true;
        } else {
            return false;
        }
    };

    private int seekToken(String token){
        for(int i = 0; i<this.tokens.size(); i++){
            if(this.tokens.get(i).token==token) return i;
        }
        return -1;
    }
}
