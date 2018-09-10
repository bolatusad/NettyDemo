package com.zt.study.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ Author: zxh
 * @ Date: Created in 19:23 2018/9/10
 * @ Description: 时间服务器，解码，拆包
 * @ Modified By:
 */
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4){
            return;
        }
        out.add(in.readBytes(4));
    }
}
