package com.jcweaver.gameEngine.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private static boolean[] isDown = new boolean[512];
	
	private static String buffer = "";
	
	public static String getBuffer(){
		String ret;
		ret = buffer;
		buffer = "";
		return ret;
	}
	
	public static void flushBuffer(){
		buffer = "";
	}
	
	public static boolean isDown(int keyCode){
		return isDown[keyCode];
	}
	
	public void keyPressed(KeyEvent arg0) {
		if(!isDown[arg0.getKeyCode()]){
			buffer += arg0.getKeyCode();
		}
		isDown[arg0.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent arg0) {

		isDown[arg0.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent arg0) {
	}

}
