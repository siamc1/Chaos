/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;

//Imports
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.Random;
/**
 *
 * @author Siam
 */
public class Game extends Canvas implements Runnable{
    //Sets the width and height of the application using static final integers
    static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
        
    //creates  a private thread to run the game on
    private Thread thread;
    //Breates a boolean variable which holds the value of whether or not the game is running
    private boolean running = false;
        
    //Creates a new handler object
    private Handler handler;
    //creates a new instance of Random
    private Random r;
    //Creates a new Heads Up Display object
    private HUD hud;
    //Creates a new spawn object
    private Spawn spawner;
    //Creates a new Menu page
    private Menu menu;
    //Creates a new Dead screen page
    private DeadScreen deadscreen;
    //Creates a new rules page
    private Rules rules;
    //Creates a new color for the baackground
    private Color backColor;
    
    //Creates 2 static boolean values to hold hard ode and if the player died before (IisHardMode NOT IN USE AS OF VERSION Release 1.0)
    static boolean isHardMode, isDeadFirst;
    
    /**
     * enum to represent the various game states
     */
    public enum STATE{
        Menu,
        Dead,
        Rules,
        Game;
    }
    
    //Creates a State object to hold the current state of the game
    static STATE gameState = STATE.Menu;
    
    /**
     * no params
     * Constructor for the Game, allows you to instantiate the game
     */
    public Game(){
        //Sets the background colour to dark gray
        backColor = Color.DARK_GRAY;
        //initializes the handler
        handler = new Handler();
        //initializes the menu
        menu =  new Menu(this, handler);
        //initializes the death screen
        deadscreen =  new DeadScreen();
        //initializes the rules screen
        rules = new Rules();
        //Adds a key listner to listen to key inputs
        this.addKeyListener(new KeyInput(handler, this));
        
        //Creates a new window with the name and the dimensions of the game
        new Window(WIDTH, HEIGHT, "Chaos", this);
        
        //initializes the heads up display
        hud = new HUD();
        //initializes the spawner
        spawner = new Spawn(handler, hud, this);
        
        //initializes the random function
        r = new Random();
        
        //Sets isHardMode to false(NOT IN USE AS OF VERSION Release 1.0)
        isHardMode = false;
    }
    
    /**
     * no params
     * instantiates the thread and starts it
     */
    public synchronized void start(){
        //Initializes the thread
	thread = new Thread(this);
        //starts the threads computations
	thread.start();
        //sets running to true, signifying that the game is running
	running = true;
    }
    
    /**
     * no params
     * Stops the game
     */
    public synchronized void stop(){
        //Tries to join the thread and set running to false. If it can't, it will print the stacktrace, if it can, it set's running to alse, which exits the while loop defined in Game's run method, stopping the game
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * no params
     * runs the game
     */
    public void run(){
        //Makes the game the active window
        this.requestFocus();
        //stores the current runtime in a long variable
        long lastTime = System.nanoTime();
        //uses a double value to hold the number of ticks
        double amountOfTicks = 60.0;
        //stores the time between each tick in nanoseconds
        double ns = 1000000000 / amountOfTicks;
        //creates a new variable to hold the delta
        double delta = 0;
        //creates a long value to store the current itme in milliseconds
        long timer = System.currentTimeMillis();
        //creates an integer variable to hold the number of frames per second
        int frames = 0;
        //will continue running as long as the game is running
        while(running){
            //Stores the current time in a long
            long now = System.nanoTime();
            //makes the delta equal to the current time minus the last time divided by the time between ticks
            delta += (now - lastTime) / ns;
            //sets the last time to now
            lastTime = now;
            //will continue running as long as delta is greater than or equals to 1
            while(delta >=1){
                //calls the tick method
                tick();
                // subtracts 1 from delta
                delta--;
            }
            //if the game is running, it will call the render method
            if(running)
                render();
            
            //increments the frame count
            frames++;
                       
            //if the current time minus timer is more than 1000, the timer is incremented by 1000 and the frame counter is set to 0
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        //Once the game stops running, the stop method is called
        stop();
    }
    
    /**
     * no params
     * preforms all the entity actions by calling the ticks of various entity classes as well as some logic
     */
    private void tick(){
        //calls the handlers tick method
        handler.tick();
        //Checks which state the game is in and calls the appropriate tick methods from the appropriate objects
        if(gameState == STATE.Game){
            hud.tick();
            spawner.tick();
        }
        else if(gameState == STATE.Menu){
            menu.tick();
        }
        else if(gameState == STATE.Dead){
            deadscreen.tick();
            
            if(isDeadFirst){
                isDeadFirst = false;
           }
            
        }
        else if(gameState == STATE.Rules){
            rules.tick();
        }
        if(hud.getHealth() == 0){
            handler.object.clear();
            isDeadFirst = true;
            hud.HEALTH = 1;
            gameState = STATE.Dead;
        }
        if(hud.getLevel() / 10 % 2 == 0){
            backColor = Color.DARK_GRAY;
        }
        else if(hud.getLevel() / 10 % 2 == 1){
           backColor = Color.BLACK;
        } 
    }

    /**
     * no params
     * renders the graphics on the screen by calling various entity render methods as well as utilising some logic
     */
    private void render(){
        //Creates a new BufferStrategy to allow for the game to render and tick at the same time
        BufferStrategy bs = this.getBufferStrategy();
        
        //if the Buffered Strategy is undefined it will define it and exit the render method.
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        //Uses the BufferedStrategy to create a graphics object
        Graphics g = bs.getDrawGraphics();
        
        //sets the colour to the predefined background colour and fills the background with the background colour
        g.setColor(backColor);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //Checks which state the game is in and calls the appropriate render methods from the various objects
        if(gameState == STATE.Game){
            handler.render(g);
            hud.render(g);
        }
        else if(gameState == STATE.Menu){
            menu.render(g);
        }
        else if(gameState == STATE.Dead){
            deadscreen.render(g, hud.getLevel(), hud.getScore());
        }
        else if(gameState == STATE.Rules){
            rules.render(g);
        }
        //resets the graphics to null and shows the Buffered Stratagy
        g.dispose();
        bs.show();
    }
        
    /**
     * @param var (Input variable)
     * @param min (Minimum legal value of the variable)
     * @param max (Maximum legal value of the cariable)
     * @return float
     */
    public static float clamp(float var, float min, float max){
        //if the variable is found outside the given bounds, it returns the edges of the given bound, otherwise it just returns the variable
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        else{
            return var;
        }
    }

    /**
     * @param args
     * starts the game at the start of the program
     */
    public static void main(String [] args){
        //Creates a new instance of Game, causing the constructor to be called, starting the game
    	new Game();
    }
}

