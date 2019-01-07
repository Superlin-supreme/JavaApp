/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIchatroom;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Superlin
 */
public class RMIServer2 {
    public static void main(String args[]){
        try{
            //第一步 注册并监听1099端口（RMI的默认端口，HTTP的默认端口是80）
            LocateRegistry.createRegistry(1099);
            //第二步 实例化服务器的远程对象
            RMIServerService2 service = new RMIServerService2Impl();
            //第三步 用助记符来对外发布远程对象，service远程对象对外服务的名字是：RMIService
            Naming.rebind( "RMIService2", service);
            System.out.println( "服务器发布了Java RMI远程对象RMIService2" );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
