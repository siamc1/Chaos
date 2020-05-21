/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.awt.*;
import java.util.*;
/**
 * Menu
 * The class that represents the main menu
 * @author Siam
 * 05/10/2020
 */
public class Menu{
    
    //Creates instances of Game, Handler and Random for future use
    private Game game;
    private Handler handler;
    Random r = new Random();
    
    /**
     * @param game (Game)
     * @param h  (Handler)
     * Constructor
     */
    public Menu(Game game, Handler h){
        //Sets local variables values equal to the entered values
        this.game = game;
        handler = h;
    }
    
    /**
     * @param g (Graphics)
     * Paints the contents of the Main Menu onto the screen
     */
    public void render(Graphics g){
        //Creates two fonts. one for the tile and one for the options
        Font f = new Font("arial", 1, 50);
        Font f2 = new Font("arial", 1, 25);
        
        //Sets the font to f and the colour to Orange
        g.setColor(Color.ORANGE);
        g.setFont(f);
        
        //Paints the title to the top center of the screen
        g.drawString("Chaos", 250, 70);
        //Changes font to f2 before printing out the options
        g.setFont(f2);
        g.drawString("Play (p)", 275, 190);
        g.drawString("Rules (r)", 265, 290);
    }
    
    /**
     * no paramss
     * Empty method
     */
    public void tick(){
        
    }
    
}
