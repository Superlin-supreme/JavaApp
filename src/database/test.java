/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Superlin
 */
public class test {
    public static void main(String arg[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3366/studentdb?user=root&password=20181204&useUnicode=true&characterEncoding=utf8");
            if(!conn.isClosed()){
                System.out.println("服务器连接成功！");
                Statement stmt = conn.createStatement();
                
//                String sName = new String("小m".getBytes("utf-8"),"utf-8");
//                String sClass = new String("计算机02".getBytes("utf-8"),"utf-8");
//                stmt.executeUpdate("insert into STUDENTS (NO,NAME,AGE,CLASS) "
//                    +"VALUES ('20151000002','"+sName+"',22,'"+sClass+"')");   
                
                boolean hasResultSet= stmt.execute("SELECT * from students");
                if(hasResultSet){
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        // 通过字段检索
                    String id = rs.getString("NO");
                    String name = rs.getString("NAME");
                    int age = rs.getInt("AGE");
                    String cls = rs.getString("CLASS");
                    // 输出数据
                    System.out.print("NO: " + id + "| NAME: " + name + "| AGE: " + age + "| CLASS: " + cls + "\n");
                    }
                } else {
                    System.out.print("无结果集返回\n");
                }        
                stmt.close();
                conn.close(); 
                if(conn.isClosed()){
                    System.out.println("服务器连接关闭！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
