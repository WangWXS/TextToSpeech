package com.aileci.speechcenter.utils;
import java.io.File;
/**
 * Created by gaoqi on 2015/6/9.
 */
public class CheckFileIsExistByText {
    /**
     * 根据输入的文本检查是否已经生成对应的语音文件
     * @param filePath
     * @param text
     * @param fileType
     * @return
     */
    public static Boolean fileIsExist(String filePath,String text,String fileType){
        File file=new File(filePath+text);
        if(file.exists()){
            return true;
        }
        return false;
    }
}
