package com.zt.study.nio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/10/6 14:45
 **/
public class JavaNIOStudy {

    @Test
    public void testPath(){
        Path path = Paths.get("F:\\temp\\hello.txt");
        Path current = Paths.get(".");
        System.out.println(current.toAbsolutePath().normalize());
        System.out.println(path.normalize());
    }

    @Test
    public void nioServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int selectKeyNum = selector.select(1000);
            if(selectKeyNum == 0){
                //为0，说明现在没有待处理的事件
                continue;
            }
            for (SelectionKey key : selector.selectedKeys()){
                if(key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if(socketChannel == null){
                        continue;
                    }
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("accept a connect:"+socketChannel.getRemoteAddress()+socketChannel);
                } else if(key.isReadable()){
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer bf = ByteBuffer.allocate(1024);
                    try {
                        while (sc.read(bf) > 0) {
                            bf.flip();
                            System.out.println(Charset.forName("UTF-8").decode(bf)
                                    .toString());
                            bf.clear();
                        }
                        if (bf != null) {
                            bf.clear();
                            bf = null;
                        }
                        sc.write(Charset.forName("UTF-8").encode(
                                "got messages from client=" + sc));
                        key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                    } catch (IOException e) {
//                    logger.admin(e.getMessage(), e);
                        try {
                            sc.close();// 关闭发生发生异常的连接，并取消注册SelectionKey
                        } catch (IOException e1) {
//                        logger.admin(e1.getMessage(), e1);
                        }
                        key.cancel();
                    }
                }

            }
        }
    }

    @Test
    public void nioClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        System.out.println(socketChannel);
        while (true){
            System.out.println(socketChannel.isConnected());
//            if (socketChannel.isConnected()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
                int writeBytes = socketChannel.write(Charset.forName("UTF-8").encode("hello"));
                if(writeBytes == 0){
                    socketChannel.register(selector,SelectionKey.OP_WRITE);//注册写事件，当缓冲区满时
                }
//            }
        }
    }

}
