/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPApp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

/**
 *
 * @author Superlin
 */
public class UDPServer {
    private DatagramSocket serverSocket;
    
    public UDPServer() throws IOException{
        serverSocket = new DatagramSocket(8888);
        System.out.println("服务器启动");
    }
    
    public void service(){
        while(true){
            try{
                //接收客户端发送的数据
                byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                //此方法在接收到数据前一直阻塞
                serverSocket.receive(packet);
                String info = new String(bytes, 0, packet.getLength(), "GB2312");
                System.out.println("我是服务器，客户端" + packet.getAddress() + "发来数据说：" + info);
                
                //响应客户端发来的消息
                //定义客户端地址，端口号，数据
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String msg = "20161003409 陈超林" + new Date().toString() + info;
                byte[] data = msg.getBytes("GB2312");
                packet = new DatagramPacket(data, data.length, address, port);
                serverSocket.send(packet);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String argString[]) throws IOException{
        new UDPServer().service();
    }
}
