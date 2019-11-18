  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomGame;
import java.lang.String;
import java.util.Random;


/**
 *
 * @author kleesans
 */



public abstract class Creature 
{
  Room currentRoom;
  String name;
  String description;
  
  public Creature(String name, String description) 
  {
    setName(name);
    setDescription(description);
  }

  
  public Creature() {}
  
  public String getClass() {
    if (this instanceof animal)
      return "animal"; 
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
      Room neighbor = this.currentRoom.findNeighbor("n");
      System.out.println("the north: " + neighbor.getName() + "room");
    } catch (NullPointerException e) {}
    
    try {
      Room room = this.currentRoom.findNeighbor("e");
      System.out.println("the east: " + room.getName() + "room");
    } catch (NullPointerException e) {}
    
    try {
      Room room = this.currentRoom.findNeighbor("s");
      System.out.println("the south: " + room.getName() + "room");
    } catch (NullPointerException e) {}
    
    try {
      Room room = this.currentRoom.findNeighbor("w");
      System.out.println("the west: " + room.getName() + " room");
    } catch (NullPointerException e) {}
    
    System.out.println("Creatures:");
    for (int i = 0; i <= getRoom().getLastCreature(); i++) {
      if (!this.currentRoom.getOccupants()[i].getName().equals(getName())) {
        Creature thisone = getRoom().getOccupants()[i];
        System.out.print(thisone.getName() + ", ");
        if (thisone instanceof PC) {
          System.out.print("PC, ");
        }
        if (thisone instanceof animal) {
          System.out.print("animal, ");
        }
        if (thisone instanceof NPC) {
          System.out.print("NPC, ");
        }
        System.out.print(thisone.getDescription() + " \n ");
      } 
    } 
  }

  
  public abstract void reactNewRoom(PC player);
  
  public abstract void reactGlad(PC player);
  
  public abstract void reactDiscontent(PC player);
  
  public final void reactChangedRoomState(String command, PC player) {
    if (this instanceof animal) {
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
    boolean move = tryToMove(direction, Boolean.valueOf(false), player);
    int counter = 0; while (true) {
      if ((!move ? 1 : 0) & ((counter < 4) ? 1 : 0)) {
        if (intDirection < 3) {
          intDirection++;
        } else {
          intDirection = 0;
        } 
        direction = directions[intDirection];
        move = tryToMove(direction, Boolean.valueOf(false), PC);
        counter++; continue;
      }  break;
    }  if (!move) {
      
      System.out.println(getName() + " the " + getClass() + " leaves room " + getRoom().getName() + " through the roof because there is nowhere to go!");
      getRoom().removeCreature(this);
      
    } 
  }


  
  public final boolean tryToMove(String direction, Boolean command, PC PC) {
    try {
      Room neighbor = getRoom().findNeighbor(direction);
      if (neighbor.getLastCreature() < 9) {

        
        neighbor.addCreature(this, neighbor.getLastCreature() + 1);
        neighbor.setLastCreatureIndex(neighbor.getLastCreature() + 1);
        System.out.println(getName() + " the " + getClass() + " leaves room " + getRoom().getName() + " to go to room " + neighbor.getName() + ", " + direction);
        
        getRoom().removeCreature(this);
        
        setCurrentRoom(neighbor);
        reactNewRoom(PC);
        return true;
      } 

      
      if (command.booleanValue() == true) {
        System.out.println("Room is full!");
        reactDiscontent(PC);
      } 
      return false;
    }
    catch (NullPointerException e) {
      
      if (command.booleanValue() == true) {
        System.out.println("There is no room to " + direction + "!");
        reactDiscontent(PC);
      } 
      return false;
    } 
  }



  
  public boolean clean(PC player, boolean command) {
    String Class = "";
    if (getRoom().getState() < 2) {
      getRoom().setState(getRoom().getState() + 1);
      
      if (this instanceof animal) {
        Class = "animal";
        if (command == true) {
          reactGlad(player);
        } 
      } 
      
      if (this instanceof NPC) {
        Class = "NPC";
        if (command == true) {
          reactDiscontent(player);
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
          reactGlad(player);
         
        } 
      } 
      
      if (this instanceof animal) {
        Class = "animal";
        if (command == true) {
          reactDiscontent(player);
          
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

  
  public Room getRoom() { 
          return this.currentRoom; 
  }
  
  public String getName() {
      return name;
  }
  
  public String getDescription (){
      return description;
  }
  
  public void setName(String name){
      this.name = name;
  }
  
  public void setDescription(String description){
      this.description = description;
  }


  
  public void setCurrentRoom(Room currentRoom) {
      this.currentRoom = currentRoom; 
  }
}
