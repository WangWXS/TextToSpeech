package com.aileci.speechcenter.utils;

/**
 * Created by gaoqi on 2015/6/1.
 */
public class ResultResolver {
    public static  ViewResultModel sendNormalResult(Object data){
        ViewResultModel viewRsultModel=new ViewResultModel();
        viewRsultModel.setData(data);
        return viewRsultModel;
    }
}
