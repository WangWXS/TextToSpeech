package com.aileci.speechcenter.utils;

import com.aileci.speechcenter.service.model.SpeechResultModel;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.*;
import java.util.Date;
/**
 * Created by gaoqi on 2015/5/28.
 */
public class SapiFunctionUtil {

    /**
     * 将获得的文本转换成语音
     * @param text 获得的文本
     * @param volume 语音的音量大小
     * @param rate   语音朗读的速率
     */
    public static void textToSpeechUtil(String text, Integer volume, Integer rate) {
        ActiveXComponent activeXComponent = new ActiveXComponent("Sapi.SpVoice");
        Dispatch dispatch = activeXComponent.getObject();
        try {
            activeXComponent.setProperty("Volume", volume);
            activeXComponent.setProperty("Rate", rate);
            Dispatch.call(dispatch, "Speak", new Variant(text));

        } catch (Exception e) {
            System.out.println("the method textToSoundUtil of Class CreateSapiInstance has exception,the exception is");
            e.printStackTrace();
        } finally {
            dispatch.safeRelease();
            activeXComponent.safeRelease();
        }
    }
    /**
     * 将文本转换成语音,并将语音存储到文件
     * @param text
     * @param volume
     * @param rate
     * @param date
     */
    public static SpeechResultModel saveSpeechToFile(String text,Integer volume,Integer rate,Date date,String fileType,String path){
        Dispatch spFileStream=null;
        Dispatch spMMAudioOut=null;
        Dispatch spAudioFormat=null;
        ActiveXComponent axSpFile;
        int formatType=22;
        ActiveXComponent activeXComponent=new ActiveXComponent("Sapi.SpVoice");
        Dispatch dispatch=activeXComponent.getObject();
        axSpFile=new ActiveXComponent("Sapi.SpFileStream");
        spFileStream=axSpFile.getObject();
        SpeechResultModel speechResultModel=new SpeechResultModel();
        String fileName=MD5.createUniqueFileName(text,fileType);
        String filePath=path+fileName;
        speechResultModel.setFilePath(filePath);
        speechResultModel.setText(text);
        speechResultModel.setRate(rate);
        speechResultModel.setVolume(volume);
        speechResultModel.setStatus(0);
        try{
            if(spAudioFormat==null){
                axSpFile=new ActiveXComponent("Sapi.SpAudioFormat");
                spAudioFormat=axSpFile.getObject();
            }
            Dispatch.put(spAudioFormat,"Type",new Variant(formatType));
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            Dispatch.call(spFileStream, "Open", new Variant(filePath), new Variant(3), new Variant(true));
            Dispatch.putRef(dispatch, "AudioOutputStream", spFileStream);
            Dispatch.put(dispatch, "Volume", new Variant(volume));
            Dispatch.put(dispatch,"Rate",new Variant(rate));
            Dispatch.call(dispatch, "Speak", new Variant(text));
            Dispatch.call(spFileStream,"Close");
            Dispatch.putRef(dispatch,"AudioOutputStream",null);
        }catch(Exception e){
            speechResultModel.setStatus(1);
            System.out.println("the method saveSpeechToFile of Class CreateSapiInstance has exception,the exception is");
            e.printStackTrace();
        }finally{
            axSpFile.safeRelease();
            dispatch.safeRelease();
            activeXComponent.safeRelease();
        }
        return speechResultModel;
    }
    /**
     * 根据输入的文件位置,把文件内包含的多个句子转换成多个文件1对1
     * @param fileSourcePath
     * @param fileSaveDir
     * @param volume
     * @param rate
     * @param fileType
     */
    public static void produceSpeechByFile(String fileSourcePath,String fileSaveDir,Integer volume,Integer rate,String fileType){
        int wordCount = 0;
        Dispatch spFileStream=null;
        Dispatch spMMAudioOut=null;
        Dispatch spAudioFormat=null;
        ActiveXComponent axSpFile;
        int formatType=22;
        ActiveXComponent activeXComponent=new ActiveXComponent("Sapi.SpVoice");
        Dispatch dispatch=activeXComponent.getObject();
        axSpFile=new ActiveXComponent("Sapi.SpFileStream");
        spFileStream=axSpFile.getObject();
        String fileName=null;
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        String str=null;
        SpeechResultModel speechResultModel=null;
        FileWriter fileWriter = null;
        try{
            fileReader=new FileReader(fileSourcePath);
            fileWriter = new FileWriter("c:/fail.txt",true);
            bufferedReader=new BufferedReader(fileReader);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            if (spAudioFormat == null) {
                axSpFile = new ActiveXComponent("Sapi.SpAudioFormat");
                spAudioFormat = axSpFile.getObject();
             }
            while((str=bufferedReader.readLine())!=null) {
                wordCount++;
                speechResultModel=new SpeechResultModel();
                speechResultModel.setRate(rate);
                speechResultModel.setVolume(volume);
                speechResultModel.setStatus(0);
                //fileName=MD5.createUniqueFileName(str,fileType);
                fileName = str + "." + fileType;
                String filePath=fileSaveDir+fileName;
                speechResultModel.setFilePath(filePath);
                speechResultModel.setText(str);
                Dispatch.put(spAudioFormat, "Type", new Variant(formatType));
                Dispatch.putRef(spFileStream, "Format", spAudioFormat);
                Dispatch.call(spFileStream, "Open", new Variant(filePath), new Variant(3), new Variant(true));
                Dispatch.putRef(dispatch, "AudioOutputStream", spFileStream);
                Dispatch.put(dispatch, "Volume", new Variant(volume));
                Dispatch.put(dispatch, "Rate", new Variant(rate));
                Dispatch.call(dispatch, "Speak", new Variant(str));
                Dispatch.call(spFileStream, "Close");
                Dispatch.putRef(dispatch, "AudioOutputStream", null);
                System.out.println(wordCount);
            }
        }catch(Exception e){
            speechResultModel.setStatus(1);
            try {
                fileWriter.write(str+"\r\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("the method saveSoundToFile of Class CreateSapiInstance has exception,the exception is");
            e.printStackTrace();
        }finally{
            try {
                fileReader.close();
                bufferedReader.close();
                axSpFile.safeRelease();
                dispatch.safeRelease();
                activeXComponent.safeRelease();
                fileWriter.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    public SpeechResultModel saveSpeechToFileTemp (String text,Integer volume,Integer rate,Date date,String fileType,String path){
        Dispatch spFileStream=null;
        Dispatch spMMAudioOut=null;
        Dispatch spAudioFormat=null;
        ActiveXComponent axSpFile;
        int formatType=22;
        ActiveXComponent activeXComponent=new ActiveXComponent("Sapi.SpVoice");
        Dispatch dispatch=activeXComponent.getObject();
        axSpFile=new ActiveXComponent("Sapi.SpFileStream");
        spFileStream=axSpFile.getObject();
        SpeechResultModel speechResultModel=new SpeechResultModel();
        // CreateUniqueFileName createUniqueFileName=new CreateUniqueFileName();
        String fileName=MD5.createUniqueFileName(text,fileType);
        String filePath=path+fileName;
        speechResultModel.setFilePath(filePath);
        speechResultModel.setText(text);
        speechResultModel.setRate(rate);
        speechResultModel.setVolume(volume);
        speechResultModel.setStatus(0);
        try{
            if(spAudioFormat==null){
                axSpFile=new ActiveXComponent("Sapi.SpAudioFormat");
                spAudioFormat=axSpFile.getObject();
            }
            Dispatch.put(spAudioFormat,"Type",new Variant(formatType));
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            Dispatch.call(spFileStream, "Open", new Variant(filePath), new Variant(3), new Variant(true));
            Dispatch.putRef(dispatch, "AudioOutputStream", spFileStream);
            Dispatch.put(dispatch, "Volume", new Variant(volume));
            Dispatch.put(dispatch,"Rate",new Variant(rate));
            Dispatch.call(dispatch, "Speak", new Variant(text));
            Dispatch.call(spFileStream,"Close");
            Dispatch.putRef(dispatch,"AudioOutputStream",null);
        }catch(Exception e){
            speechResultModel.setStatus(1);
            System.out.println("the method saveSpeechToFile of Class CreateSapiInstance has exception,the exception is");
            e.printStackTrace();
        }finally{
            axSpFile.safeRelease();
            dispatch.safeRelease();
            activeXComponent.safeRelease();
        }
        return speechResultModel;
    }

}
