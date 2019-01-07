/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIchatroom;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Superlin
 */
public interface RMIServerService2 extends Remote{
    //和服务器对话的远程方法
    public String send(String msg)throws RemoteException;
    //客户加入群组的远程方法，在name中包含自己的学号和姓名
    public void addClientToOnLine(RMIClientService client, String name)throws RemoteException;
    //客户退出群组的远程方法
    public void delClientFromOnLine(RMIClientService client, String name)throws RemoteException;
    //有客户加入和退出时推送消息
    public void ClientChangeMsg(String msg)throws RemoteException;
    //客户在群组中发送聊天消息的远程方法
    public void sendMessageToServer(String msg, String name)throws RemoteException;
}
