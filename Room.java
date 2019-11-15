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
    Room north;
    Room south;
    Room east;
    Room west;
    String name = "Living Room";
    String state;
    animal[] RoomAnimals = new animal[10];
    
   
    public Room(String n) {
        name = n;
        RoomAnimals = new animal[10];
    }

 
    
    
    public String toString() {
  for (int i = 0; i < 10; i++){
      if (RoomAnimals[i] != null) {
          System.out.println(RoomAnimals[i].toString());
      } 
      }
        return null;
    }
       
    public void addAnimal(Room r,String NewAnimal) {
      for (int i = 0; i < 10; i++){
      if (RoomAnimals[i] == null){
      RoomAnimals[i] = new animal(NewAnimal);
      i++;
            }
        }
    }
}




