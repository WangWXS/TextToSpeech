package com.aileci.speechcenter.utils;

/**
 * Created by gaoqi on 2015/6/9.
 */
public class ProduceSpeechFile {
    public static void produceSpeechFileByDir(String fileSourcePath,String fileSavePath,String text,Integer volume,Integer rate,String fileType){

    }
    public static void produceSpeechFile(String fileSourcePath,String fileSavePath,Integer volume,Integer rate,String fileType){
          SapiFunctionUtil.produceSpeechByFile(fileSourcePath,fileSavePath,volume,rate,fileType);
    }
    public static void main(String[] args){
        ProduceSpeechFile.produceSpeechFile("c:/test/test.txt","c:/SpeechResult/",80,0,"wav");
    }
}
