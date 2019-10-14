/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;
import java.lang.String;
import java.util.Arrays;
/**
 *
 * @author kleesans
 */
public class Room {
    String name;
    String description;
    String state;
    Creature[] RoomAnimals = new Creature[10];
    Room north;
    Room south;
    Room west;
    Room east;
    
   
    public Room(String n) {
        name = n;
        RoomAnimals = new Creature[10];
    }

    
    
    public String toString() {
  for (int i = 0; i < RoomAnimals.length; i++){
          System.out.println(Animal.toString([i]));
    }
  }
  
    
    public void addAnimal(Animal a) {
      for (int i = 0; i < 10; i++){
      if (RoomAnimals[i] == null){
      RoomAnimals[i] = Animal(a);
      i++;
            }
        }
    }
}




