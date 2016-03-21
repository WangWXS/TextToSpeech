package com.aileci.speechcenter.interceptor.bean;

/**
 * Created by aileci on 2015/7/10.
 */
public class RequestCountBean {
    private Long requestCount;

    public RequestCountBean(Long requestCount) {
        this.requestCount = requestCount;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount)
    {
        this.requestCount = requestCount;
    }
    public void decRequestCount(){
        this.requestCount--;
    }
    public void incRequestCount(){
        this.requestCount++;
    }
    public synchronized void incAndDecRequestCount(boolean flag){
        if(flag){
            this.incRequestCount();
        }else{
            this.decRequestCount();
        }
    }
}
