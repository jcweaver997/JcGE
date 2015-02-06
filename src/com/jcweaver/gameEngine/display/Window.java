package com.jcweaver.gameEngine.display;

import java.awt.Graphics;

import org.jbox2d.common.Vec2;

import com.jcweaver.gameEngine.util.Mouse;

public class Window {
	private static float scale = 1;
	private static float offsetx, offsety;
	public static void setScale(float scal){
		scale = scal;
	}
	public static void setOffsetx(float offset){
		offsetx = offset;
	}
	public static void setVerticalMeters(float m){
		scale = Display.getCanvas().getHeight()/m;
	}
	public static float getHM(){
		return Display.getCanvas().getWidth()/scale;
	}
	public static float getVM(){
		return Display.getCanvas().getHeight()/scale;
	}
	public static void setOffsety(float offset){
		offsety = offset;
	}
	public static float getScale(){
		return scale;
	}
	public static float getOffsetx(){
		return offsetx;
	}
	public static float getOffsety(){
		return offsety;
	}
	public static float getMX(){
		return Mouse.getX()/scale-offsetx;
		
	}
	public static float getMY(){
		return (Display.getCanvas().getHeight()-Mouse.getY())/scale-offsety;
		
	}
	public static float getSX(){
		return (offsetx+(Display.getCanvas().getWidth()/scale))-offsetx;
	}
	public static float getSY(){
		return (Display.getCanvas().getHeight()/scale-offsety)-offsety;
	}
	public static void draw(Graphics g, float x, float y, float w, float h, float angle){
		
		x = ((x+offsetx)*scale);
		y = (int)(-1*(((y+offsety)*scale)-Display.getCanvas().getHeight()));
		w*=scale;
		h*=scale;
		
		int dangle = -(int)Math.toDegrees(angle);
		int mangle = dangle%90;
		
		Vec2 topright = new Vec2((x+(w/2f))-((mangle/90f)*w),y+(h/2f));
		Vec2 botleft = new Vec2((x-(w/2f))+((mangle/90f)*w),y-(h/2f));
		Vec2 topleft = new Vec2(x-(w/2f),(y+(h/2f))-((mangle/90.0f)*h) );
		Vec2 botright = new Vec2(x+(w/2f),(y-(h/2f))+((mangle/90.0f)*h) );
		
		
		int[] xpoints = {
				(int)topright.x,
				(int)topleft.x,
				(int)botleft.x,
				(int)botright.x
				
				};
		int[] ypoints = {
				(int)topright.y,
				(int)topleft.y,
				(int)botleft.y,
				(int)botright.y
				
		};
		
		
		g.drawPolygon(xpoints, ypoints, 4);
		
		
		//g.fillRect((int)((x+offsetx)*scale), (int)(-1*(((y+offsety)*scale)-Display.getCanvas()().getHeight())), (int)(w*scale), (int)(h*scale));
		
	}
	
	public static void draw(Graphics g, Vec2[] ar){
		int[] xpoints = {
				(int) ((ar[0].x+offsetx)*scale),
				(int) ((ar[1].x+offsetx)*scale),
				(int) ((ar[2].x+offsetx)*scale),
				(int) ((ar[3].x+offsetx)*scale)
		};
		int[] ypoints = {
				(int)(-1*(((ar[0].y+offsety)*scale)-Display.getCanvas().getHeight())),
				(int)(-1*(((ar[1].y+offsety)*scale)-Display.getCanvas().getHeight())),
				(int)(-1*(((ar[2].y+offsety)*scale)-Display.getCanvas().getHeight())),
				(int)(-1*(((ar[3].y+offsety)*scale)-Display.getCanvas().getHeight()))
				
		};
		g.drawPolygon(xpoints, ypoints, 4);
	}
	
	public static void draw(Graphics g, Texture t, float x, float y, float w, float h, float angle){
		
		t.draw(g, ((x+offsetx)*scale), (-1f*(((y+offsety)*scale)-Display.getCanvas().getHeight())), (w*scale), (h*scale), angle);
		
	}
	public static void debugView(){
		Vec2 topleft,topright,botleft,botright;
		topleft = new Vec2(offsetx,(Display.getCanvas().getHeight()/scale-offsety));
		topright = new Vec2(offsetx+(Display.getCanvas().getWidth()/scale),(Display.getCanvas().getHeight()/scale-offsety));
		botleft = new Vec2(offsetx,offsety);
		botright = new Vec2(offsetx+(Display.getCanvas().getWidth()/scale),offsety);
		System.out.print("1: ");
		System.out.println(topleft.x +", "+topleft.y);
		System.out.print("2: ");
		System.out.println(topright.x +", "+topright.y);
		System.out.print("3: ");
		System.out.println(botright.x +", "+botright.y);
		System.out.print("4: ");
		System.out.println(botleft.x +", "+botleft.y);
		System.out.println(Display.getCanvas().getHeight());
		System.out.println(-1*(((1+0)*scale)-Display.getCanvas().getHeight()));
	}
}
