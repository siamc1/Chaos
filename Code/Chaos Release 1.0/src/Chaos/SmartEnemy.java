/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * SmartEnemy
 * An object which represents an enemy that will move towards the player
 * @author Siam
 * 05/11/2020
 */
public class SmartEnemy extends GameObject{
    //Creates int variables for the width and height of the enemy
    int Width = 16;
    int Height = 16;
    
    //Creates an instance of the handler
    Handler handler;
    
    //Creates an instance of the player as a GameObject
    private GameObject player;
    
    /**
     * @param x (X PPosition)
     * @param y (Y Position)
     * @param id (Identifier)
     * @param handler (Handler)
     * Constructor
     */
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        //Supers the position and the identifier into GameObject
        super(x, y, id);
        
        //Runs through the objects in the object linked list in handler to find player
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }
        
        //Sets the initial velocity to 5
        velX = 5;
        velY = 5;
        
        //Defines the local handler as the entered handler
        this.handler = handler;
    }

    /**
     * @return Rectangle
     * Returns a rectangle outlining the object
     */
    public Rectangle getBounds(){
        //Returns a rectangle outlining the object
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    /**
     * no params
     * preforms all functions of the enemy
     */
    public void tick() {
        //Increments the position by adding the velocities
        x += velX;
        y += velY;
        
        //Finds the difference in position
        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        
        //Calculates straightline distance between enemy and player using Pythagorean Theorem
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
        
        //Calculates the required velocities for the enemy to move towards the player 
        velX = (float)((-1 / distance) * diffX) * 2;
        velY = (float)((-1 / distance) * diffY) * 2;
        
        //Allows for it to bounce off of walls
        if(y <= 0 || y >= Game.HEIGHT - 3 * Height) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - Height) velX *= -1;
        
    }

    /**
     * @param g (Graphics)
     * Paints the object onto the screen
     */
    public void render(Graphics g) {
        //Changes the colour to green and draws the object on the screen
        g.setColor(Color.GREEN);
        g.fillRect((int)x, (int)y, Width, Height);
    }
}
