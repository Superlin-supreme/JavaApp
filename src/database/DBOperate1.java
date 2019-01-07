/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Superlin
 */
public class DBOperate1 {
    
    public static void main(String args[]) throws Exception{
        //加载MySQL驱动器，其中com.mysql.jdbc.Driver包含在mysqldriver.jar中。
        Class jdbcDriver = Class.forName("com.mysql.jdbc.Driver");

        //注册MySQL驱动器
        java.sql.DriverManager.registerDriver ((Driver)jdbcDriver.newInstance());
        
        //指定数据库所在位置
//        String dbURL = "jdbc:mysql://localhost:3366/studentdb?useUnicode=true&characterEncoding=GB2312"; 
//        String dbUSER = "root";
//        String dbPWD = "20181204";
        String dbURL ="jdbc:mysql://192.168.207.101:3306/STUDENTDB1";
        String dbUSER = "myuser";
        String dbPWD = "mysql";
        //创建数据库连接对象
        Connection con = java.sql.DriverManager.getConnection(dbURL,dbUSER,dbPWD);
        //创建数据库执行对象：
        Statement stmt = con.createStatement(); 
        
//        String sName = new String("陈超林".getBytes("GB2312"),"ISO-8859-1");
//        String sClass = new String("网络工程1601".getBytes("GB2312"),"ISO-8859-1");
//        String sIP = "192.168.207.4";
//        stmt.executeUpdate("insert into STUDENTS (NO,NAME,AGE,CLASS,IP) "
//             +"VALUES ('20161003409','"+sName+"',23,'"+sClass+"', '"+sIP+"')");
        
        //从数据库的返回集合中读出数据，并验证自己插入的信息是否包含其中：
        ResultSet rs= stmt.executeQuery("SELECT * from students");
            //将数据库的字段值赋值给程序变量sName或String sName = rs.getString(STUDENTS.NAME);
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("NO");
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                String cls = rs.getString("CLASS");
                String sIP = rs.getString("IP");
                
                String GBname = new String(name.getBytes("ISO-8859-1"),"GB2312");
                String GBcls = new String(cls.getBytes("ISO-8859-1"),"GB2312");
                String GBip = new String(sIP.getBytes("ISO-8859-1"),"GB2312");
                // 输出数据
                System.out.print("NO: " + id + "| NAME: " + GBname + "| AGE: " + age + "| CLASS: " + GBcls + "| IP: " + GBip + "\n");
            }
        rs.close();
        stmt.close();
        con.close();
    }

}
