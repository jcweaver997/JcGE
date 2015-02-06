package com.jcweaver.gameEngine.game;

import java.awt.Graphics;

import com.jcweaver.gameEngine.display.Display;

public abstract class Scene {
	public abstract void init();
	public abstract void update(double delta);
	public abstract void render(Graphics g);
	
	protected int pX(float p){
		return (int)(Display.getCanvas().getWidth()*p);
	}
	protected int pY(float p){
		return (int)(Display.getCanvas().getHeight()*p);
	}
}
