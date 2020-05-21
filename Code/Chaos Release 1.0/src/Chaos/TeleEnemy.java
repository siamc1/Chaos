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
import java.util.Random;
/**
 * TeleEnemy
 * A representation of an enemy which teleports from one side of the screen to the other instead of bouncing
 * @author Siam
 * 05/15/2020
 */
public class TeleEnemy extends GameObject{
    //Sets the dimensions of the entity as int variables
    int Width = 32;
    int Height = 32;
    
    //Creates instances of Handler and Random
    Handler handler;
    Random r;
    
    /**
     * @param x (X Position)
     * @param y (Y Position)
     * @param id I(Identifier)
     * @param handler (Handler)
     * Constructor
     */
    public TeleEnemy(int x, int y, ID id, Handler handler) {
        //Susprs the position and the identifier
        super(x, y, id);
        
        //Initializes random
        r = new Random();
        
        //Sets velocities to random balues between -8 and 8
        velX = r.nextInt(16) - 8;
        velY = r.nextInt(16) - 8;
        
        //Sets local handler equal to entered handler
        this.handler = handler;
    }

    /**
     * no params
     * @return rectangle
     */
    public Rectangle getBounds(){
        //Returns a rectangle that outlines the entity
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    /**
     * no params
     * Preforms all logic of the enemy
     */
    public void tick() {
        //increments position with the velocities
        x += velX;
        y+= velY;
        
        //checks to see if it's touching a border, if so, it transports it to the opposite border
        if(y <= 0){
            y = Game.HEIGHT - 3 * Height;
        }
        else if(y >= Game.HEIGHT - 3 * Height){
            y = 0;
        }
        if(x <= 0){
            x = Game.WIDTH - Height;
        }
        else if(x >= Game.WIDTH - Height){
            x = 0;
        }
    }

    /**
     * @param g (Graphics)
     * Paints the entity on the screen
     */
    public void render(Graphics g) {
        //Sets the colour to a special purple
        g.setColor(new Color(153, 51, 153));
        //Draws the entity  on screen
        g.fillRect((int)x, (int)y, Width, Height);
    }
}
