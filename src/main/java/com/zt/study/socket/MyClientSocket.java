package com.zt.study.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/10/6 13:24
 **/
public class MyClientSocket {

    private static final int port = 12345;

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true){
            Socket socket = new Socket("127.0.0.1",port);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            String str = "hello sever";
            outputStream.writeUTF(str);
            outputStream.flush();
            Thread.sleep(1000*10);
        }

    }

}
