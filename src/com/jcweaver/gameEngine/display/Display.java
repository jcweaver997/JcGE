package com.jcweaver.gameEngine.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.jcweaver.gameEngine.game.Scene;
import com.jcweaver.gameEngine.physics.Physics;
import com.jcweaver.gameEngine.util.Keyboard;
import com.jcweaver.gameEngine.util.Mouse;

public class Display {

	private static JFrame frame;
	private static GraphicsConfiguration gc;
	private static BufferStrategy bs;
	private static VolatileImage vimg;
	private static ArrayList<Scene> scenes;
	private static int curScene;
	private static double inverseFPS = 1/60.0;
	private static boolean isActive;
	private static Color clearColor = Color.WHITE;
	private static GLCanvas canvas;
	
	public Display(){
		frame = new JFrame();
		frame.setSize(720, 480);
		setup();
	}

	public Display(String title, int w, int h,boolean borderless){
		frame = new JFrame(title);
		frame.setSize(w, h);
		frame.setUndecorated(borderless);
		setup();
		
	}
	
	private void setup(){
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scenes = new ArrayList<Scene>();
		
		Mouse m = new Mouse();
	
		canvas = new GLCanvas();
		canvas.addMouseListener(m);
		canvas.addMouseMotionListener(m);
		canvas.addKeyListener(new Keyboard());
		frame.add(canvas);
		Physics.init();
		
	}
	public static void setScene(int sce){
		curScene = sce;
		scenes.get(curScene).init();
	}
	public static JFrame getFrame(){
		return frame;
	}
	
	public static void setClearColor(Color c){
		clearColor = c;
	}
	
	public static void setIsActive(boolean act){
		
		if(act && !isActive){
		isActive = true;
		}
		if(!act){
			isActive = false;
		}
		
	}
	
	public static void setFPS(int des){
		inverseFPS = 1.0/des;
		
	}
	public static GLCanvas getCanvas(){
		return canvas;
	}
	public static int addScene(Scene s){
		scenes.add(s);
		return scenes.size()-1;
	}
	
	public static void create(){
		frame.setVisible(true);
		gc = frame.getGraphicsConfiguration();
		isActive = true;
		vimg = gc.createCompatibleVolatileImage(frame.getWidth(), frame.getHeight());
		System.out.println(gc.getDevice().toString());
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		
		scenes.get(curScene).init();
		
		loop();

	}
	
	
	private static long bef,aft;
	private static double delta;
	private static long dif;
	private static void loop(){
		
		while(isActive){
			bef = System.nanoTime();


		
		update(delta);
		Physics.getWorld().step((float)delta, (int)Physics.getPasses().x,(int)Physics.getPasses().y);
		render(vimg.getGraphics());
		bs.getDrawGraphics().drawImage(vimg, 0, 0, frame.getWidth(), frame.getHeight(), null);
		bs.show();
		bs.getDrawGraphics().dispose();

		
		
		aft = System.nanoTime();
		dif = aft-bef;
		delta = dif/(1000000.0*1000);
		if(delta < inverseFPS){
			try{
			Thread.sleep((long) (1000*((inverseFPS)-delta)));
			delta = inverseFPS;
			}catch(Exception e){e.printStackTrace();}
		}
		
		
	}
	}
	
	
	private static void update(double delta){
		scenes.get(curScene).update(delta);
	}
	
	private static void render(Graphics g){
		g.setColor(clearColor);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.BLACK);
		scenes.get(curScene).render(g);
		
		
	}
	
}
