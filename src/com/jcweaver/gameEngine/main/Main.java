package com.jcweaver.gameEngine.main;


import com.jcweaver.gameEngine.display.Display;
import com.jcweaver.gameEngine.physics.Physics;

public class Main {
public static void main(String[] args){

	new Display("test", 720,480,false);
	Display.addScene(new Menu());
	Display.create();
}
}
