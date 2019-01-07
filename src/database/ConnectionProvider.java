/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Superlin
 */
public class ConnectionProvider {
    private String JDBC_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    
    public ConnectionProvider(){
        //PropertyReader对象会读出db.conf文件中的配置信息
        JDBC_DRIVER = PropertyReader.get("JDBC_DRIVER");
        DB_URL=PropertyReader.get("DB_URL");
        DB_USER=PropertyReader.get("DB_USER");
        DB_PASSWORD=PropertyReader.get("DB_PASSWORD");
        try{
            Class jdbcDriver = Class.forName(JDBC_DRIVER);
            java.sql.DriverManager.registerDriver((Driver)jdbcDriver.newInstance());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException{
        Connection con=java.sql.DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        return con;
    }
}
