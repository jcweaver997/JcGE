package com.jcweaver.gameEngine.game;

import java.awt.Graphics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import com.jcweaver.gameEngine.display.Window;
import com.jcweaver.gameEngine.physics.Physics;

public class DebugBox{
	public Body b;
	public BodyDef bd;
	public PolygonShape ps;
	public float w,h;
	public DebugBox(float x, float y, float w, float h,BodyType bt, float mass){
		this.w = w;
		this.h = h;
		bd = new BodyDef();
		bd.position = new Vec2(x,y);
		bd.type = bt; 
		bd.fixedRotation = false;
		ps = new PolygonShape();
		ps.setAsBox(w/2, h/2);
		b = Physics.getWorld().createBody(bd);
		b.createFixture(ps, mass);
		
		
		
	}
	public Body getBody(){ 
		return b;
	}
	public BodyDef getBodyDef(){
		return bd;
	}
	
	public void draw(Graphics g){
		//Window.draw(g,(int)b.getPosition().x,(int)b.getPosition().y,w,h);
		Vec2[] vertices = ps.getVertices().clone();
		for(int i = 0; i < 4; i++){
			vertices[i] = b.getWorldPoint(vertices[i]);
		}
		Window.draw(g, vertices);
		//Window.draw(g,(int)b.getPosition().x-(w/2),(int)b.getPosition().y+(h/2),w,h, b.getAngle());

	}
}
