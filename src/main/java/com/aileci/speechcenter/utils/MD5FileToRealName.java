package com.aileci.speechcenter.utils;

import java.io.*;
import java.util.HashMap;

/**
 * Created by gaoqi on 2016/1/8.
 */
public class MD5FileToRealName {

    public static void  md5FileNameToRelaName(String md5FileDir, String fileNameText){

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>(20000);
        File md5Dir = new File(md5FileDir);
        File [] files = md5Dir.listFiles();
        for(File temp : files){
            String md5Name = temp.getName().substring(0, temp.getName().indexOf('.'));
            hashMap.put(md5Name, 1);
        }

        File file = new File(fileNameText);
        if(file.exists()){

            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String temp = null;
                while((temp = bufferedReader.readLine()) != null){
                    if(hashMap.containsKey(MD5.createMD5FromText(temp))){
                        System.out.println("ok");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String [] args){
        MD5FileToRealName.md5FileNameToRelaName("C:/result", "C:/1/10000.text");
    }
}
