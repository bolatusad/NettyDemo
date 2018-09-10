package com.zt.study.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @ Author: zxh
 * @ Date: Created in 17:39 2018/9/10
 * @ Description: 时间客户端处理器
 * @ Modified By:
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    //这样去读的话，是可能存在tcp半包的情况的，因为是流传输的
    //因此，需要先进行解码，可以提供一个解码的程序类，如果有足够的，才进行解析
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
