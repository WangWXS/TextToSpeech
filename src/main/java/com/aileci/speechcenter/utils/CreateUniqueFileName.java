package com.aileci.speechcenter.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by aileci on 2015/5/28.
 */
public class CreateUniqueFileName {
    /**
     * 生成唯一文件名 UUID 方法
     * @param date
     */
    public static String createUniqueFileName(Date date,String fileType){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String stringDate=simpleDateFormat.format(date);
        UUID uuid=UUID.randomUUID();
        String stringUUID=uuid.toString();
        String fileName=stringUUID+stringDate+"."+fileType;
           return fileName;
    }
   public static String testUniqueCreate(String text){
        return null;
   }
    public static void main(String[]args){
        CreateUniqueFileName createUniqueFileName=new CreateUniqueFileName();
        Date date=new Date();
        String fileName=createUniqueFileName.createUniqueFileName(date,"wav");
        System.out.println(fileName);

    }
}
