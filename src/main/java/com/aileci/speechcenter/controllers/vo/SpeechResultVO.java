package com.aileci.speechcenter.controllers.vo;

import com.aileci.speechcenter.service.model.ResultSpeechModel;
import com.aileci.speechcenter.service.model.SpeechResultModel;

/**
 * Created by gaoqi on 2015/6/1.
 */
public class SpeechResultVO {
    private ResultSpeechModel resultSpeechModel;
    private SpeechResultModel speechResultModel;

    public SpeechResultModel getSpeechResultModel() {
        return speechResultModel;
    }

    public void setSpeechResultModel(SpeechResultModel speechResultModel) {
        this.speechResultModel = speechResultModel;
    }

    public ResultSpeechModel getResultSpeechModel() {
        return resultSpeechModel;
    }

    public void setResultSpeechModel(ResultSpeechModel resultSpeechModel) {
        this.resultSpeechModel = resultSpeechModel;
    }
}
