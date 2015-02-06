package com.jcweaver.gameEngine.game;

import java.awt.Graphics;
import com.jcweaver.gameEngine.display.Texture;
import com.jcweaver.gameEngine.display.Window;
import com.jcweaver.gameEngine.util.Mouse;

public class Button {
private float x,y,w,h;
private boolean usingS = true;
private Texture t,hl, cl;


public Button(float x, float y, float w, float h, Texture t){
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
	this.t = t;
}
public Button(float x, float y, float w, float h, String tex){
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
	this.t = new Texture(tex);
}
public void load(){
	t.load();
}
public void unload(){
	t.unload();
}
public void setUsingS(boolean using){
	usingS = using;
}
public boolean isIn(){
	if(usingS)
		return (Mouse.getX()>x && Mouse.getX()<x+w && Mouse.getY() > y && Mouse.getY() < y+h);
	else
		return (Window.getMX()>x && Window.getMY()<x+w && Window.getMY() < y && Window.getMY() > y-h);
}
public void draw(Graphics g){
	if(usingS){
		if(isIn()){
			if(Mouse.isDown(1)){
				if(cl == null){
					t.draw(g, x, y, w, h, 3);
				}else{
					cl.draw(g, x, y, w, h, 3);
				}
				
			}else{
				if(hl==null){
					t.draw(g, x, y, w, h,3);
				}else{
					hl.draw(g, x, y, w, h, 3);
				}
				
			}
		}else{
			
			t.draw(g, x, y, w, h, 3);
		}
	}
	else{
		if(isIn()){
			if(Mouse.isDown(1)){
				if(cl == null){
					Window.draw(g, t, x, y, w, h, 0);
				}else{
					Window.draw(g, cl, x, y, w, h, 0);
				}
			}else{
				if(hl==null){
					Window.draw(g, t, x, y, w, h, 0);
				}else{
					Window.draw(g, hl, x, y, w, h, 0);
				}
			}
		}else{
			Window.draw(g, t, x, y, w, h, 0);
		}
	}
}

}
