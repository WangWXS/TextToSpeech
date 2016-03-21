package com.aileci.speechcenter.service;

import com.aileci.speechcenter.service.model.ParameterModel;
import com.aileci.speechcenter.service.model.ResultParameterModel;
import com.aileci.speechcenter.service.model.SpeechResultModel;

import java.util.Date;

/**
 * Created by gaoqi on 2015/5/28.
 */
public interface TextToSpeechService {
    public void textToSpeech(String text,Integer volume,Integer rate);
    public SpeechResultModel getSpeechFileByText(String text,Integer volume,Integer rate,Date date,String fileType,String path);
    public SpeechResultModel getSpeechFileByDir(String text,Integer volume,Integer rate,Date date,String fileType,String path);
    public ResultParameterModel getSpeechFile(String text,Integer volume,Integer rate,String fileType);
}
