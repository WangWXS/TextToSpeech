package com.aileci.speechcenter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gaoqi on 2015/7/2.
 */
public class TestLogger {
   public static Logger logger=LoggerFactory.getLogger(TestLogger.class);
    public static void main(String[]args){

        logger.info("test logger");
    }
}
