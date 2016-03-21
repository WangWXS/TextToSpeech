package com.aileci.speechcenter.controllers;

import com.aileci.speechcenter.controllers.vo.SpeechResultVO;
import com.aileci.speechcenter.service.TextToSpeechService;
import com.aileci.speechcenter.service.model.RequestModel;
import com.aileci.speechcenter.service.model.SpeechResultModel;
import com.aileci.speechcenter.utils.ResultResolver;
import com.aileci.speechcenter.utils.ViewResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by gaoqi on 2015/6/1.
 */
@Controller
public class TestController {
    @Autowired
    TextToSpeechService textToSpeechService;
    @ResponseBody
    @RequestMapping("/speechcenter/transfer")
    public String getSpeechResult(HttpServletRequest request){
        RequestModel requestModel=(RequestModel)request.getAttribute("model");
        return requestModel.getFileSavePath()+" "+requestModel.getText();
    }
    @ResponseBody
    @RequestMapping("/getspeech")
    public ViewResultModel getResult(@RequestParam("text")String text,@RequestParam("volume")Integer volume,@RequestParam("rate")Integer rate,@RequestParam("fileType")String fileType){
        String path="c:/SpeechResult/";
        SpeechResultModel speechResultModel=textToSpeechService.getSpeechFileByText(text,volume,rate,new Date(),fileType,path);
        SpeechResultVO speechResultVO=new SpeechResultVO();
        speechResultVO.setSpeechResultModel(speechResultModel);
        return ResultResolver.sendNormalResult(speechResultVO);
    }
    @RequestMapping("/get")
    @ResponseBody
    public String getSpeech(@RequestParam("text")String text,@RequestParam("volume")Integer volume,@RequestParam("rate")Integer rate,@RequestParam("fileType")String fileType){
        String path="c:/SpeechResult/";
        SpeechResultModel speechResultModel=textToSpeechService.getSpeechFileByText(text,volume,rate,new Date(),fileType,path);
        SpeechResultVO speechResultVO=new SpeechResultVO();
        speechResultVO.setSpeechResultModel(speechResultModel);
        return "hello world";
    }
}
