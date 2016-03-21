package com.aileci.speechcenter.utils;

import java.util.Date;

/**
 * Created by gaoqi on 2015/9/7.
 */
public class ProduceSpeechFileUtil {
    public static void produceSpeechFileByFile(String fileSourcePath , String fileSavePath ,Integer volume , Integer rate ,String fileType){
        SapiFunctionUtil.produceSpeechByFile(fileSourcePath , fileSavePath ,volume ,rate,fileType);
    }
    public static void main(String [] args){
        /*for(int i = 1; i < 8; i++){
            produceSpeechFileByFile("c:/" + i + "/10000.txt", "c:/res" + i + "/", 100, -2, "wav");
        }*/
        produceSpeechFileByFile("c:/5/10000.txt", "c:/res5/", 100, -2, "wav");
        //SapiFunctionUtil.saveSpeechToFile("ability-to-pay principle",80,-6,new Date(),"wav","c:/");
        //System.out.print("hello world"+"\r\n"+"\r\n"+"\r\n");
    }
}
