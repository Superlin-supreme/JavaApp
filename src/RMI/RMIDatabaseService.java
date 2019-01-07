/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Superlin
 */
public interface RMIDatabaseService extends Remote{
    //定义一个远程方法，在给定的数据库中插入一条记录，并返回插入的结果.
    public String insert(String no,String name,int age, String sClass) throws RemoteException;    
}
