package com.zt.study;

import com.bbk.im.tlv.TLVUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/9/14 14:39
 **/
public class Temp {

    public static void main(String[] args) {
        Long dialogId = 47828565l;
        System.out.println(dialogId/5000000);
        System.out.println(dialogId%5000);
    }

    @Test
    public void getByteKey(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Set<String> keysSet = jedis.keys("*");
        Long synckey = 5L;
        Long dialogId = 1234567L;
        byte[] keyTemp = String.format("%s%d_%d","message_",dialogId,synckey).getBytes();
        jedis.set(keyTemp,"123456".getBytes());

//        for(String key : keysSet){
//            System.out.println(key);
//        }
    }


    /**
     * 获取redis中所有key
     */
    @Test
    public void getByteResult(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Set<String> keysSet = jedis.keys("message_1234567_*");
        for(String key : keysSet){
            System.out.println(Arrays.toString(jedis.get(key.getBytes())));
        }
    }

    @Test
    public void insetTestMessage() throws IllegalAccessException, InstantiationException {
        Jedis jedis = new Jedis("172.28.1.145",6387);
        byte[] temp = {2, 7, 109, 115, 103, 73, 100, 73, 100, 3, 1, 2, 4, 1, 1, 5, 1, 14, 6, 1, 1, 7, 1, 2, 8, 19, 32, 17, 10, 1, 0, 16, 1, 0, 19, 1, 0, 22, 1, 0, 25, 3, 97, 97, 97, 9, 8, 0, 0, 1, 93, 48, 88, -22, 112};
        for(int i = 15;i>0;i--){
//            Message message = new Message();
//            message.setMessageId(78978979789L);
////            message.setMessage("vocTime: 0, storeAddr: null, groupId: null, lastIndex: null, gdLatitude: null, gdLongitude: null, gdRadius: 0, bdLatitude: null, bdLongitude: null, bdRadius: 0, ggLatitude: null, ggLongitude: null, ggRadius: 0, resoureKey: null, deadline: null, extra: aaa, commandId: null, commandDesc: null.".getBytes());
//            message.setAccountId(9l);
//            message.setCreated(new Date());
//            message.setDialogId(42l);
//            message.setModify(new Date());
//            message.setMsgId(UUID.randomUUID().toString());
//            message.setRegistId(4564564l);
//            message.setMsgType((short) 2);
//            message.setSyncKey((long) i);
            Message message = TLVUtil.decode(temp,Message.class);
            message.setMsgType((short) 2);
            message.setAccountId(10l);
            message.setDialogId(42l);
            message.setSyncKey((long) i);
            message.setCreatedTime(new Date().getTime());
            message.setCreated(new Date());
            byte[] messageTemp = TLVUtil.encode(message);
            jedis.set(("message_42_"+i).getBytes(),messageTemp);
        }
//        byte[] value = jedis.get("message_14_1".getBytes());
//        System.out.println(Arrays.toString(value));

    }

    @Test
    public void tlvEncodeMessage() throws IllegalAccessException, InstantiationException {
//        Message message = new Message();
//        message.setMessageId(78978979789L);
//        message.setMessage("\"vocTime: 0, storeAddr: null, groupId: null, lastIndex: null, gdLatitude: null, gdLongitude: null, gdRadius: 0, bdLatitude: null, bdLongitude: null, bdRadius: 0, ggLatitude: null, ggLongitude: null, ggRadius: 0, resoureKey: null, deadline: null, extra: aaa, commandId: null, commandDesc: null.".getBytes());
//        message.setAccountId(9l);
//        message.setCreated(new Date());
//        message.setDialogId(42l);
//        message.setModify(new Date());
//        message.setMsgId(UUID.randomUUID().toString());
//        message.setRegistId(4564564l);
//        message.setMsgType((short) 2);
        byte[] temp = {2, 7, 109, 115, 103, 73, 100, 73, 100, 3, 1, 2, 4, 1, 1, 5, 1, 14, 6, 1, 1, 7, 1, 2, 8, 19, 32, 17, 10, 1, 0, 16, 1, 0, 19, 1, 0, 22, 1, 0, 25, 3, 97, 97, 97, 9, 8, 0, 0, 1, 93, 48, 88, -22, 112};
        Message message = TLVUtil.decode(temp,Message.class);
//        System.out.println(Arrays.toString(TLVUtil.encode(message)));
        System.out.println(message);
    }

    @Test
    public void testTemp(){
        System.err.println("hello");
        System.out.println("hello");
    }
}
