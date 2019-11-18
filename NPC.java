/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomGame;

/**
 *
 * @author kleesans
 */
public class NPC extends Creature {
    

  public NPC(String name, String description) {
      super(name, description); }

  
  public void grumble(PC pc) {
    pc.lowerRespect();
    System.out.println(getName() + " the NPC grumbles at  you.");
  }
  
  public void smile(PC pc) {
    pc.raiseRespect();
    System.out.println(getName() + " the NPC smiles at you.");
  }

  
  public void reactNewRoom(PC pc) {
    if (getRoom().getState() == 2) {
      dirty(pc, false);
    }
  }


  
  public void reactGlad(PC pc) { 
      smile(pc); 
  }



  
  public void reactDiscontent(PC pc) {
      grumble(pc); 
  }
}

    

