package com.jcweaver.gameEngine.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import org.jbox2d.dynamics.BodyType;

import com.jcweaver.gameEngine.display.Animation;
import com.jcweaver.gameEngine.display.Display;
import com.jcweaver.gameEngine.display.Texture;
import com.jcweaver.gameEngine.display.Window;
import com.jcweaver.gameEngine.game.Button;
import com.jcweaver.gameEngine.game.Character;
import com.jcweaver.gameEngine.game.DebugBox;
import com.jcweaver.gameEngine.game.GameObject;
import com.jcweaver.gameEngine.game.Map;
import com.jcweaver.gameEngine.game.MapLoader;
import com.jcweaver.gameEngine.game.Scene;
import com.jcweaver.gameEngine.util.Keyboard;
import com.jcweaver.gameEngine.util.Mouse;
import com.jcweaver.gameEngine.util.Reader;

public class Menu extends Scene{
	double fps;
	GameObject floor;
	Character character;
	ArrayList<DebugBox> deb = new ArrayList<DebugBox>();
	Texture[] t;
	Texture te;
	Map thisone;
	Button but;
	
	public void init() {
		t = new Texture[1];
		t[0] = new Texture("/com/jcweaver/gameEngine/main/grass.jpg");
		te = new Texture("/com/jcweaver/gameEngine/main/brick.jpg");
		te.load();
		Animation ani = new Animation(t,.001f);
		
		character = new Character(1f,1f,.5f,.5f, ani);
		character.setCamFollowH(false);
		//floor = new GameObject(300,50,500,10,BodyType.STATIC,0,"/com/jcweaver/gameEngine/main/brick.jpg");
		//floor.load();
		Window.setVerticalMeters(5);
		Window.debugView();
		Display.setFPS(600);
		but = new Button(100f, 100f, 100f, 50f, "/com/jcweaver/gameEngine/main/brick.jpg");
		try{
		MapLoader m = new MapLoader();
		thisone = m.load("/com/jcweaver/gameEngine/main/test.txt");
		thisone.load();
		
		}catch(Exception e){e.printStackTrace();}
		
		}

	
	private double count = 0;
	private double spawn = .2;
	
	public void update(double delta) {
	fps = 1/delta;
	character.update(delta);
	
	
	if(but.isIn()&&Mouse.wasClicked()){
	}
	
	
	if(Keyboard.isDown(KeyEvent.VK_ESCAPE)){
		System.exit(0);
	}
	count+=delta;
	if(count>=spawn){
		DebugBox c = new DebugBox(Window.getMX(),Window.getMY(),.25f,.25f,BodyType.DYNAMIC,1);
		deb.add(c);
		count = 0;
		
	}
	

	}
	
	public void render(Graphics g) {
		g.drawString((fps)+"", 400, 100);
		//floor.draw(g);
		thisone.draw(g);
		character.draw(g);
		but.draw(g);
		for(DebugBox obj : deb){
			obj.draw(g);
		}
		//Window.draw(g, t, 1,1,1,1);
	}

}
