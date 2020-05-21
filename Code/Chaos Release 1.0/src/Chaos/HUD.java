/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.awt.Color;
import java.awt.Graphics;

/**
 * HUD (Heads Up Display)
 * Is the entity that holds and shows your HP, score, level and more
 * @author Siam
 * 05/12/2020
 */
public class HUD {
    //Creates a static int to hold thee health points and assigns it a starting value of 100
    static int HEALTH = 100;
    
    //Creates a private int to hold the green value of the health bar and gives it an iniitilal vaue of 255
    private int greenVal = 255;
    
    //Creates private ints to hold the score and level and gives them initial values of 0 and 1 respectively
    private int score = 0;
    private int level = 1;
    
    //creates ints to hold dimensions and positions
    int x = 15, y = 15, height = 32, width = 200;
    
    /**
     * no params
     * Preforms all the actions 
     */
    public void tick(){
        HEALTH = (int)(Game.clamp(HEALTH, 0, 100));
        greenVal = (int)(Game.clamp(greenVal, 0, 255));
        
        greenVal = HEALTH * 2;
        
        score++;
    }
    
    /**
     * @param g (Graphics)
     * Paints all components of the entity on to the screen
     */
    public void render(Graphics g){
        //sets the colour to gray before painting the background of the health bar
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
        
        //sets the color to a new colour using the entered RRGB values and then drawing the health bar
        g.setColor(new Color(75, greenVal, 0));
        g.fillRect(x, y, HEALTH * 2, height);
        
        //paints a stroke around the health bar in black
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        
        //sets the colour to white and paints out the score and the level
        g.setColor(Color.white);
        g.drawString("Score: " + score, 10, 64);
        g.drawString("Level: " + level, 10, 80);
    }
    
    /**
     * @param score
     * Changes the recorded score value for the entered one
     */
    public void setScore(int score){
        this.score= score;
    }
    
    /**
     * @return int score
     * Returns the recorded score
     */
    public int getScore(){
        return score;
    }
    
    /**
     * @param level
     * CHanges the recorded level value for the entered one
     */
    public void setLevel(int level){
        this.level = level;
    }
    
    /**
     * @return int level
     * returns the recorded level value
     */
    public int getLevel(){
        return level;
    }
    
    /**
     * @return int HEALTH
     * returns the current recorded health
     */
    public int getHealth(){
        return(HEALTH);
    }
}
