package com.aileci.speechcenter.interceptor;

import com.aileci.speechcenter.interceptor.bean.RequestCountBean;
import com.aileci.speechcenter.redis.RedisOperation;
import com.aileci.speechcenter.service.model.RequestModel;
import com.aileci.speechcenter.thread.MultiThreadService;
import com.aileci.speechcenter.utils.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * 拦截器拦截对于语音转换请求路径的请求，加入到请求队列进行异步处理请求生成
 */
public class  RequestInterceptor extends HandlerInterceptorAdapter {
    public HashMap<Long,AsyncContext> hashMap=new HashMap<Long,AsyncContext>(10000);
    public RequestCountBean requestCountBean=new RequestCountBean(0L);
    public MultiThreadService multiThreadService=new MultiThreadService();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestCountBean.incAndDecRequestCount(true);
        RequestModel requestModel = new RequestModel();
        requestModel.setText(request.getParameter("text"));
        requestModel.setVolume(Integer.parseInt(request.getParameter("volume")));
        requestModel.setRate(Integer.parseInt(request.getParameter("rate")));
        requestModel.setUniqueId(requestCountBean.getRequestCount());
        requestModel.setFileSavePath("c:/SpeechResult/" + MD5.createUniqueFileName(requestModel.getText(), "wav"));
        requestModel.setFileType("wav");
        Jedis jedis = RedisOperation.getConnection("localhost");
        String uniqueFileName = MD5.createUniqueFileName(requestModel.getText(), request.getParameter("fileType"));
        RedisOperation.putValueToRedis(jedis, "requestQueueList", requestModel);
        RedisOperation.closeConnection(jedis);
        AsyncContext asyncContext=request.startAsync();
        hashMap.put(requestModel.getUniqueId(),asyncContext);
        multiThreadService.setParameter(hashMap);
        //asyncContext.setTimeout(100*1000);
        asyncContext.start(multiThreadService);
        /*AsyncContext asyncContext=request.startAsync();
        hashMap.put(requestCount,asyncContext);
        asyncContext.start(new MultiThredService(hashMap,requestModel,asyncContext));*/
       // Thread thread = new Thread(new MultiThreadService(hashMap, requestModel, request, response));
        //thread.start();
        /*RequestDispatcher rd = request.getRequestDispatcher("/speechcenter/getspeecht");
        try {
            rd.forward(request, response);
            return false;
        } catch (Exception e) {
        }*/
        return false;
    }
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           System.out.println("-------postHandle-------");

           if(modelAndView != null){
                     modelAndView.addObject("now", new Date());
                 }
         }

     @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
          System.out.println("-------afterCompletion-------");
        }

}
