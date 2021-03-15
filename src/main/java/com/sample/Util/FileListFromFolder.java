package com.sample.Util;

import java.io.File;

public class FileListFromFolder {


	    public static void main(String a[]){
	        File file = new File("D:\\screenshots\\enzacta\\done\\bis model");
	        String[] fileList = file.list();
			
			
	        for(String name:fileList){
//	            System.out.println(name);
	        
	            System.out.println( name.substring(0,name.indexOf(".")));//"-- "+
			}
	    }
	}

