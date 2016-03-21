package com.aileci.speechcenter.service.model;

/**
 * Created by gaoqi on 2015/5/28.
 */
public class ParameterModel {
    private String text;
    private Integer volume;
    private Integer rate;

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
}
