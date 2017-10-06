package com.zt.study.socket;

import org.junit.platform.commons.util.CollectionUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/10/6 13:24
 **/
public class MyServerSocket {

    private static final int port = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        while (true){
            if(serverSocket == null){
                serverSocket = new ServerSocket(port);
            }
            Socket socket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String str = inputStream.readUTF();
            System.out.println(str);
            inputStream.close();
        }
    }
}
