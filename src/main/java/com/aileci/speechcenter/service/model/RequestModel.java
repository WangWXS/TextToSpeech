package com.aileci.speechcenter.service.model;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by gaoqi on 2015/6/2.
 */
public class RequestModel implements Serializable{
    private String text;
    private Integer volume;
    private Integer rate;
    private String fileSavePath;
    private String fileType;
    private Long uniqueId;

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSavePath() {
        return fileSavePath;
    }

    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
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

}
