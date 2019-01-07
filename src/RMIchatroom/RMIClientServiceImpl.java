/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIchatroom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Superlin
 */
public class RMIClientServiceImpl extends UnicastRemoteObject implements RMIClientService{
    RMIChatClientJFrame frame;
    public RMIClientServiceImpl(RMIChatClientJFrame frame)throws Exception{
        this.frame = frame;
    }
    public void showMessageToClient(String msg)throws RemoteException{
        //在客户端的聊天窗口中显示msg内容
        frame.appendMessageToArea(msg);
    }
}
