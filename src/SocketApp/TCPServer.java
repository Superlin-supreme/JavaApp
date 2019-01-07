/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketApp;

import java.io.*;
import java.net.*;

/**
 *
 * @author gdufs
 */
public class TCPServer {
    private ServerSocket serverSocket;
    
    public TCPServer() throws IOException{
        serverSocket = new ServerSocket(8008);
        System.out.println("服务器启动");
    }
    
    private PrintWriter putWriter(Socket socket) throws IOException{
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(socketOut, "GB2312"), true);
    }
    
    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn, "GB2312"));        
    }
    
    public void service(){
        while(true){
            Socket socket = null;
            try{
                socket = serverSocket.accept();
                System.out.println("New Income" + socket.getInetAddress());
                BufferedReader br = getReader(socket);
                PrintWriter pw = putWriter(socket);
                
                String msg;
                while((msg = br.readLine()) != null){
                    pw.println("来自服务器：" + msg);
                    if(msg.equals("bye")){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(socket!=null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String argString[])throws IOException {
        new TCPServer().service();
    }
}
