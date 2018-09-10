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
        System.out.println("1111111111111");
        if(in.readableBytes() < 4){
            return;
        }
//        out.add(in.readBytes(4));

        System.out.println("2222222222222222");
        //解析成一个对象
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
