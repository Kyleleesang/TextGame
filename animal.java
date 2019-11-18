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
public abstract class animal extends Creature {
    String name;
    Room room;
    
    public animal(String name, String description){
       super(name,description);
    }
    
    public void growl(PC pc){
        pc.lowerRespect();
        System.out.println(getName() + "The animal growls at you");
    }
    public void lickFace(PC pc){
        pc.raiseRespect();
        System.out.println(getName() + "The animal licks you");
    }

    
    public String toString(){
        return name;
    }
    
      public void reactNewRoom(PC pc) {
if (getRoom().getState()==0){
      clean(pc, false);
  }
  }
  
  public void reactGlad(PC pc) {
  lickFace(pc);
  }
  
  public void reactDiscontent(PC pc) {
  growl(pc);
    
}

