package com.aileci.speechcenter.thread;

import com.aileci.speechcenter.redis.RedisOperation;
import com.aileci.speechcenter.service.model.RequestModel;
import com.aileci.speechcenter.service.model.SpeechResultModel;
import com.aileci.speechcenter.utils.SapiFunctionUtil;
import org.omg.CORBA.Request;
import redis.clients.jedis.Jedis;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by gaoqi on 2015/6/26.
 */
public class MultiThreadService implements Runnable {

    private HashMap<Long,AsyncContext> hashMap;
    private RequestModel requestModel;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private AsyncContext asyncContext;
    public void setParameter(HashMap<Long,AsyncContext> hashMap){
        this.hashMap=hashMap;
    }
   /**
     * synchronized method create speech file by request parameter
     *
     */
    public synchronized void createSpeechFile(){
        Jedis jedis= RedisOperation.getConnection("localhost");
        if(jedis!=null){
            RequestModel requestModelFromRedis;
            AsyncContext asyncContext1;
            String text;
            Integer volume;
            Integer rate;
            String fileType;
            String filePath;
            Integer status;
            while((requestModelFromRedis=(RequestModel)RedisOperation.getValueByKey(jedis,"RequestQueueList".getBytes()))!=null) {
                text=requestModelFromRedis.getText();
                volume=requestModelFromRedis.getVolume();
                rate=requestModelFromRedis.getRate();
                fileType=requestModelFromRedis.getFileType();
                filePath=requestModelFromRedis.getFileSavePath();
                SpeechResultModel speechResultModel = SapiFunctionUtil.saveSpeechToFile(text, volume, rate, new Date(), fileType, filePath);
                if (speechResultModel != null) {
                    status=speechResultModel.getStatus();
                    if(status==0) {
                        asyncContext1 = hashMap.get(requestModelFromRedis.getUniqueId());
                        hashMap.remove(requestModelFromRedis.getUniqueId());
                        asyncContext1.dispatch("/speechcenter/getspeech?text=" + text+"&filePath="+filePath+"&status="+status);
                        System.out.println("hashmap size is" + hashMap.size());
                    }else{
                        asyncContext1 = hashMap.get(requestModelFromRedis.getUniqueId());
                        hashMap.remove(requestModelFromRedis.getUniqueId());
                        asyncContext1.dispatch("/speechcenter/getspeech?text=" + text+"&filePath=empty"+"&status="+status);
                        System.out.println("hashmap size is" + hashMap.size());
                    }
                }
            }
        }

    }
    public void run(){
        this.createSpeechFile();
        requestModel=(RequestModel)((HttpServletRequest) asyncContext.getRequest()).getAttribute("requestModel");
        if(request!=null){
            System.out.println("request is no null");
            System.out.println(request);
        }
        System.out.println("attribute parameter is "+((HttpServletRequest)asyncContext.getRequest()).getRequestURL());
        System.out.println("进入到run方法");
        Jedis jedis= RedisOperation.getConnection("localhost");
        RequestModel requestModel1=(RequestModel)RedisOperation.getValueByKey(jedis,"requestQueueList".getBytes());
        Set<Map.Entry<Long,AsyncContext>> mapEntrySet=hashMap.entrySet();
        Iterator iterator=mapEntrySet.iterator();
        while(iterator.hasNext()){
          Map.Entry<Long,AsyncContext> mapEntry=(Map.Entry)iterator.next();
            System.out.println(mapEntry.getKey());
            System.out.println(hashMap.size());

        }
        while(requestModel!= null) {
            System.out.println("run parameter is" + asyncContext.getRequest().getParameter("text"));
            SapiFunctionUtil.saveSpeechToFile(requestModel.getText(), requestModel.getVolume(), requestModel.getRate(), new Date(), requestModel.getFileType(), requestModel.getFileSavePath());
            try {
                System.out.println(requestModel.getUniqueId());
                System.out.println("hashmap size is:"+hashMap.size());
                if(hashMap.get(1L)!=null){
                    System.out.println("its not null");
                }
                hashMap.get(1L).dispatch("/test.jsp");
                requestModel=null;
            }catch( Exception e){
                e.printStackTrace();
                requestModel=null;
            }

        }
    }
}

