package com.aileci.speechcenter.utils;

import java.io.*;

/**
 * Created by gaoqi on 2015/9/23.
 */
public class SplitFileUtil {
    /**
     * split big file
     * @param filePath
     */
    public static void splieFile(String filePath){
        File file = new File(filePath);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        if(file.exists()){
            try {
                fileReader = new FileReader(filePath);
                bufferedReader = new BufferedReader(fileReader);
                String str = null;
                int count = 0;
                int order = 1;
                fileWriter = new FileWriter(new File("c:/1/10000.txt"),true);
                while((str = bufferedReader.readLine())!=null){
                    count++;
                    fileWriter.write(str+"\r\n");
                    if(count % 10000 == 0){
                        order++ ;
                        if(fileWriter != null){
                            fileWriter.close();
                            fileWriter = new FileWriter(new File("c:/"+order+"/10000.txt"),true);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileReader.close();
                    bufferedReader.close();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String [] args){
        SplitFileUtil.splieFile("c:/word.txt");
    }
}
