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
    Animal[] RoomAnimals = new Animal[9];
    
   
    public Room(String n) {
        name = n;
        RoomAnimals = new Animal[9];
    }

    
    
    public String toString() {
  for (int i = 0; i < RoomAnimals.length; i++){
          System.out.println(Animal.toString());
    }
  }
  
    
    public void addAnimal(Animal a) {
      for (int i = 0; i < 10; i++){
      if (RoomAnimals[i] == null){
      RoomAnimals[i] = new Animal(a);
      i++;
            }
        }
    }
}




