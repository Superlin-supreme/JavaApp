/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Superlin
 */
public class RMIServerService3Impl extends UnicastRemoteObject implements RMIServerService3{
    HashSet<RMIClientService> onLine = new HashSet<RMIClientService>();
    String name;
    public RMIServerService3Impl()throws RemoteException{ }
    //和服务器对话的远程方法
    public String send(String msg)throws RemoteException{
        return "服务器的返回:"+msg;
    }
    //客户加入群组的远程方法，在name中包含自己的学号和姓名
    public String addClientToOnLine(RMIClientService client, String name)throws RemoteException{
        this.name = name;
        onLine.add(client);
        return ("欢迎\""+name+"\"加入到超级无敌开心聊天室!");
    }
    //客户退出群组的远程方法
    public String delClientFromOnLine(RMIClientService client)throws RemoteException{
        onLine.remove(client);
        return ("\""+name+"\"退出群聊!");
    }
    
    //客户在群组中发送聊天消息的远程方法
    //若客户端调用了该方法，则服务器遍历群组集合，将该消息推送到群组中所有的成员
    public void sendMessageToServer(String msg)throws RemoteException{
        Iterator it = onLine.iterator();
        while(it.hasNext()){
            RMIClientService client = (RMIClientService)it.next();
            //调用客户端的远程对象方法showMsgToClient()实现刷新消息窗口
            client.showMessageToClient(msg);
        }
    }
}
