/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Superlin
 */
public interface RMIClientService extends Remote{
    //刷新聊天信息的方法，供服务器调用
    public void showMessageToClient(String msg)throws RemoteException;
}
