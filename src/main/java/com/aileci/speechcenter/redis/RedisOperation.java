package com.aileci.speechcenter.redis;

import com.aileci.speechcenter.utils.SerializeUtil;
import redis.clients.jedis.Jedis;
/**
 * redis 操作封装类
 */
public class RedisOperation {
    /**
     * 获取redis连接
     * @param address
     * @return
     */
    public static Jedis getConnection(String address){
        try{
            Jedis jedis=new Jedis(address);
                return jedis;
        }catch(Exception e){

        }
        return null;
    }

    /**
     * 根据key找到相应的value
     * @param jedis
     * @param bytes
     * @return
     */
    public static Object getValueByKey(Jedis jedis,byte[] bytes){
        if(jedis!=null) {
            byte[] bytes1 =jedis.lpop(bytes);
            return SerializeUtil.unserialize(bytes1);
        }
        return null;
    }

    /**
     * 将key和value放入到redis
     * @param jedis
     * @param taskListName
     * @param object
     */
    public static void putValueToRedis(Jedis jedis,String taskListName,Object object){
        byte[] bytes= SerializeUtil.serialize(object);
        jedis.rpush(taskListName.getBytes(),bytes);

    }

    /**
     * 关闭redis 连接
     * @param jedis
     */
    public static void closeConnection(Jedis jedis){
        try{
            jedis.disconnect();
        }catch(Exception e){

        }
    }
}
