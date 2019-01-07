/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftpClient;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author gdufs
 */
public class FileDialogServer {
  private int port=2021;

  private ServerSocket server;

  private ExecutorService executorService;  //线程池

  private final int POOL_SIZE=15;  //单个CPU时线程池中工作线程的数目

 

  public FileDialogServer() throws IOException {

 

server = new ServerSocket(port);//打开服务器端口

 

    //创建线程池

    //Runtime的availableProcessors()方法返回当前系统的CPU的数目

    //系统的CPU越多，线程池中工作线程的数目也应该越多

    executorService= Executors.newFixedThreadPool(

        Runtime.getRuntime().availableProcessors() * POOL_SIZE);

 

    System.out.println("多用户服务器启动");

  }

 

  public void service() {//主进程，用于接收客户、分配线程给客户

       

     while (true) {

      Socket socket=null;

      try {

        socket = server.accept(); //监听客户请求, 阻塞语句.

        executorService.execute(new Handler(socket));

       //接受一个客户请求,从线程池中拿出一个线程专门处理该客户.   

      }catch (IOException e) {  }

    }

  }

 

  public static void main(String args[])throws IOException {

    new FileDialogServer().service();

  }

}

 

//定义内部类，实现和客户对话功能

class Handler implements Runnable{

  private Socket socket;

 

  public Handler(Socket socket){

    this.socket=socket;

  }

  private PrintWriter getWriter(Socket socket)throws IOException{

    OutputStream socketOut = socket.getOutputStream();

    return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);

  }

 

  private BufferedReader getReader(Socket socket)throws IOException{

    InputStream socketIn = socket.getInputStream();

    return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));

  }

 

  public void run(){//覆盖线程体

    try {

       

      BufferedReader br =getReader(socket);//字节装饰成字符流

      PrintWriter pw = getWriter(socket); //字节装饰成字符流

      pw.println("20161003409 陈超林");
      
      //文件目录服务模块
      pw.println("可供下载的文件如下：");

      String fName1="G:\\workspace";//给出下载目录路经

      File f=new File(fName1);

      String[] s=f.list();//获得目录下所有的子目录和文件名.

      int len=s.length;

      int n=0;

      long filelength=0;

      File temp;

      while(n<len){

         temp=new File(f+"\\"+s[n]);

         if(temp.isFile()){

             filelength=temp.length()/1024;//字节单位KB

             pw.println(s[n]+" ["+filelength+"KB]");

         }else

             pw.println(s[n]+" [DIR]");

         n++;

      }

      pw.println("************文件下载********************");

      pw.println("输入文件全名，点击下载按钮,退出输入bye");
      
      
      String msg = null;

      while (( msg = br.readLine())!= null) {       

        pw.println("From ThreadServer: " + msg); //send to client.
        
        System.out.println("From Client: " + msg);
 

        if (msg.contains("bye".subSequence(0, 2))){

            System.out.println( socket.getInetAddress() + ":" +"Exit");

            break;

        }

      }

    }catch (IOException e) {

       e.printStackTrace();

    }finally {

       try{

         if(socket!=null)socket.close();

       }catch (IOException e) { }

    }

  }    
}
