package com.duanxian.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lionsong on 2017/6/26.
 */
public class ServerSocketTest {
   public void createSocket(int port){
       ServerSocket serverSocket = null;
       try {
           serverSocket = new ServerSocket(port);
           Socket socket = serverSocket.accept();
           InputStream is = socket.getInputStream();
           InputStreamReader isr = new InputStreamReader(is,"UTF-8");
           BufferedReader br = new BufferedReader(isr);
           String info = null;
           while ((info = br.readLine())!=null){
               System.out.println("I'm server: "+info);
           }
           socket.shutdownInput();
           OutputStream os = socket.getOutputStream();
           PrintWriter pw = new PrintWriter(os);
           pw.write("Welcome!");
           pw.flush();
           pw.close();
           os.close();
           br.close();
           isr.close();
           is.close();
           socket.close();
           serverSocket.close();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
    public static void main(String[] args){
       ServerSocketTest st = new ServerSocketTest();
       st.createSocket(10086);
    }
}
