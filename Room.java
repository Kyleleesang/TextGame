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
public class Room {
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    String description;
    private int state;
    String name;
    Creature[] RoomOccupants;
    private int LastCreatureIndex;
    
   
  public Room(String name, String description, String state) {
    setName(name);
    setDescription(description);
    if (state.equals("dirty")) {
      this.state = 0;
    }
    if (state.equals("half-dirty")) {
      this.state = 1;
    }
    if (state.equals("clean")) {
      this.state = 2;
    }
    this.RoomOccupants = new Creature[10];
    this.LastCreatureIndex = -1; 
  }
  
   public Room findNeighbor(String command) {
    if (command.equalsIgnoreCase("north"))
      return findNorth(); 
    if (command.equalsIgnoreCase("east"))
      return FindEast(); 
    if (command.equalsIgnoreCase("south"))
      return FindSouth(); 
    if (command.equalsIgnoreCase("west")) {
      return FindWest();
    }
    return null;
  }

  
    public Room findNorth() { 
      return this.north; 
  }
    
  public void setNorthRoom(Room north) { 
      this.north = north; 
  }


  
  public Room FindSouth(){ 
      return this.south; 
  }
  
  public void setDescription(String description) {
   this.description = description;   
  }
  
  public void setName(String name) {
   this.name = name;   
  }


  
  public void setSouthRoom(Room south) 
  { 
      this.south = south; 
  }


  
  public Room FindEast() 
  { 
      return this.east; 
  }


  
  public void setEastRoom(Room east) 
  { 
      this.east = east; 
  }


  
  public Room FindWest() 
  { 
      return this.west; 
  }


  
  public void setWestRoom(Room west) 
  
  { 
      this.west = west; 
  }

    
    public String toString() {
  for (int i = 0; i < 10; i++){
      if (RoomOccupants[i] != null) {
          System.out.println(RoomOccupants[i].toString());
      } 
      }
        return null;
    }
       

    
    
    
  public void reactCreaturesChangedRoom(Creature thisone, String command, PC player) {
    Creature temp = null;
    for (int i = 0; i <= getLastCreature(); i++) {
      if (!(getOccupants()[i] instanceof PC)) {
        temp = getOccupants()[i];
        getOccupants()[i].reactChangedRoomState(command, player);
        if (!temp.equals(getOccupants()[i])) {
          i--;
        }
      } 
    } 
  }
   public void addCreature(Creature thisone, int i) 
  { 
      this.RoomOccupants[i] = thisone; 
  }

  
  public void removeCreature(Creature thisone) {
    for (int i = 0; i <= this.LastCreatureIndex; i++) {
      if (this.RoomOccupants[i].getName().equalsIgnoreCase(thisone.getName())) {
        this.RoomOccupants[i] = null;
        for (int j = i; j < this.LastCreatureIndex; j++) {
          this.RoomOccupants[j] = this.RoomOccupants[j + 1];
        }
        this.LastCreatureIndex--;
      } 
    } 
  }

      public Creature[] getOccupants() { 
          return this.RoomOccupants; 
      }


  
  public void setOccupants(Creature[] RoomOccupants) { 
      this.RoomOccupants = RoomOccupants; 
  }

  
  public int getState() { 
      return this.state; 
  }
  
  public String getName() {
      return name;
  }


  
  public void setState(int state) { 
      this.state = state; 
  }


  
  public int getLastCreature() { 
      return this.LastCreatureIndex; }


  
  public void setLastCreatureIndex(int lastCreatureIndex) { 
      this.LastCreatureIndex = lastCreatureIndex; }
}






