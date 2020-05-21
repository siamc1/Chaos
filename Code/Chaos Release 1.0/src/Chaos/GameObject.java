/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;

//Imports
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject{

    //Creates protected float variables for the position of the GameObject
    protected float x, y;

    //Creates a protected ID to hold the identifier
    protected ID id;
        
    //Creates a protected float set to hold the velocities in the two dimensions
    protected float velX, velY;
        
    /**
     * @param x1 (X Position)
     * @param y1 (Y Position)
     * @param id1 (Identifier)
     * Constructor, assigns variables
     */
    public GameObject(float x1, float y1, ID id1){
        //Sets the local variables as the inputed ones
        this.x = x1;
        this.y = y1;
        this.id = id1;
    }
        
    //Creates abstract methods tick, render and getBounds, which will be inherited and written in the entity clases 
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
        
    /**
     * @param x (X Position)
     * Changes the recorded x value for the one entered
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * @param y (Y Position)
     * Changes the recorded y value for the entered value
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * @return float x
     * returns the recorded x value
     */
    public float getX(){
        return x;
    }
    
    /**
     * @return float y
     * returns the recorded y value
     */
    public float getY(){
        return y;
    }
    
    /**
     * @param id (Identifier)
     * Changes the recirded id value for the entered value
     */
    public void setId(ID id){
        this.id = id;
    }
    
    /**
     * @return ID id
     * Returns the recorded id
     */
    public ID getId(){
        return id;
    }
    
    /**
     * @param x (X Velocity)
     * Changes the recorded velX value for the entered value
     */
    public void setVelX(int x){
        this.velX = x;
    }
        
    /**
     * @param y (Y Velocity) 
     * CHanges the recorded velY for the entered value
     */
    public void setVelY(int y){
        this.velY = y;
    }
    
    /**
     * @return float velX
     * return the recorded X Velocity value
     */
    public float getVelX(){
        return velX;
    }
    
    /**
     * @return float velY
     * Returns the recorded Y Velocity value
     */
    public float getVelY(){
        return velY;
    }
}