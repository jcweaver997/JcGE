package com.jcweaver.gameEngine.util;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{
	private static int mx,my;
	private static boolean[] isDown = new boolean[16];
	private static boolean wasDown = false;
	
	
	
	
	
	public static boolean isDown(int button){
		
		return isDown[button];
	}
	public static boolean wasClicked(){
		boolean answer = !wasDown && isDown[1];
		wasDown=true;
		return answer;
	}
	public static int getX(){
		return mx;
	}
	public static int getY(){
		return my;
	}
	public static Point getPos(){
		return new Point(mx,my);
	}
	public void mouseDragged(MouseEvent arg0) {
		mx = arg0.getX();
		my = arg0.getY();
	}

	public void mouseMoved(MouseEvent arg0) {
		mx = arg0.getX();
		my = arg0.getY();
	}

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {
		wasDown=false;
		isDown[arg0.getButton()] = true;
	}

	public void mouseReleased(MouseEvent arg0) {
		isDown[arg0.getButton()] = false;
	}

}
