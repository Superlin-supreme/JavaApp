/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketApp;

import java.net.*;
import java.io.*;

/**
 *
 * @author gdufs
 */
public class TCPClient {
    
    private Socket socket = null;
    private PrintWriter pw;
    private BufferedReader br;
    
    public TCPClient(String IP, String Port) throws IOException{
        socket = new Socket(IP,Integer.parseInt(Port));
        
        OutputStream socketOut = socket.getOutputStream();
        pw = new PrintWriter(new OutputStreamWriter(socketOut, "GB2312"), true);
        
        InputStream socketIn = socket.getInputStream();
        br = new BufferedReader(new InputStreamReader(socketIn, "GB2312"));
    }
    
    public void send(String msg){
        pw.println(msg);
    }
    
    public String receive(){
        String msg;
        try{
            msg = br.readLine();
        } catch(IOException ex) {msg=null;}
        return msg;
    }
    
    public void close(){
        try{
            if(socket!=null)
                socket.close();
        } catch(IOException ex) {}
    }
    
    public static void main(String args[]) throws IOException{
        TCPClient tc = new TCPClient("127.0.0.1", "8008");
        tc.send("Hello, server");
        System.out.println(tc.receive());
        tc.close();
    }
}
