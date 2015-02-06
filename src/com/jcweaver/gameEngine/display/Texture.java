package com.jcweaver.gameEngine.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture{
	
	private BufferedImage b;
	private String loc;
	public Texture(String loc){
		this.loc = loc;
		
	}
	
	
	public void load(){
		try {
			b = ImageIO.read(getClass().getResource(loc));
		} catch (IOException e) {e.printStackTrace();}
		
	}
	public void unload(){
		b = null;
	}
	public void draw(Graphics g,float x,float y,float w,float h, float angle){
		try{
		g.drawImage(b, (int)x, (int)y, (int)w, (int)h, Display.getCanvas());
		}catch(Exception e){e.printStackTrace();}
	}
	
}
