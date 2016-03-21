package com.aileci.speechcenter.utils;

import com.aileci.speechcenter.service.model.RequestModel;


/**
 * Created by gaoqi on 2015/6/1.
 */
public class TestRedis {
    public static void main(String[]args){
        RequestModel requestModel=new RequestModel();
        requestModel.setFileType("wav");
        requestModel.setFileSavePath("c:");
        requestModel.setRate(10);
        requestModel.setVolume(10);
        requestModel.setText("hello");
        requestModel.setUniqueId(2l);
        CreateInsertSql createInsertSql=new CreateInsertSql();
        System.out.println(createInsertSql.createInsert(requestModel,"user"));


    }
}
