package com.aileci.speechcenter.serviceimpl;

import com.aileci.speechcenter.service.TextToSpeechService;
import com.aileci.speechcenter.service.model.ResultParameterModel;
import com.aileci.speechcenter.service.model.SpeechResultModel;
import com.aileci.speechcenter.utils.CheckFileIsExistByText;
import com.aileci.speechcenter.utils.SapiFunctionUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by gaoqi on 2015/5/28.
 */
@Service
public class TextToSpeechServiceImpl implements TextToSpeechService {
    /**
     * 将输入的文本串转换成语音
     * @param text
     * @param volume
     * @param rate
     */
    public void textToSpeech(String text,Integer volume,Integer rate){
        SapiFunctionUtil.textToSpeechUtil(text, volume, rate);
    }
     /**
     * 根据输入的文本以及相应参数生成语音文件
     * @param text
     * @param volume
     * @param rate
     * @param date
     * @param fileType
     * @param path
     * @return
     */
    public SpeechResultModel getSpeechFileByText(String text,Integer volume,Integer rate,Date date,String fileType,String path){
       SpeechResultModel speechResultModel=new SpeechResultModel();
       if(!CheckFileIsExistByText.fileIsExist(path,text,fileType))
       {
           return SapiFunctionUtil.saveSpeechToFile(text,volume,rate,date,fileType,path);
       }
        speechResultModel.setRate(rate);
        speechResultModel.setVolume(volume);
        speechResultModel.setText(text);
        speechResultModel.setStatus(0);
        speechResultModel.setFilePath(path);
        return speechResultModel;

    }

    /**
     * 根据输入的文件夹路径将路径下包含的文本文件转换成语音文件
     * @param text
     * @param volume
     * @param rate
     * @param date
     * @param fileType
     * @param path
     * @return
     */
    public SpeechResultModel getSpeechFileByDir(String text,Integer volume,Integer rate,Date date,String fileType,String path){
        return null;
    }
    public ResultParameterModel getSpeechFile(String text,Integer volume,Integer rate,String fileType){
        return null;
    }
}
