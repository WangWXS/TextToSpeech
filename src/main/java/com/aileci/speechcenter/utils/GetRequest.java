package com.aileci.speechcenter.utils;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by gaoqi on 2015/6/15.
 */
public class GetRequest {
    private HashMap<Integer,HttpServletRequest> hashMap;
    private PublishAndSubscribeMessage publishAndSubscribeMessage=new PublishAndSubscribeMessage();

    public GetRequest(HashMap<Integer, HttpServletRequest> hashMap) {
        this.hashMap = hashMap;
        Jedis jedis=new Jedis("localhost",6379);
        jedis.publish("tv2","hello");
        Jedis jedis1=new Jedis("localhost",6379);
        jedis1.psubscribe(this.publishAndSubscribeMessage,"tv2");
    }

}
