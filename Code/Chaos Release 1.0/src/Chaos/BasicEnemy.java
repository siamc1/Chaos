/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//Imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * BasicEnemy
 * Is the most basicenemy. It reflects off of walls and deals minimal damage to player upon collision.
 * @author Siam
 * 05/10/2020
 */
public class BasicEnemy extends GameObject{
    
    //Sets the dimensions of the entity as static final integers
    static final int Width = 16;
    static final int Height = 16;
    //Creates an instance of Handler
    Handler handler;
    //Creates an instance of Random from Java.util
    Random r;
    
    /**
     * @param x (x position)
     * @param y (y position)
     * @param id (Identifier)
     * @param handler (Handler)
     */
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        //Supers the position and the id into the GameObject parent class
        super(x, y, id);
        
        //Initializes the Random function
        r = new Random();
        
        //initializes velocities
        velX = r.nextInt(10) - 5;
        velY = r.nextInt(10) - 5;
        
        //initializes the handler
        this.handler = handler;
    }

    /**
     * no params
     * @returns a rectangle hitbox 
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, Width, Height);
    }
    
    /**
     * no params
     * Preforms all the actions of the enatity such as movement and bouncing off of walls.
     */
    public void tick() {
        //Moves the entity by adding the velocity to the position.
        x += velX;
        y += velY;
        
        //Checks to see if the entity is hitting the wall, if so, it bounces off by multiplying the velocity by -1
        if(y <= 0 || y >= Game.HEIGHT - 3 * Height) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - Height) velX *= -1;
    }

    /**
     * Renders the entity on the screen
     * @param g (Graphics)
     */
    public void render(Graphics g) {
        //Sets the colour to red and draws the entity using the dimensions of the entity as well as the position
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, Width, Height);
    }
    
}
