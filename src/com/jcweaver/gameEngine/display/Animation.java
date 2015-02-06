package com.jcweaver.gameEngine.display;

import java.awt.Graphics;

public class Animation {
private Texture[] tex;
private int curFrame;
private int maxFrame;
private double interval;
private double count;

public Animation(Texture[] t, float fps){
	tex = t;

	interval = 1.0/fps;
	maxFrame = tex.length;
	
}
public void load(){
	for(Texture temp : tex){
		temp.load();
	}
}
public void unload(){
	for(Texture temp : tex){
		temp.unload();
	}
}

public void update(double delta){
	count += delta;
	
	if(count >= interval){
		count = 0;
		nextFrame();
	}
	
}
public void setFps(float fps){
	interval = 1.0/fps;
}
public void draw(Graphics g, float x, float y, float w, float h,float angle){
	Window.draw(g, tex[curFrame], x, y, w, h,angle);
}
public Texture getCurTexture(){
	return tex[curFrame];
}
public void nextFrame(){
	curFrame++;
	if(curFrame >= maxFrame){
		curFrame = 0;
	}
}
}
