/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Superlin
 */
public class RMIServerServiceImpl extends UnicastRemoteObject implements RMIServerService{
    String rs;
    public RMIServerServiceImpl() throws RemoteException{ }
    public String send(String msg) throws RemoteException{
        return "来自20161003409 陈超林服务器的返回:"+msg;
    }
    public String send(String yourNo, byte[] yourName) throws RemoteException{
//        String rs;
        try {
            rs = yourNo + new String(yourName, "GB2312");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RMIServerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
