package com.duanxian.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by Lionsong on 2017/6/26.
 */
public class URLTest {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String hostname = address.getHostName();
            String ip = address.getHostAddress();
            byte[] bytes = address.getAddress();
            System.out.print(hostname + "\n");
            System.out.print(ip + "\n");
            for (byte b : bytes) {
                System.out.print(b+"\n");
            }
//            System.out.print(bytes);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            URL baidu = new URL("http://www.baidu.com");
//            URL url = new URL(baidu,"/index.html?username=梦无休止符#test");
//            System.out.println(url.getProtocol());
//            System.out.println(url.getHost());
//            System.out.println(url.getPort());
//            System.out.println(url.getPath());
            InputStream is = baidu.openStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();
            while(data != null){
                System.out.println(data);
                data = br.readLine();
            }
            br.close();
            isr.close();
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
