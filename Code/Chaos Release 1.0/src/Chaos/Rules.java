/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.awt.*;
/**
 * Rules
 * Represents the RUles page
 * @author Siam
 * 05/12/2020
 */
public class Rules {
    /**
     * no params
     * empty constructor
     */
    public Rules(){
        
    }
    
    /**
     * @param g (Graphics) 
     * Paints the contents of the rules page onto the screen
     */
    public void render(Graphics g){
        //Creates two new fonts for the title and the body
        Font f = new Font("arial", 1, 50);
        Font f2 = new Font("arial", 1, 16);
        
        //Sets the colour to Orange and the font to f
        g.setColor(Color.ORANGE);
        g.setFont(f);
        
        //Paints the title
        g.drawString("Rules", 250, 70);
        
        //Sets the font to f2 before painting the rules onto the screen
        g.setFont(f2);
        g.drawString("You are the white square", 10, 125);
        g.drawString("Move Using W A S D", 10, 150);
        g.drawString("Dodge all the enemies", 10, 175);
        g.drawString("If you hit an enemy, you will lose HEALTH", 10, 200);
        g.drawString("Once your HELATH drops to Zero, the game will end", 10, 225);
        g.drawString("Different enemies have different qualities", 10, 250);
        g.drawString("RED enemies are the most basic and deal the least damage", 10, 275);
        g.drawString("BLUE enemies are faster and deal more damage than RED enemies", 10, 300);
        g.drawString("PURPLE enemies deal more damage than Blue enemies and teleport", 10, 325);
        g.drawString("GREEN enemies deal the most damage and will adjust their paths to get to you", 10, 350);
        g.drawString("Have fun!                                        Main Menu (m)", 275, 425);
    }
    
    /**
     * no params
     * empty tick method
     */
    public void tick(){
        
    }
}
