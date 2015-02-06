package com.jcweaver.gameEngine.game;

import java.io.File;
import java.util.ArrayList;

import org.jbox2d.dynamics.BodyType;

import com.jcweaver.gameEngine.util.Reader;

public class MapLoader {
private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public Map load(String f){
		try {
			String[] lines = Reader.read(new File(getClass().getResource(f).toURI()));
			for(String s : lines){
				
				if(s.startsWith("//")){
					System.out.println(s);
				}else{
					String[] params = s.split(" ");
					if(params.length < 8 ){
						break;
					}
					BodyType bt;
					if(params[5].equals("STATIC")){
						bt = BodyType.STATIC;
					}else if(params[5].equals("DYNAMIC")){
						bt = BodyType.DYNAMIC;
					}else if(params[5].equals("KINEMATIC")){
						bt = BodyType.KINEMATIC;
					}else{
						break;
					}
					GameObject b = new GameObject(Float.parseFloat(params[1]),Float.parseFloat(params[2]),Float.parseFloat(params[3]),Float.parseFloat(params[4]),bt,Float.parseFloat(params[6]),params[7]);
					objects.add(b);
				}
				
			}
			
			
			
		} catch (Exception e) {e.printStackTrace();}
		
		return new Map(objects);
		
		
		
	}
	
	
}
