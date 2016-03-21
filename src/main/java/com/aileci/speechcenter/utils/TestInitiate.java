package com.aileci.speechcenter.utils;

import java.util.Date;

/**
 * Created by gaoqi on 2015/6/15.
 */
public class TestInitiate {

    public static void main(String[]args){
        MultiThread multiThread=new MultiThread("hello world","c:/SpeechResult/");
        Thread thread=new Thread(multiThread);
        MultiThread multiThread1=new MultiThread("hello world my name is","c:/SpeechResult/");
        Thread thread1=new Thread(multiThread1);
        thread.start();
        thread1.start();
        //SapiFunctionUtil.saveSpeechToFile("First dates can be awkward, and most people don't know how to act. And if you're going out to dinner, getting the wrong type of food can make everything even more uncomfortable. So here's a suggested list of things you should NOT order on a first date",80,0,new Date(),"wav","c:/SpeechResult/");


    }
}
