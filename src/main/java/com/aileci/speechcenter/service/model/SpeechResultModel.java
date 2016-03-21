package com.aileci.speechcenter.service.model;

/**
 * Created by gaoqi on 2015/6/1.
 */
public class SpeechResultModel {
    private String text;
    private Integer volume;
    private Integer rate;
    private String filePath;
    private Integer status;//记录生成状态,0正常1存在异常

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
