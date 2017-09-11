package com.zt.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Descriprion: netty server
 * @author: zhangxiaohua
 * @create 2017/9/11 18:40
 **/
public class EchoServer {

    //端口
    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //创建event-loop
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建Server-Bootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)   //指定所使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port)) //使用指定的端口套接字地址
                    .childHandler(new ChannelInitializer() {  //添加一个EchoServer-Handler到子Channel的ChannelPipeline
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(serverHandler);
                        }
                    });
            //异步地绑定服务器，调用sync（）方法阻塞等待直到绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            //获取Channel的closeFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        }finally {
            //关闭Eventloop，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
