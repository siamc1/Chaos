/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
import java.awt.event.*;
import static Chaos.Game.HEIGHT;
import static Chaos.Game.WIDTH;
import java.util.Random;
/**
 * KeyInput
 * Checks for KeyInput in various GameStates and preforms actions appropriately
 * @author Siam
 * 05/10/2020
 */
public class KeyInput extends KeyAdapter{
    
    //Creates a private instance of Handler
    private Handler handler;
    
    //Creates 3 private boolean variables to hold the various directional commands
    private boolean uP = false;
    private boolean dP = false;
    private boolean lP = false;
    private boolean rP = false;
    
    //Creates an instance of the Random function and defines it
    Random r = new Random();
    //Creates an instance of Game
    Game game;
    
    /**
     * @param handler (Handler)
     * @param game (Game)
     * Constructor
     */
    public KeyInput(Handler handler, Game game){
        //Sets local variables to the values entered
        this.handler = handler;
        this.game = game;
    }
    
    /**
     * @param e (KeyEvent)
     * Reads key Input and preforms appropriate actions based on the GameState
     */
    public void keyPressed(KeyEvent e){
        //Stores the key pressed's value in an int variable
        int key = e.getKeyCode();
        
        //Based on which GameState the game is in, it will run one of the following code sets
        if(Game.gameState == Game.STATE.Dead){
            //If the player enters q, the game is quit
            if(key == KeyEvent.VK_Q){
                System.exit(0);
            }
            //If the user enters r, the game is restarted
            else if(key == KeyEvent.VK_R){
                HUD.HEALTH = 100;
                Game.gameState = Game.STATE.Game;
            }
            //If the user enters M, the game is sent to the main menu
            else if(key == KeyEvent.VK_M){
                HUD.HEALTH = 100;
                Game.gameState = Game.STATE.Menu;
            }
        }
        else if(Game.gameState == Game.STATE.Menu){
            //If the user enters p, the game is started
            if(key == KeyEvent.VK_P){
                HUD.HEALTH = 100;
                Game.gameState = Game.STATE.Game;
            }
            //If the user enters r, the game is sent to the rules GameState
            else if(key == KeyEvent.VK_R){
                Game.gameState = Game.STATE.Rules;
            }
        }
        else if(Game.gameState == Game.STATE.Rules){
            //If the user enters m, the game is sent bakc to the menu GameState
            if(key == KeyEvent.VK_M){
                Game.gameState = Game.STATE.Menu;
            }
        }
        else if(Game.gameState == Game.STATE.Game){
            //Runs through all the objects in the object LinkedList in the handler
            for(int i = 0; i < handler.object.size(); i++){
                GameObject temp = handler.object.get(i);
            
                //If the object is of Identity Player this code runs
                if(temp.getId() == ID.Player){
                    //Based on key input it will set the boolean variables to true and set the velocities to the appropriate values
                    if(key == KeyEvent.VK_W){
                        uP = true;
                        temp.setVelY(-5);
                    }
                    if(key == KeyEvent.VK_S){
                        dP = true;
                        temp.setVelY(5);
                    }
                    if(key == KeyEvent.VK_A){
                        lP = true;
                        temp.setVelX(-5);
                    }
                    if(key == KeyEvent.VK_D){
                        rP = true;
                        temp.setVelX(5);
                    }
                }
            }
            

        }
        
        
    }
    
/**
 * @param e (KeyEvent)
 * Checks for when a key is released
 */
    public void keyReleased(KeyEvent e){
        //stores the value of the released key in an int variable
        int key = e.getKeyCode();
        
        //Will run code inside if the GameState is Game
        if(Game.gameState == Game.STATE.Game){
            //Uses a for loop to run through all the objects in the object linked list in the handler
            for(int i = 0; i < handler.object.size(); i++){
                GameObject temp = handler.object.get(i);
                
                //If the Object at i has an Identity of Player, it will run the code inside
                if(temp.getId() == ID.Player){
                    //Based on the key released and the boolean variables set to true, it will set the correct boolean variables to true or false and set the velocities to the correct values
                    if(key == KeyEvent.VK_W){
                        uP = false;
                        if(dP){
                            temp.setVelY(5);
                        }
                        else{
                            temp.setVelY(0);
                        }
                    }
                    if(key == KeyEvent.VK_S){
                        dP = false;
                        if(uP){
                            temp.setVelY(-5);
                        }
                        else{
                            temp.setVelY(0);
                        }
                    }
                    if(key == KeyEvent.VK_D){
                        rP = false;
                        if(lP){
                            temp.setVelX(-5);
                        }
                        else{
                            temp.setVelX(0);
                        }
                    }
                    if(key == KeyEvent.VK_A){
                        lP = false;
                        if(rP){
                            temp.setVelX(5);
                        }
                        else{
                            temp.setVelX(0);
                        }
                    }
                }
            }
        }
        
    }
    
}
