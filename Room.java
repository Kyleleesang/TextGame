/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;
import java.lang.String;
/**
 *
 * @author kleesans
 */
public class Room {
    String name = "Living Room";
    //instantiating an array at 9 makes 10 objects because of an object at array[0] so I made it with 9
    animal[] RoomAnimals = new animal[9];
    
   
    public Room(String n) {
        name = n;
        RoomAnimals = new animal[9];
    }

    
    
    public String toString() {
  for (int i = 0; i < 10; i++;){
          System.out.println(animal.toString());
    }
  }
  
    /**
     *
     * @param r
     * @param a
     */
    public void addAnimal(Room r,animal a) {
      for (int i = 0; i < 10; i++;){
      if (RoomAnimals[i] == null){
      RoomAnimals[i] = new animal(a);
      i++;
            }
        }
    }
}




