package com.jcweaver.gameEngine.game;

import java.awt.Graphics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import com.jcweaver.gameEngine.display.Texture;
import com.jcweaver.gameEngine.display.Window;
import com.jcweaver.gameEngine.physics.Physics;

public class GameObject {
	public Texture t;
	public Body b;
	public BodyDef bd;
	public BodyType bt;
	public PolygonShape sh;
	public float w,h,mass;
	public boolean isLoaded;
	
	
	public GameObject(float x, float y, float w, float h,BodyType bt, float mass, String tex){
		init(x,y,w,h,bt,mass,tex);
		
	}
	public GameObject(float x, float y, float w, float h,BodyType bt, float mass, Texture t){
		init(x,y,w,h,bt,mass,t);
	}
	protected void init(float x, float y, float w, float h,BodyType bt, float mass, String tex){
		this.w = w;
		this.h = h;
		this.mass = mass;
		this.bt = bt;
		bd = new BodyDef();
		bd.position = new Vec2(x,y);
		bd.type = bt; 
		sh = new PolygonShape();
		sh.setAsBox(w/2, h/2);
		t = new Texture(tex);
		
	}
	protected void init(float x, float y, float w, float h,BodyType bt, float mass, Texture t){
		this.w = w;
		this.h = h;
		this.mass = mass;
		this.bt = bt;
		bd = new BodyDef();
		bd.position = new Vec2(x,y);
		bd.type = bt; 
		
		sh = new PolygonShape();
		sh.setAsBox(w/2, h/2);
		
		
		b = Physics.getWorld().createBody(bd);
		b.createFixture(sh, mass);
		this.t = t;
	}
	public void load(){
		isLoaded=true;
		b = Physics.getWorld().createBody(bd);
		b.createFixture(sh, mass);
		t.load();
	}
	public void unload(){
		isLoaded=false;
		t.unload();
		Physics.getWorld().destroyBody(b);
	}
	public Body getBody(){ 
		return b;
	}
	public void setTexture(Texture t){
		this.t = t;
	}
	public BodyDef getBodyDef(){
		return bd;
	}
	
	public void draw(Graphics g){
		Vec2[] vertices = sh.getVertices().clone();
		for(int i = 0; i < 4; i++){
			vertices[i] = b.getWorldPoint(vertices[i]);
		}
		//Window.draw(g, t, vertices);
		
		
		Window.draw(g,t,b.getPosition().x-(w/2f),b.getPosition().y+(h/2f),w,h, b.getAngle());

	}
	
	
}
