package com.sample.util;

import java.io.IOException;

import org.apache.log4j.Logger;
 import org.apache.log4j.PropertyConfigurator;
//Reference DOC: http://blogs.quovantis.com/how-to-use-log4j-with-selenium-web-driver/

public class logs4jUtil
{
    static String log4jConfPath;
    
    final Logger log = Logger.getLogger("SummaryLogger");
   
    static {
    	 String current = null;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logs4jUtil.log4jConfPath = current+"/config/log4j.properties";
        PropertyConfigurator.configure(logs4jUtil.log4jConfPath);
    }
    
//    public void LogRootInfo(final String msg) {
//        PropertyConfigurator.configure(Logs4jUtil.log4jConfPath);
//        final Logger log = Logger.getLogger("rootLogger");
//        log.info((Object)msg);
//    }
//    
//    public void LogInfo(final String msg) {
//        PropertyConfigurator.configure(Logs4jUtil.log4jConfPath);
//        final Logger log = Logger.getLogger("devpinoyLogger");
//        log.info((Object)msg);
//    }
//    
//    public void LogDebug(final String msg) {
//        PropertyConfigurator.configure(Logs4jUtil.log4jConfPath);
//        final Logger log = Logger.getLogger("devpinoyLogger");
//        log.debug((Object)msg);
//    }
    
    public void LogSumInfo(final String msg) {
        log.info(msg);
    }
    public void LogSumError(final String msg) {
        log.error(msg);
    }
    public void LogSumDebug(final String msg) {
        log.debug(msg);
    }
}

