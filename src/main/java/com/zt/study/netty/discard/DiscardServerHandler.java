package com.zt.study.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @ Author: zxh
 * @ Date: Created in 15:37 2018/9/10
 * @ Description: 基于netty的丢弃处理器
 * @ Modified By:
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //丢弃bytebuf中的内容
//        ((ByteBuf)msg).release();
        //输出收到的内容
        ByteBuf in = (ByteBuf) msg;
        try {
            //循环的方式接受消息
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
            //调用netty 提供的工具
            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));

            //写回数据
            ctx.write(msg);
            ctx.flush();
        } finally {
            //释放收到的buf
//            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
