package com.jcweaver.gameEngine.game;

import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
	private ArrayList<GameObject> objects;
	public Map(ArrayList<GameObject> go){
		objects = go;
	}
	public void load(){
		for(GameObject b : objects){
			b.load();
		}
	}
	public void unload(){
		for(GameObject b : objects){
			b.unload();
		}
	}
	public void draw(Graphics g){
		for(GameObject b : objects){
			b.draw(g);
		}
	}
}
