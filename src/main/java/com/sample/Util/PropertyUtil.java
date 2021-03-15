package com.sample.Util;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;

public class PropertyUtil
{
    public static Properties LoadProperties(final String fileName)  {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return prop;
    }
   
    public static String getProperty(String filename, final String Key)  {
//        final Properties prop = this.LoadProperties(String.valueOf(new File("./").getAbsolutePath()) + "\\config\\env.properties");
    	   final Properties prop = LoadProperties(filename);
    	return prop.getProperty(Key);
    }
    
    public static void setProperty(String filename, final String Key, final String Value) throws IOException {
//        final Properties prop = this.LoadProperties(String.valueOf(new File("./").getAbsolutePath()) + "\\config\\env.properties");
    	  final Properties prop = LoadProperties(filename);
        prop.setProperty(Key, Value);
    }
}