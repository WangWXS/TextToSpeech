package com.aileci.speechcenter.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by gaoqi on 2015/6/15.
 */
public class PublishAndSubscribeMessage extends JedisPubSub {
    private HashMap<Integer,HttpServletRequest> hashMap;
    public PublishAndSubscribeMessage(){

    }
    public PublishAndSubscribeMessage(HashMap<Integer, HttpServletRequest> hashMap) {
        this.hashMap = hashMap;
        Jedis jedis=new Jedis("localhost",6379);
        jedis.publish("tv2","hello");
        Jedis jedis1=new Jedis("localhost",6379);
        jedis1.subscribe(new PublishAndSubscribeMessage(), "tv2");
    }

    public void onMessage(String channel, String message) {
          System.out.println(channel + "=" + message);
         System.out.println("即将进入语音转换程序");
     }
    // 初始化订阅时候的处理
     public void onSubscribe(String channel, int subscribedChannels) {
       System.out.println(channel + "=" + subscribedChannels);
       }
     // 取消订阅时候的处理
     public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "=" + subscribedChannels);
        }
     // 初始化按表达式的方式订阅时候的处理
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern + "=" + subscribedChannels);
    }
     // 取消按表达式的方式订阅时候的处理
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern + "=" + subscribedChannels);
    }
    // 取得按表达式的方式订阅的消息后的处理
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(channel + "=" + message);
    }
   /*public static void main(String[]args){
       Jedis jedis=new Jedis("localhost",6379);
       jedis.publish("tv2","hello");
       Jedis jedis1=new Jedis("localhost",6379);
       jedis1.subscribe(new PublishAndSubscribeMessage(null), "tv2");
       jedis.disconnect();
       jedis1.disconnect();

   }*/

}
