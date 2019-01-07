/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jcapture;

import java.net.InetAddress;
import jpcap.*;
import jpcap.packet.*;

/**
 *
 * @author Superlin
 */
public class PacketSender {
    public static void main(String args[]){
        try{
            //获取网络接口参数
            NetworkInterface[] devices = JpcapCaptor.getDeviceList();
            //实例化JpcapSender类
            JpcapSender sender = JpcapSender.openDevice(devices[3]);
            //构造一个TCP包
            //TCPPacket tcp = new TCPPacket(int src_port, int dst_port,  long sequence, long ack_num, boolean urg, boolean ack,boolean psh,boolean rst, boolean syn, boolean fin, boolean rsv1,boolean rsv2, int window, int urgent);
            TCPPacket tcp = new TCPPacket(8000,8008,56,78,false,false,false,false,true,false,true,true,20,10);
            //设置TCPIPv4头参数, 源IP地址, 目标IP地址
            tcp.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100, IPPacket.IPPROTO_TCP, InetAddress.getByName("192.168.207.10"), InetAddress. getByName ("202.116.195.81"));
            //填充TCP中的用户数据
            tcp.data = ("20161003409 陈超林").getBytes("GB2312");
            //构造相应的MAC头
            EthernetPacket ether=new EthernetPacket();
            ether.frametype=EthernetPacket.ETHERTYPE_IP;
            tcp.datalink=ether;
            //MAC地址要转换成十进制，ipconfig /all 查看本机的MAC地址.
            //源src_mac地址是自己机器的MAC地址。若源机器的十六进制MC地址为:50-7B-9D-7D-D2-85,则十进制表示为:ether.src_mac=new byte[]{(byte)80,(byte)123,(byte)157,(byte)125,(byte)210,(byte)133};
            //本机以太网物理地址20-47-47-75-F1-B6
            ether.src_mac=new byte[]{(byte)32,(byte)71,(byte)47,(byte)117,(byte)241,(byte)182};
            //arp -a,查看默认网关（其IP一般是.1或.254的机器）的MAC地址，用默认网关作为下一跳转发该包, 将默认网关的MAC填入目的dst_mac中,如: ether.dst_mac=new byte[]{(byte)00,(byte)17,(byte)93,(byte)157,(byte)128,(byte)00};
            //192.168.226.254 00-11-5d-9c-94-00
            //192.168.207.254 00-11-5d-9c-94-00
            ether.dst_mac=new byte[]{(byte)00,(byte)17,(byte)93,(byte)156,(byte)148,(byte)00};
            //理解什么是下一跳。
            //发送特定的TCP包：
            sender.sendPacket(tcp);       
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
