  
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
public abstract class Creature {
 
    Room current;
    
    public Creature(String name, String description){
        name = n;
    }
    
    public String toString(){
        return name;
    }
//alerting animals about the room
    public void notification() {
   }
//method signature for peekroom since all animals can peek

//allows a creature to enter or exit a room
//change room state
     public void Interact() {
   }
     public Room getRoom() {
        return this.current;
    }
     public void SetRoom(Room current) {
          this.current = current;
      }
}


public abstract class Creature 
{
  Room currentRoom;
  
  public Creature(String name, String description) 
  {
    setName(name);
    setDescription(description);
  }

  
  public Creature() {}
  
  public String getClass() {
    if (this instanceof Animal)
      return "Animal"; 
    if (this instanceof NPC) {
      return "NPC";
    }
    if (this instanceof PC) {
      return "PC";
    }
    return null;
  }

  
  public void PeekRoom() {
    System.out.println("\n Room: " + getRoom().getName() + ", " + getRoom().getDescription() + ", " + getRoom().getState());
    System.out.println("Doors to:");
    
    try {
      Room neighbor = this.currentRoom.getNeighbor("n");
      System.out.println("the north: " + neighbor.getName() + "room");
    } catch (NullPointerException e) {}
    
    try {
      Room room = this.currentRoom.getNeighbor("e");
      System.out.println("the east: " + room.getName() + "room");
    } catch (NullPointerException e) {}
    
    try {
      Room room = this.currentRoom.getNeighbor("s");
      System.out.println("the south: " + room.getName() + "room");
    } catch (NullPointerException e) {}
    
    try {
      Room room = this.currentRoom.getNeighbor("w");
      System.out.println("the west: " + room.getName() + " room");
    } catch (NullPointerException e) {}
    
    System.out.println("Creatures:");
    for (int i = 0; i <= getRoom().getLastCreatureIndex(); i++) {
      if (!this.currentRoom.getOccupants()[i].getName().equals(getName())) {
        Creature thisone = getRoom().getOccupants()[i];
        System.out.print(crit.getName() + ", ");
        if (thisone instanceof PC) {
          System.out.print("PC, ");
        }
        if (thisone instanceof Animal) {
          System.out.print("Animal, ");
        }
        if (thisone instanceof NPC) {
          System.out.print("NPC, ");
        }
        System.out.print(crit.getDescription() + " \n ");
      } 
    } 
  }

  
  public abstract void reactNewRoom(PC player);
  
  public abstract void reactGlad(PC player);
  
  public abstract void reactDiscontent(PC player);
  
  public final void reactChangedRoomState(String command, PC player) {
    if (this instanceof Animal) {
      if (command.equals("clean")) {
        reactGlad(player);
      }
      if (command.equals("dirty")) {
        reactDiscontent(player);
      }
      if (this.currentRoom.getState() == 0) {
        tryToLeaveRoom(player);
      }
    } 
    if (this instanceof NPC) {
      if (command.equals("clean")) {
        reactDiscontent(player);
      }
      if (command.equals("dirty")) {
        reactGlad(player);
      }
      if (this.currentRoom.getState() == 2) {
        tryToLeaveRoom(player);
      }
    } 
  }

  
  private void tryToLeaveRoom(PC player) {
    String[] directions = { "north", "east", "south", "west" };
    
    Random generator = new Random();
    int intDirection = generator.nextInt(4);
    String direction = directions[intDirection];
    boolean move = tryToMove(direction, Boolean.valueOf(false), pc);
    int counter = 0; while (true) {
      if ((!move ? 1 : 0) & ((counter < 4) ? 1 : 0)) {
        if (intDirection < 3) {
          intDirection++;
        } else {
          intDirection = 0;
        } 
        direction = directions[intDirection];
        move = tryToMove(direction, Boolean.valueOf(false), pc);
        counter++; continue;
      }  break;
    }  if (!move) {
      
      System.out.println(getName() + " the " + getClass() + " leaves room " + getRoom().getName() + " through the roof because there is nowhere to go!");
      getRoom().removeCreature(this);
      getRoom().reactCrittersRoof(pc);
    } 
  }


  
  public final boolean tryToMove(String direction, Boolean command, PC pc) {
    try {
      Room neighbor = getRoom().getNeighbor(direction);
      if (neighbor.LastCreature() < 9) {

        
        neighbor.addCreature(this, neighbor.getLastCreature() + 1);
        neighbor.setLastCreatureIndex(neighbor.getLastCreature() + 1);
        System.out.println(getName() + " the " + getCreatureClass() + " leaves room " + getRoom().getName() + " to go to room " + neighbor.getName() + ", " + direction);
        
        getRoom().removeCreature(this);
        
        setCurrentRoom(neighbor);
        reactNewRoom(pc);
        return true;
      } 

      
      if (command.booleanValue() == true) {
        System.out.println("Room is full!");
        reactDiscontent(pc);
      } 
      return false;
    }
    catch (NullPointerException e) {
      
      if (command.booleanValue() == true) {
        System.out.println("There is no room to " + direction + "!");
        reactDiscontent(pc);
      } 
      return false;
    } 
  }



  
  public boolean clean(PC player, boolean command) {
    String Class = "";
    if (getRoom().getState() < 2) {
      getRoom().setState(getRoom().getState() + 1);
      
      if (this instanceof Animal) {
        Class = "Animal";
        if (command == true) {
          reactGlad(pc);
        } 
      } 
      
      if (this instanceof NPC) {
        Class = "NPC";
        if (command == true) {
          reactDiscontent(pc);
        } 
      } 
      
      if (this instanceof PC) {
        Class = "PC";
      }
      System.out.println(getName() + " the " + Class + " cleans the room " + getRoom().getName() + ". The room's state is now " + getRoom().getState());
      return true;
    } 
    return false;
  }
  
  public boolean dirty(PC player, boolean command) {
    String Class = "";
    if (getRoom().getState() > 0) {
      getRoom().setState(getRoom().getState() - 1);
      if (this instanceof NPC) {
        Class = "NPC";
        if (command == true) {
          reactGlad(pc);
          reactGlad(pc);
        } 
      } 
      
      if (this instanceof Animal) {
        Class = "Animal";
        if (command == true) {
          reactDiscontent(pc);
          reactDiscontent(pc);
        } 
      } 
      
      if (this instanceof PC) {
        Class = "PC";
      }
      System.out.println(getName() + " the " + Class + " dirties the room " + getRoom().getName() + ". The room's state is now " + getRoom().getState());
      return true;
    } 
    return false;
  }

  
  public Room getRoom() { return this.currentRoom; }


  
  public void setCurrentRoom(Room currentRoom) 
  {
      this.currentRoom = currentRoom; }
}
