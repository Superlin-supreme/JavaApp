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

/**
 *
 * @author Superlin
 */
public class UDPClient {
    private int remotePort;
    private InetAddress remoteIP;
    private DatagramSocket socket;
    
    public UDPClient(String ip, String port) throws IOException{
        this.remotePort = Integer.parseInt(port);
        this.remoteIP = InetAddress.getByName(ip);
        
        //创建UDP套接字
        socket = new DatagramSocket(); //空套接字
        //与本地一个固定的UDP端口绑定
        //socket = new DatagramSocket(9000);
    }
    
    //定义数据发送方法
    public void send(String msg){
        try{
            //准备待发送的数据报
            byte[] outputData = msg.getBytes("GB2312");
            //构建一个数据报文
            DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, remoteIP, remotePort);
            //发送数据报
            socket.send(outputPacket);
        } catch (IOException ex) { }
    }
    
    //定义数据接收方法
    public String receive(){
        String msg;
        //准备空数据报文
        DatagramPacket inputPacket = new DatagramPacket(new byte[1024], 1024);
        try{
            //阻塞语句，有数据就装包，直到装完或装满
            socket.receive(inputPacket);
            //从报文中取出字节数据并装饰城字符
            msg = new String(inputPacket.getData(), 0, inputPacket.getLength(), "GB2312");
        } catch (IOException ex) {
            msg = null;
        }
        return msg;
    }
    
    public void close() {
        if(socket!=null)
            socket.close(); //释放端口
    }
    
}
