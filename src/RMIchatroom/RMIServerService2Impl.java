/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIchatroom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Superlin
 */
public class RMIServerService2Impl extends UnicastRemoteObject implements RMIServerService2{
    HashSet<RMIClientService> onLine = new HashSet<RMIClientService>();
    
    public RMIServerService2Impl()throws RemoteException{ }
    //和服务器对话的远程方法
    public String send(String msg)throws RemoteException{
        return "服务器的返回:"+msg;
    }
    //客户加入群组的远程方法，在name中包含自己的学号和姓名
    public void addClientToOnLine(RMIClientService client, String name)throws RemoteException{
        onLine.add(client);
        ClientChangeMsg("\""+name+"\"加入到群聊!");
    }
    //客户退出群组的远程方法
    public void delClientFromOnLine(RMIClientService client, String name)throws RemoteException{
        onLine.remove(client);
        ClientChangeMsg("\""+name+"\"退出群聊!");
    }
    //有客户加入和退出时推送消息
    public void ClientChangeMsg(String msg)throws RemoteException{
        Iterator it = onLine.iterator();
        while(it.hasNext()){
            RMIClientService client = (RMIClientService)it.next();
            client.showMessageToClient(msg);
        }
    }
    //客户在群组中发送聊天消息的远程方法
    //若客户端调用了该方法，则服务器遍历群组集合，将该消息推送到群组中所有的成员
    public void sendMessageToServer(String msg, String name)throws RemoteException{
        Iterator it = onLine.iterator();
        while(it.hasNext()){
            RMIClientService client = (RMIClientService)it.next();
            //调用客户端的远程对象方法showMsgToClient()实现刷新消息窗口
            client.showMessageToClient("\""+name+"\"发出消息："+msg);
        }
    }
}
