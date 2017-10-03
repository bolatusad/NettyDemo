package com.zt.study.nio;

import io.netty.channel.ChannelFuture;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/9/22 9:46
 **/
public class ChannelDemo {

    @Test
    public void fileChannelDemo() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\hh.html","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        int bytesRead = fileChannel.read(byteBuffer);//read into buffer
        while (bytesRead != -1){
            System.out.println("Read:"+bytesRead);
            byteBuffer.flip(); //make buffer ready for read
            while (byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get()); //read one byte at a timee
            }
            System.out.println();
            byteBuffer.clear();
            bytesRead = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }

    @Test
    public void transferFormTest() throws IOException {
//        File file = new File("test.txt");
//        System.out.println(file.exists()+"length:"+file.length());
        RandomAccessFile fromFile = new RandomAccessFile("test.txt","rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("test2.txt","rw");
        FileChannel toFileChannel = toFile.getChannel();
        int position = 0;
        long count = fromChannel.size();
        toFileChannel.transferFrom(fromChannel,position,count);

    }


    @Test
    public void SelectorTest() throws IOException {
        //创建一个selector
        Selector selector = Selector.open();
    }

    @Test
    public void socketChannel() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(4700));
//        String newData = "New String to write to file..." + System.currentTimeMillis();
//
//        ByteBuffer buf = ByteBuffer.allocate(48);
//        buf.clear();
//        buf.put(newData.getBytes());
//
//        buf.flip();
//
//        while(buf.hasRemaining()) {
//            socketChannel.write(buf);
//        }

        int i = 0;
        while (i<50){
            Thread.sleep(3*1000);
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while(buf.hasRemaining()) {
                socketChannel.write(buf);
            }
        }
        socketChannel.close();
    }

    @Test
    public void serverSocketChannelTest() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        //非阻塞式
        serverSocketChannel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBuf = socketChannel.read(buffer);
                buffer.flip(); //make buffer ready for read
                while (buffer.hasRemaining()){
                    System.out.print((char)buffer.get()); //read one byte at a timee
                }
            }
        }
    }


    //是一个能收发UDP包的通道
    @Test
    public void datagramChannelTest() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));
        while(true){
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            byteBuffer.clear();
            channel.receive(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get()); //read one byte at a timee
            }
        }



    }

    //发送UDP包
    @Test
    public void sendDatagramChannel() throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        int bytesSent = channel.send(buf, new InetSocketAddress(9999));
    }

    @Test
    public void  tempTest(){
        SelectSockets selectSockets = new SelectSockets();
        selectSockets.run();
    }

}
