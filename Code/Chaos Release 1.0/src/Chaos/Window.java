/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import javax.swing.*;
import java.awt.*;
/**
 * Window
 * Creates a screen for the game to occur on and starts the game
 * @author Siam
 * 05/10/2020
 */
public class Window {
    
    /**
     * @param width (Width of screen)
     * @param height (Height of screen)
     * @param title (Title of screen)
     * @param game (Game)
     * Constructor
     */
    public Window(int width, int height, String title, Game game){
        //Creates a new JFrame for the game to occur in
        JFrame screen = new JFrame();

        //Sts the preferred size the maximum size and the minimum size all as the same size with dimensions of width andheight
        screen.setPreferredSize(new Dimension(width, height));
        screen.setMaximumSize(new Dimension(width, height));
        screen.setMinimumSize(new Dimension(width, height));
        
        //Sets the default closed operation to exit on close, meaning the program wil stop once the screen is closed
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sets the screen as not resizeable
        screen.setResizable(false);
        //Sets the location of the screen to the center of the computer screen
        screen.setLocationRelativeTo(null);
        //adds the game to the JFrame
        screen.add(game);
        //Makes the JFrame visibel
        screen.setVisible(true);
        //Starts the game
        game.start();
    }
}
