package com.zt.study.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @Descriprion: TODO
 * @author: zhangxiaohua
 * @create 2017/9/26 9:00
 **/
public class RedisDemo {

    @Test
    public void redisTransaction(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.keys("*"));
        Transaction transaction = jedis.multi();
        transaction.incr("num1");
        System.out.println(2/0);
        transaction.incr("num2");
        transaction.exec();
        System.out.println("num1:"+jedis.get("num1")+"\nnum2:"+jedis.get("num2"));


    }
}
