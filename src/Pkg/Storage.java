package Pkg;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Storage extends UnicastRemoteObject implements StorageFx {
    public static final long serialVersionUID = 103L;
    private ArrayList<Lib> lib = new ArrayList<Lib>();
    public Storage() throws RemoteException {
        super();
    }
    public String getStr(String usr,int nmb){
        Lib yourLib = iterateLib(usr);
        if(yourLib==null || nmb>= this.lib.size()){
            return null;
        } else {
            return yourLib.lib.get(nmb);
        }
    };
    public int addStr(String usr,String text){
        Lib yourLib = iterateLib(usr);
        if(yourLib==null){
            yourLib = new Lib(usr);
            yourLib.lib.add(text);
            this.lib.add(yourLib);
            return 0;
        } else {
            yourLib.lib.add(text);
            return yourLib.lib.size()+1;
        }
    };

    public Lib iterateLib(String user){
        for(Lib x: this.lib){
            if(x.user==user){
                return x;
            }
        }
        return null;
    }
}
