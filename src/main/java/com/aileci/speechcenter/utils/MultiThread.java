package com.aileci.speechcenter.utils;

import java.util.Date;

/**
 * Created by gaoqi on 2015/6/24.
 */
public class MultiThread implements Runnable {
    private String text;
    private String filePath;
    public MultiThread(String text,String filePath) {
        this.text = text;
        this.filePath=filePath;
    }


    public void run(){
        SapiFunctionUtil sapiFunctionUtil=new SapiFunctionUtil();
        sapiFunctionUtil.saveSpeechToFileTemp(text,80,-1,new Date(),"wav",filePath);
    }
}
