/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;

//Imports all required libraries.
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * FastEnemy
 * A fast moving enemy which deals minimal damage when it collides with the player
 * @author Siam
 * 05/10/2020
 */
public class FastEnemy extends GameObject{
    //Sets the dimensions of the entity as static final integers
    static final int Width = 16;
    static final int Height = 16;
    
    //creates an instance of the handler
    Handler handler;
    //Creates the random function 
    Random r;
    
    /**
     * @param x (X Position)
     * @param y (Y Position)
     * @param id (Identifier)
     * @param handler (Handler)
     */
    public FastEnemy(int x, int y, ID id, Handler handler) {
        //supers the position as well as the id into the GameObject class
        super(x, y, id);
        
        //initializes the random function
        r = new Random();
        
        //generates random values for the velocities and sets the corresponding variables to the right values
        velX = r.nextInt(20) - 10;
        velY = r.nextInt(20) - 10;
        
        //initializes the handler
        this.handler = handler;
    }
    
    /**
     * no params
     * @return Rectangle
     * Returns a rectangle with the dimensions and position of the entity 
     */
    public Rectangle getBounds(){
        //returns the hitbox of this enemy
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    /**
     * no params
     * preforms all the computations of this entity
     */
    public void tick() {
        //increments the position using the velocity values
        x += velX;
        y+= velY;
        
        //makes the entity bounce off the walls by checking to see if it is touching a wall, and if so it multiplies the velocities by negative one to make it look like the entity is bouncing
        if(y <= 0 || y >= Game.HEIGHT - 3 * Height) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - Height) velX *= -1;
    }

    /**
     * @param g (Graphics)
     * paints the entity on to the screen
     */
    public void render(Graphics g) {
        //sets the colour to blue before painting a square onto the screen which represents the entity
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, Width, Height);
    }
}
