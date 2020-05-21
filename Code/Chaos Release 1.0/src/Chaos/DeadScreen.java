/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * DeadScreen
 * Basic menu screen for when the player dies.
 * @author Siam
 * 05/12/2020
 */
public class DeadScreen {
    /**
     * no params
     * empty constructor
     */
    public DeadScreen(){
        
    }
    
    /**
     * no params
     * empty tick method
     */
    public void tick(){
        
    }
    
    /**
     * @param g (Graphics)
     * @param l (Final level)
     * @param s (Final score)
     * Paints the contents of the death screen onto the screen
     */
    public void render(Graphics g, int l, int s){
        //Sets color to black and fills the background with it
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        
        //Creates two new fonts for the title and the contents
        Font f = new Font("arial", 1, 50);
        Font f2 = new Font("arial", 1, 25);
        
        //Sets the color to red and the font to f before painting the title of the page to the screen
        g.setColor(Color.RED);
        g.setFont(f);
        g.drawString("Game Over", 185, 150);
        
        //Changes the font to f2 and paints the gam einformationa s well as your options
        g.setFont(f2);
        g.setColor(Color.orange);
        g.drawString("Final Level: " + l, 225, 200);
        g.drawString("Final Score: " + s, 225, 250);
        g.drawString("RESPAWN (r)", 230, 300);
        g.drawString("MAIN MENU (m)", 222, 350);
        g.drawString("QUIT (q)", 260, 400);
        
    }
}
