  
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
public class Creature {
    String name;
    String description;
    Room current;
    
    public Creature(String n){
        name = n;
    }
    
    public String toString(){
        return name;
    }
//alerting animals about the room
    public void notification() {
   }
//method signature for peekroom since all animals can peek
    public void Peekroom(){

   }
//allows a creature to enter or exit a room
     public void Changeroom(Room r) {
         Creature.Room = Room r;
   }
//change room state
     public void Interact() {
   }
}
