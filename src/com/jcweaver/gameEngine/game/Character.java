package com.jcweaver.gameEngine.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import com.jcweaver.gameEngine.display.Animation;
import com.jcweaver.gameEngine.display.Window;
import com.jcweaver.gameEngine.physics.Physics;
import com.jcweaver.gameEngine.util.Keyboard;

public class Character {
public Animation ani;
private Body b;
private BodyDef bd;
private float w,h;
private float speed = 2;
private float jumpHeight = 4;
private boolean camFollowH = true;
private boolean camFollowV = true;
private boolean isGrounded;

public Character(float x, float y, float w, float h, Animation ani){
	
	
	this.w = w;
	this.h = h;
	bd = new BodyDef();
	bd.position = new Vec2(x,y);
	bd.type = BodyType.DYNAMIC; 
	
	PolygonShape sh = new PolygonShape();
	sh.setAsBox(w/2, h/2);
	
	b = Physics.getWorld().createBody(bd);
	b.createFixture(sh, 10);
	b.setFixedRotation(true);
	this.ani = ani;
	this.ani.load();
}
public void setAni(Animation ani){
	this.ani = ani;
}
public void setCamFollowH(boolean canf){
	camFollowH = canf;
}
public boolean setCamFollowH(){
	return camFollowH;
}
public void setCamFollowV(boolean canf){
	camFollowV = canf;
}
public boolean setCamFollowV(){
	return camFollowV;
}
public float getSpeed(){
	return speed;
}
public void setSpeed(float sp){
	speed = sp;
}
public float getJump(){
	return jumpHeight;
}
public void setJump(float j){
	jumpHeight = j;
}
public void update(double delta){
	isGrounded = false;
	isOnGround();
	if (camFollowH){
		Window.setOffsetx(-(b.getPosition().x-(Window.getHM()/2)));
	}
	if(camFollowV){
		Window.setOffsety(-(b.getPosition().y-(Window.getVM()/2)));
	}
	ani.update(delta);
	if(isGrounded){
	if(Keyboard.isDown(KeyEvent.VK_RIGHT) || Keyboard.isDown(KeyEvent.VK_D)){
		b.setLinearVelocity(new Vec2(speed,b.getLinearVelocity().y));
	}
	
	if(Keyboard.isDown(KeyEvent.VK_LEFT) || Keyboard.isDown(KeyEvent.VK_A)){
		b.setLinearVelocity(new Vec2(-speed,b.getLinearVelocity().y));
	}
	if(Keyboard.isDown(KeyEvent.VK_SPACE)){
		b.setLinearVelocity(new Vec2(b.getLinearVelocity().x,jumpHeight));
	}
	}
}
private void isOnGround(){
	Physics.getWorld().raycast(new Callback(), new Vec2(b.getPosition().x-(w/2),b.getPosition().y), new Vec2(b.getPosition().x-(w/2),b.getPosition().y-.1f-(h/2)));
	Physics.getWorld().raycast(new Callback(), new Vec2(b.getPosition().x+(w/2),b.getPosition().y), new Vec2(b.getPosition().x+(w/2),b.getPosition().y-.1f-(h/2)));
	Physics.getWorld().raycast(new Callback(), b.getPosition(), new Vec2(b.getPosition().x,b.getPosition().y-.1f-(h/2)));
	
}
class Callback implements RayCastCallback{

	public float reportFixture(Fixture arg0, Vec2 arg1, Vec2 arg2, float arg3) {
		isGrounded = true;
		return 0;
	}
	
}
public void unload(){
	ani.unload();
	Physics.getWorld().destroyBody(b);
}
public Animation getAni(){
	return ani;
}
public void draw(Graphics g){
	ani.draw(g, b.getPosition().x-(w/2), b.getPosition().y+(h/2), w, h,b.getAngle());
}

}
