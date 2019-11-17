/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomGame;
import java.lang.String;


/**
 *
 * @author kleesans
 */
public class animal extends Creature {
    String name;
    Room room;
    
    public animal(String name, String description){
       super(name,description);
    }

    
    public String toString(){
        return name;
    }
    
}

