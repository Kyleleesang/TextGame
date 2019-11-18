/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomGame;
import java.util.Scanner;
import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.xml.parsers.SAXParser;
import java.io.File;
import javax.xml.parsers.SAXParserFactory;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author kleesans
 */

public class Main extends DefaultHandler
{
    static Room[] Rooms;
    static int roomCounter;
    static int CreatureNumber;
    static Creature[] tempCreatures;
    static PC player;
    
 public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException  {
 BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
      
 System.out.println("type the name of your input file");
 String Inputline = Input.readLine();
 SAXParserFactory fpety = SAXParserFactory.newInstance();
 DefaultHandler defaultone = new Main();
        SAXParser parser = fpety.newSAXParser();
        parser.parse(new File(Inputline), defaultone);
        printHelp();
        System.out.println("Type command: ");
        for (Inputline = Input.readLine(); !Inputline.equalsIgnoreCase("exit"); Inputline = Input.readLine()) {
             {
                if (!Inputline.equalsIgnoreCase("help")) {
                     String[] lines = Inputline.split(":");
                    if (lines.length == 1) {
                         String command = lines[0];
                        executeCommand(Main.player, command);
                    }
                    else if (lines.length == 2) {
                         String command = lines[1];
                        try {
                             Creature commandCrit = commandCreature(lines[0]);
                            executeCommand(commandCrit, command);
                        }
                        catch (NullPointerException e) {
                            System.out.println("There is no creature of name " + lines[0] + " in the " + Main.player.getRoom().getName() + " room!");
                        }
                    }
                    else {
                        System.out.println("This is not a valid command! Commands can contain only one or two words separated by a colon (:).");
                    }
                    System.out.println("After the execution of this command, the PC's respect is " + Main.player.getRespect());
                    if (Main.player.getRespect() >= 80) {
                        System.out.println("Congratulations! You win the game in praise!");
                    }
                    else {
                        if (Main.player.getRespect() > 0) {
                            
                        }
                        System.out.println("You end the game in disgrace. Try to play better.");
                    }
                    return;
                }
                printHelp();
            }
            System.out.println("Type new command: ");
        }
        System.out.println("Goodbye!");
    }
    
    private static Creature commandCreature( String pet) {
        for (int i = 0; i < Main.player.getRoom().getOccupants().length; ++i) {
            if (Main.player.getRoom().getOccupants()[i].getName().equals(pet)) {
                return Main.player.getRoom().getOccupants()[i];
            }
        }
        return null;
    }
    
    private static void executeCommand( Creature thisone,  String command) {
        if (command.equalsIgnoreCase("n") || command.equalsIgnoreCase("e") || command.equalsIgnoreCase("s") || command.equalsIgnoreCase("w")) {
             boolean move = thisone.tryToMove(command, true, Main.player);
        }
        else if (command.equalsIgnoreCase("PeekRoom")) {
            thisone.PeekRoom();
        }
        else if (command.equalsIgnoreCase("clean")) {
            if (thisone.clean(Main.player, true)) {
                Main.player.getRoom().reactCreaturesChangedRoom(thisone, command, Main.player);
            }
        }
        else if (command.equalsIgnoreCase("dirty")) {
            if (thisone.dirty(Main.player, true)) {
                Main.player.getRoom().reactCreaturesChangedRoom(thisone, command, Main.player);
            }
        }
        else {
            System.out.println(command + " is not a valid action that creatures can perform!");
        }
    }
    
