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
public class Animal extends Creature{
    String name;
    Room room;
    
    public Animal(String n){
        name = n;
    }
    
    public String toString(){
        return name;
    }
    
}

