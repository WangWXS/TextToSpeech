package com.aileci.speechcenter.controllers;

import com.aileci.speechcenter.controllers.vo.SpeechResultVO;
import com.aileci.speechcenter.service.TextToSpeechService;
import com.aileci.speechcenter.service.model.ResultSpeechModel;
import com.aileci.speechcenter.service.model.SpeechResultModel;
import com.aileci.speechcenter.utils.ResultResolver;
import com.aileci.speechcenter.utils.ViewResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**produce speech file by the sentence transferd by user
 * Created by gaoqi on 2015/6/18.
 */

@Controller
public class DealRequestController {

    @Autowired
    TextToSpeechService textToSpeechService;
    @ResponseBody
    @RequestMapping("/speechcenter/getspeech")
    public ViewResultModel  createSpeechBySentence(@RequestParam("text")String text,@RequestParam("volume")Integer volume,@RequestParam("rate")Integer rate,@RequestParam("fileType") String fileType,@RequestParam("status")Integer status,@RequestParam("fileSavePath")String fileSavePath){
        ResultSpeechModel resultSpeechModel=new ResultSpeechModel();
        resultSpeechModel.setText(text);
        resultSpeechModel.setVolume(volume);
        resultSpeechModel.setFileType(fileType);
        resultSpeechModel.setRate(rate);
        resultSpeechModel.setFileSavePath(fileSavePath);
        SpeechResultVO speechResultVO=new SpeechResultVO();
        speechResultVO.setResultSpeechModel(resultSpeechModel);
        return ResultResolver.sendNormalResult(speechResultVO);
    }
    @ResponseBody
    @RequestMapping("/speechcenter/getspeecht")
    public String  createSpeechBySentenceTemp(@RequestParam("text")String text,@RequestParam("volume")Integer volume){
       /* String filePath="c:/SpeechResult/";
        SpeechResultModel speechResultModel=textToSpeechService.getSpeechFileByText(text, volume, rate, new Date(), fileType, filePath);
        SpeechResultVO speechResultVO=new SpeechResultVO();
        speechResultVO.setResult(speechResultModel);*/
        String userName=String.valueOf(1).intern();

        return text+volume;
    }
}
