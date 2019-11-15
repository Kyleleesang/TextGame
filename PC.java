/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomGame;
import java.util.Scanner;

/**
 *
 * @author kleesans
 */
public class PC extends Creature{
    String name;
    String description;
    Room room;
    int Respect = 40;
    
    public PC(String n){
    	super(n);
        name = n;
    }
    
    public String toString(){
        return name;
    }
    public void play(Scanner s) {
    	String line;
    	boolean keep_going = true;
    	while(keep_going == true) {
    		line = s.next();
    		if(s.equals("help")) {
        		System.out.println(" type look to look around the room");
        		System.out.println("type clean to clean the room");
        		System.out.println("type a direction north east west south to go somewhere");
        		System.out.println(" type exit or quit to quit game");
        	}
    		else if(s.equals("clean")) {
        		String state = room.state;
        		if(state.equals("dirty")) {
        			room.state = "half-dirty";
        		}
            if(state.equals("half-dirty")) {
            	room.state= "clean";
        			
        		}
            if(state.equals("clean")) {
    			
    		}
        	}
    		else if(s.equals("ditry")) {
    		String state = room.state;
    		if(state.equals("dirty")) {
    			
    		}
        if(state.equals("half-dirty")) {
        	room.state= "dirty";
    			
    		}
        if(state.equals("clean")) {
        	room.state = "half-dirty";
			
		}
    		
    	}
    		else	if(s.equals("look")) {
    		room.toString();
    		
    	}
    		else if(s.equals("north") || s.equals("west") || s.equals("east") || s.equals("south")) {
    		if(s.equals("north")) {
    			room = room.north;
    		}
if(s.equals("east")) {
    			room= room.east;
    		}
if(s.equals("west")) {
	room = room.west;
}
if(s.equals("south")) {
	room= room.south;
}
    		
    	}
    		else if(s.equals(("exit")) ||  s.equals("quit")){
    		System.out.println("Goodbye");
    		System.exit(0);
    	}
    		else {
    			System.out.println("could not understand please try again");
    		}
    		
    	}
    	
    }
    
}