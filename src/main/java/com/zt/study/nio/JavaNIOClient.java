package com.zt.study.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/10/6 14:58
 **/
public class JavaNIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);//在connect之前使用
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        while (true){
            if (socketChannel.isConnected()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int writeBytes = socketChannel.write(Charset.forName("UTF-8").encode(reader.readLine()));
                if(writeBytes == 0){
                    socketChannel.register(selector,SelectionKey.OP_WRITE);//注册写事件，当缓冲区满时
                }
            }
            int selectKeys = selector.select();
            if(selectKeys == 0){
                continue;
            }
            for (SelectionKey key : selector.selectedKeys()){
                if(key.isConnectable()){
                    SocketChannel socketChannel1 = (SocketChannel) key.channel();
                    if(socketChannel == null){
                        continue;
                    }
                    socketChannel1.configureBlocking(false);
                    socketChannel1.register(selector,SelectionKey.OP_READ);
                    socketChannel1.finishConnect();
                }else if (key.isReadable()){
                    SocketChannel socketChannel1 = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel1.read(byteBuffer) > 0){
                        byteBuffer.flip();
                        System.out.println(Charset.forName("UTF-8").decode(byteBuffer).toString());
                        byteBuffer.clear();
                    }
                }else if(key.isWritable()) {
                    //只要缓冲区未满，就会一直产生写事件，如果此时又不写数据时，惠山省不必要的资源
                    //损耗，所以这里需要取消写事件，避免CPU消耗100%
                    //写数据，如果写缓冲区满时，继续注册写事件，
//                    key.interestOps(key.interestOps()|SelectionKey.OP_WRITE);
                    key.interestOps(key.interestOps()&~SelectionKey.OP_WRITE);
                }

            }
            selector.selectedKeys().clear();
        }
    }
}
