/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import database.ConnectionProvider;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Superlin
 */
public class RMIDatabaseServiceImpl extends UnicastRemoteObject implements RMIDatabaseService{
    public RMIDatabaseServiceImpl()throws RemoteException{ };
    public String insert(String no,String name,int age, String sClass) throws RemoteException{
        Connection con=null;
        String msg = "";
        try{
            con = new ConnectionProvider().getConnection();           
            //省略记录插入代码
            if(!con.isClosed()){
                Statement stmt = con.createStatement();
                String sName = new String(name.getBytes("UTF-8"), "UTF-8");
                String sclass = new String(sClass.getBytes("UTF-8"), "UTF-8");
                String sql = "insert into STUDENTS(NO,NAME,AGE,CLASS) values("
                + "'" + no + "'" + "," + "'" + sName + "'" + "," + age + "," + "'" + sclass + "'" + ")";
                int affected_rows = stmt.executeUpdate(sql);
                if(affected_rows!=0){
                    msg = "添加记录成功，影响了"+affected_rows+"行。";
                } else {
                    msg = "添加记录失败！";
                }
                stmt.close();
                con.close();
            } else {
                msg = "连接数据库失败！";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }    
}
