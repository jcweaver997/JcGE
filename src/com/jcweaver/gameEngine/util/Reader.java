package com.jcweaver.gameEngine.util;

import java.io.File;
import java.io.FileInputStream;

public class Reader {

	@SuppressWarnings("resource")
	public static String[] read(File f){
		FileInputStream fis;
		String[]  s;
		s = new String[5];
		try {
			fis = new FileInputStream(f);
			byte[] ba = new byte[1024];
			
					fis.read(ba);
					String sa = new String(ba).trim();
					int lines = 1;
					
					for(byte b : sa.getBytes()){
						if (b==13){
							lines++;
						}
					}
					
					s = new String[lines+1];
					
					for(int i = 0; i < s.length; i++){
						s[i] = "";
					}
					
					int curline = 0;
					for(byte b : sa.getBytes()){
						if(b!=13&&b!=10){
							s[curline] +=(char)b;
						}
						if (b==13){
							curline++;
						}
					}
					
			return s;
			
			
			
		} catch (Exception e) {e.printStackTrace();}
		
		return s;
		
		
		
		
		
	}
	
	
}
