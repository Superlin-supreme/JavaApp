/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 *
 * @author Superlin
 */
public class DBOperate2 {

    private final ConnectionProvider provider;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public DBOperate2(ConnectionProvider provider) {
        this.provider = provider;
    }

    public void addStudent(String sNo, String sName, int age, String sClass, String sIP) throws SQLException, UnsupportedEncodingException {

        con = provider.getConnection();
        stmt = con.createStatement();
        sName = new String(sName.getBytes("GB2312"), "ISO-8859-1");
        sClass = new String(sClass.getBytes("GB2312"), "ISO-8859-1");
        sIP =  new String(sIP.getBytes("GB2312"), "ISO-8859-1");
//        sName = new String(sName.getBytes("UTF-8"), "UTF-8");
//        sClass = new String(sClass.getBytes("UTF-8"), "UTF-8");
        String sql = "insert into peoples2(NO,NAME,AGE,CLASS,IP) values('"
            + sNo + "','" + sName + "'," + age + ",'" + sClass + "','" + sIP + "')";

        stmt.execute(sql);

        closeStatement();
        closeConnection();

    }

    //存二进制大数据文件到数据库表的Clob字段中.
    public void saveClobToDatabase(String fileName) throws Exception {
        con = provider.getConnection();
        PreparedStatement stmtp = con.prepareStatement("insert into ACLOB(ID,FILE) values(?,?) ");
        stmtp.setLong(1, 1);
        FileInputStream fin = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(fin);
        stmtp.setCharacterStream(2, reader, fin.available());
        stmtp.executeUpdate();
        reader.close();
        stmtp.close();
    }

    public void deleteStudent(String name) throws SQLException {
        try {
            con = provider.getConnection();
            stmt = con.createStatement();
            String sql = "delete from CUSTOMERS where NAME='" + name + "'";
            stmt.execute(sql);
        } finally {
            closeStatement();
            closeConnection();
        }
    }

    public void printAllStudents() throws SQLException, UnsupportedEncodingException {

        try {
            con = provider.getConnection();
            stmt = con.createStatement();
            //查询记录
            rs = stmt.executeQuery("SELECT * from peoples2");
            //输出查询结果
            while (rs.next()) {
                int sID = rs.getInt(1);
                String sNo = rs.getString(2);
                String sName = new String((rs.getString(3)).getBytes("ISO-8859-1"), "GB2312");
//                String sName = rs.getString(2);
                int age = rs.getInt(4);
                String sClass = new String((rs.getString(5)).getBytes("ISO-8859-1"), "GB2312");
                String sIP = new String((rs.getString(6)).getBytes("ISO-8859-1"), "GB2312");

                //打印所显示的数据
                System.out.println("id="+sID+",no=" + sNo + ",name=" + sName + ",age=" + age + ",class=" + sClass + ",ip=" + sIP);

            }
        } finally {
            closeAll();
        }
    }

    public ResultSet DatabaseSet() throws SQLException {

        con = provider.getConnection();
        stmt = con.createStatement();
        //查询记录
        rs = stmt.executeQuery("SELECT NO,NAME,AGE,CLASS from STUDENTS");
        //输出查询结果

        return rs;
    }

    public void closeAll() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }
    }

    private void closeResultSet() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }

    private void closeStatement() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
        }
    }

    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }
    }
    //探测表名
    public void printMetaData() throws IOException,SQLException{
        try {
            con = provider.getConnection();
            DatabaseMetaData meta = con.getMetaData();
            //返回数据库的一些元信息。
            ResultSet tables = meta.getTables(null, null, null, new String[]{"TABLE"});
            //语句调用会返回一个ResultSet结果集，该结果集含4列，其中有一列含表名，可通过集合操作查看表名。
            while (tables.next()) {
                System.out.println("User tables: "+tables.getString("TABLE_NAME"));
                //peoples1,peoples2,teachers
            }
            tables.close();
        } finally {
            closeConnection();
        }
    }
    //探测某表字段名
    public void printTableInfo(String tableName) throws SQLException{
        try {
            con = provider.getConnection();
            stmt = con.createStatement();
            rs=stmt.executeQuery("select * from "+tableName);
            ResultSetMetaData fields = rs.getMetaData(); //会返回该表的字段信息
            int n = fields.getColumnCount(); //会返回表中字段的数目
            for(int i=1;i<=n;i++){
                String colname= fields.getColumnLabel(i);//会返回表中第i个字段名
                System.out.println("第"+i+"个字段名为"+colname);
            }
        } finally {
            closeConnection();
        }
    }

    public static void main(String args[]) throws Exception {
        DBOperate2 tester = new DBOperate2(new ConnectionProvider());
//        tester.printMetaData();
//        tester.printTableInfo("peoples1");
//        tester.addStudent("20161003409", "陈超林", 3, "网络工程1601","192.168.207.5");
        tester.printAllStudents();
//        tester.deleteCustomer("小王");
    }
}
