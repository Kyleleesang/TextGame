/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomGame;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author kleesans
 */
public class Main {
    public static void Main(String[] Args) {
            String name;
    String description;
    Room Room;
    int Respect = 40;
    

    
  
    }
    public void play(Scanner s) {
    	String line;
    	boolean keep_going = true;
    	while(keep_going == true) {
    		line = s.next();
    		if(s.equals("help")) {
        		System.out.println(" type look to look around the Room");
        		System.out.println("type clean to clean the Room");
        		System.out.println("type a direction north east west south to go somewhere");
        		System.out.println(" type exit or quit to quit game");
        	}
    		else if(s.equals("clean")) {
        		String state = Room.state;
        		if(state.equals("dirty")) {
        			Room.state = "half-dirty";
        		}
            if(state.equals("half-dirty")) {
            	Room.state= "clean";
        			
        		}
            if(state.equals("clean")) {
    			
    		}
        	}
    		else if(s.equals("ditry")) {
    		String state = Room.state;
    		if(state.equals("dirty")) {
    			
    		}
        if(state.equals("half-dirty")) {
        	Room.state= "dirty";
    			
    		}
        if(state.equals("clean")) {
        	Room.state = "half-dirty";
			
		}
    		
    	}
    		else	if(s.equals("look")) {
    		Room.toString();
    		
    	}
    		else if(s.equals("north") || s.equals("west") || s.equals("east") || s.equals("south")) {
    		if(s.equals("north")) {
    			Room = Room.north;
    		}
if(s.equals("east")) {
    			currentRoom = Room.east;
    		}
if(s.equals("west")) {
	Room = Room.west;
}
if(s.equals("south")) {
	Room = Room.south;
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
        
    }
}
