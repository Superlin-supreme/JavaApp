/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMail;

/**
 *
 * @author Superlin
 */
public class BASE64Encoder {
    public static void main(String args[]) {
//        String uname = "20161003409@gdufs.edu.cn";
//        String pwd = "asdasdccl15755";
        String uname = "419084766@qq.com";
        String pwd = "vydtseqxmtjvbgfc";
        
        uname = new sun.misc.BASE64Encoder().encode(uname.getBytes());
        pwd = new sun.misc.BASE64Encoder().encode(pwd.getBytes());
        
        System.out.println(uname);
        System.out.println(pwd);
    }
}
