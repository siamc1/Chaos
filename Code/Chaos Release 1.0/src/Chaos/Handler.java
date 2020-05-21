/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Handler
 * Handles all the gameObjects by calling their various methods, allowing for a dynamic entity size with ease
 * @author Siam
 * 05/13/2020
 */
public class Handler {
    //Creates a linked list with GameObject type in order to hold all the GameObjects currently within play
    LinkedList<GameObject> object = new LinkedList();
    
    /**
     * no params
     * Calls the tick method of all game objects in play
     */
    public void tick(){
        //Uses a for loop to run through all the game objects in object
        for (int i = 0; i < object.size(); i++){
            //creates a temporary object to hold the GameObject at i
            GameObject tempObject =  object.get(i);
            //Calls the tick methode of the GameObject at i
            tempObject.tick();
        }
    }
    
    /**
     * @param g (Graphics)
     * Calls the render method of all game objects in play
     */
    public void render(Graphics g){
        //Uses a for loop to run through all the game objects in object
        for ( int i = 0; i < object.size(); i++){
            //Creates a temporary object to hold the GameObject at i
            GameObject tempObject = object.get(i);
            //Calls the render method of the GameObject at i
            tempObject.render(g);
        }
    }
    
    /**
     * @param object1  (GameObject to be added)
     * Adds a new GameObject to the object list
     */
    public void addObject(GameObject object1){
        //Adds the GameObject entered to the object list
        this.object.add(object1);
    }
    
    /**
     * @param object1 (GameObject to be removed)
     * Removes a GameObject from the object list
     */
    public void removeObject(GameObject object1){
        //Removes the GameObject entered from the object list
        this.object.remove(object1);
    }
}
