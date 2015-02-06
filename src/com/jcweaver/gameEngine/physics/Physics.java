package com.jcweaver.gameEngine.physics;


import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Physics {
	private static World world;
	private static Vec2 passes = new Vec2(6,3);
	public static void init(){
		world = new World(new Vec2(0f,-9.8f));
		world.setSleepingAllowed(true);
		world.setContinuousPhysics(false);
	}
	public static void setPasses(Vec2 s){
		passes = s;
	}
	public static Vec2 getPasses(){
		return passes;
	}
	public static World getWorld(){
		return world;
	}

}
