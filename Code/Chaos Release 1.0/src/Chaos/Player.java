/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


/**
 * Player
 * Is the class representing the Player
 * @author Siam
 * 05/10/2020
 */
public class Player extends GameObject{
    //Creates static ints for the width and the height fo the player
    static int Width, Height;
    //Instanciates Random and Handler
    Random r = new Random();
    Handler handler;
    
    /**
     * @param x (X Position)
     * @param y (Y Position)
     * @param id (Identification)
     * @param handler (Handler)
     */
    public Player(int x, int y, ID id, Handler handler){
        //Supers the position as well as the ID
        super(x, y, id);
        //Sets the width and length
        Width = 32;
        Height =32;
        
        //Stores the entered handler in the local handler
        this.handler = handler;
    }
    
    /**
     * @return Rectangle
     * Returns a rectangle outlining the entity
     */
    public Rectangle getBounds(){
        //Returns a rectangle outlining the entity
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    
    /**
     * no params
     * Preforms all the actions of the entity
     */
    public void tick(){
        //Updates the current position by adding the velocity
        x += velX;
        y += velY;
        
        //Clamps the position so as not to let the player outside of the game
        x = Game.clamp(x, 0, Game.WIDTH - Width - 6);
        y = Game.clamp(y, 0, Game.HEIGHT - 2 * Height - 3);
        
        //Calls the collision method
        collision();
    }
    
    /**
     * no params
     * Checks to see if the player hit anything, and if so, applys the appropriate reaction
     */
    private void collision(){
        //Runs through all the GameObjects in the object LinkedList in handler
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            //Checks to see what object it is and whether or not it and the player are touching, if they are, the appropriate COLLISIONCODE is run
            if(tempObject.getId() == ID.BasicEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //COLLISIONCODE
                    HUD.HEALTH -= 4;
                    handler.removeObject(tempObject);
                }
            }
            else if(tempObject.getId() == ID.FastEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //COLLISIONCODE
                    HUD.HEALTH -= 6;
                    handler.removeObject(tempObject);
                }
            }
            else if(tempObject.getId() == ID.SmartEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //COLLISIONCODE
                    HUD.HEALTH -= 10;
                    handler.removeObject(tempObject);
                }
            }
            else if(tempObject.getId() == ID.TeleEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //COLLISIONCODE
                    HUD.HEALTH -= 15;
                    handler.removeObject(tempObject);
                }
            }
        }
    }
    
    /**
     * @param g (Graphics)
     * Paints the player onto the screen
     */
    public void render(Graphics g){
        //Sets the colour to white
        g.setColor(Color.white);
        //Paints a rectangle to represent the player on the screen
        g.fillRect((int)x, (int)y, Width, Height);
    }
}