    private static void printHelp() {

        System.out.println("Type clean or dirty to clean or dirty the room");
        System.out.println("To PeekRoom, simply type in the word PeekRoom");
        System.out.println("To make a creature clean or dirty the current room, type creature clean or creature dirty");
        System.out.println("To make a creature that is in the current room PeekRoom around the room, type PeekRoom");
        System.out.println("To see this menu, type help");
        System.out.println("To exit the game, type exit ");
    }
    
  
    public void endElement( String namespaceURI,  String placeholder,  String Query) throws SAXException {
        if (Query.equalsIgnoreCase("room")) {
            Main.Rooms[Main.roomCounter].setOccupants(Main.tempCreatures);
            Main.Rooms[Main.roomCounter].setLastCreatureIndex(Main.CreatureNumber);
        }
    }
    
    
    public void startElement( String namespaceURI,  String placeholder,  String Query,  Attributes attrs) throws SAXException {
        if (Query.equalsIgnoreCase("room")) {
            Main.tempCreatures = new Creature[10];
            String name = null;
            String description = null;
            String state = null;
            String north = null;
            String east = null;
            String south = null;
            String west = null;
            Main.roomCounter++;
            Main.CreatureNumber = -1;
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); ++i) {
                    if (attrs.getQName(i).equals("name")) {
                        name = attrs.getValue(i);
                    }
                    if (attrs.getQName(i).equals("description")) {
                        description = attrs.getValue(i);
                    }
                    if (attrs.getQName(i).equals("state")) {
                        state = attrs.getValue(i);
                    }
                    if (attrs.getQName(i).equals("north")) {
                        north = attrs.getValue(i);
                    }
                    if (attrs.getQName(i).equals("east")) {
                        east = attrs.getValue(i);
                    }
                    if (attrs.getQName(i).equals("south")) {
                        south = attrs.getValue(i);
                    }
                    if (attrs.getQName(i).equals("west")) {
                        west = attrs.getValue(i);
                    }
                }
            }
            if (name == null || state == null || description == null) {
                throw new SAXException("Room in input file not formatted properly");
            }
            Main.Rooms[Main.roomCounter] = new Room(name, description, state);
            Room neighbor = null;
            if (north != null) {
                neighbor = this.searchNeighbor(north);
                if (neighbor != null) {
                    Main.Rooms[Main.roomCounter].setNorthRoom(neighbor);
                    neighbor.setSouthRoom(Main.Rooms[Main.roomCounter]);
                    neighbor = null;
                }
            }
            if (east != null) {
                neighbor = this.searchNeighbor(east);
                if (neighbor != null) {
                    Main.Rooms[Main.roomCounter].setEastRoom(neighbor);
                    neighbor.setWestRoom(Main.Rooms[Main.roomCounter]);
                    neighbor = null;
                }
            }
            if (south != null) {
                neighbor = this.searchNeighbor(south);
                if (neighbor != null) {
                    Main.Rooms[Main.roomCounter].setSouthRoom(neighbor);
                    neighbor.setNorthRoom(Main.Rooms[Main.roomCounter]);
                    neighbor = null;
                }
            }
            if (west != null) {
                neighbor = this.searchNeighbor(west);
                if (neighbor != null) {
                    Main.Rooms[Main.roomCounter].setWestRoom(neighbor);
                    neighbor.setEastRoom(Main.Rooms[Main.roomCounter]);
                    neighbor = null;
                }
            }
        }
        if (Query.equalsIgnoreCase("animal")) {
            String name = null;
            String description = null;
            ++Main.CreatureNumber;
            if (attrs != null) {
                for (int j = 0; j < attrs.getLength(); ++j) {
                    if (attrs.getQName(j).equals("name")) {
                        name = attrs.getValue(j);
                    }
                    if (attrs.getQName(j).equals("description")) {
                        description = attrs.getValue(j);
                    }
                }
            }
            if (name == null || description == null) {
                throw new SAXException("Animal in input file not formatted properly");
            }
            (Main.tempCreatures[Main.CreatureNumber] = new animal(name, description)).setCurrentRoom(Main.Rooms[Main.roomCounter]);
        }
        if (Query.equalsIgnoreCase("NPC")) {
            String name = null;
            String description = null;
            ++Main.CreatureNumber;
            if (attrs != null) {
                for (int j = 0; j < attrs.getLength(); ++j) {
                    if (attrs.getQName(j).equals("name")) {
                        name = attrs.getValue(j);
                    }
                    if (attrs.getQName(j).equals("description")) {
                        description = attrs.getValue(j);
                    }
                }
            }
            if (name == null || description == null) {
                throw new SAXException("NPC in input file not formatted properly");
            }
            (Main.tempCreatures[Main.CreatureNumber] = new NPC(name, description)).setCurrentRoom(Main.Rooms[Main.roomCounter]);
        }
        if (Query.equalsIgnoreCase("PC")) {
            String name = null;
            String description = null;
            Main.CreatureNumber++;
            if (attrs != null) {
                for (int j = 0; j < attrs.getLength(); ++j) {
                    if (attrs.getQName(j).equals("name")) {
                        name = attrs.getValue(j);
                    }
                    if (attrs.getQName(j).equals("description")) {
                        description = attrs.getValue(j);
                    }
                }
            }
            if (name == null || description == null) {
                throw new SAXException("PC in input file not formatted properly");
            }
            Main.tempCreatures[Main.CreatureNumber] = new PC(name, description);
            (Main.player = (PC)Main.tempCreatures[Main.CreatureNumber]).setCurrentRoom(Main.Rooms[Main.roomCounter]);
        }
    }
    
    private Room searchNeighbor(String neighbor) {
        for (int i = 0; i < Main.roomCounter; i++) {
            if (Main.Rooms[i].getName().equalsIgnoreCase(neighbor)) {
                return Main.Rooms[i];
            }
        }
        return null;
    }
    
    static {
        Main.Rooms = new Room[50];
        Main.roomCounter = -1;
        Main.CreatureNumber = -1;
    }
}