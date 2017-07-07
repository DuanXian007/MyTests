package com.duanxian.test;

import java.io.*;
import java.net.Socket;

/**
 * Created by Lionsong on 2017/6/26.
 */
public class ClientSocketTest {
    public void createClientSocket(int port){
        try {
            Socket socket = new Socket("localhost",port);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("User: admin; Password: 123");
            pw.flush();
            socket.shutdownOutput();
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = br.readLine())!=null){
                System.out.println("I'm client, server said: "+info);
            }
            br.close();
            is.close();
            pw.close();
            os.close(); 
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ClientSocketTest cs = new ClientSocketTest();
        cs.createClientSocket(10086);
    }
}
