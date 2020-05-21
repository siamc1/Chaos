/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chaos;
//imports
import java.util.Random;
import static Chaos.Game.HEIGHT;
import static Chaos.Game.WIDTH;

/**
 * Spawn
 * Is the spawning system
 * @author Siam
 * 05/14/2020
 */
public class Spawn {
    //Makes instances of Handler, HUD, Game and Random
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    Game game;
    
    //Makes a private int to keep the score
    private int scoreKeep = 0;
    
    //Creates an int to hold the delay
    int delay;
    
    /**
     * @param handler (Handler)
     * @param hud (HUD)
     * @param game (Game)
     * COnstructor
     */
    public Spawn(Handler handler, HUD hud, Game game){
        //Sets the local variables equal to their entered counterparts
        this.handler = handler;
        this.hud = hud;
        this.game = game;
        
        //Sets the delay to 500
        delay = 500;
    }
    
    /**
     * no params
     * Preforms all logic required to spawn in enemies in waves
     */
    public void tick(){
        //Increments scorekeep
        scoreKeep++;
        
        //If the size of thelinked list object in the handler is equal to 0, it will run the code inside
        if(handler.object.size() == 0){
            //Sets the score to 0
            hud.setScore(0);
            //Sets the level to 1
            hud.setLevel(1);
            //Spawns the player as well as 1 basic enemy
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.BasicEnemy, handler));
            
        }
        
        //If the scorekeep is greater than the delay, it will run the code inside
        if(scoreKeep >= delay){
            //Score keep is set back to 0
            scoreKeep = 0;
            
            //Increments the current level
            hud.setLevel(hud.getLevel() + 1);
            
            //The temp level is found by grabbing the level from the hud
            int tempLevel = hud.getLevel();
            
            //based on what level it is, it will spawn a dynamic number of a set of enemies after first clearing the feild
            if(tempLevel % 5 == 0){
                if(!game.isHardMode){
                    clearAll();
                }
                for(int i = 0; i < tempLevel; i++){
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.SmartEnemy, handler));
                }
            }
            
            else if(tempLevel % 4 == 0){
                if(!game.isHardMode){
                    clearAll();
                }
                for(int i = 0; i < tempLevel; i++){
                    if(i % 2 == 0){
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.FastEnemy, handler));
                    }
                    else{
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.SmartEnemy, handler));
                        handler.addObject(new TeleEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.TeleEnemy, handler));
                    }
                }
            }
            
            else if(tempLevel % 3 == 0){
                if(!game.isHardMode){
                    clearAll();
                }
                for(int i = 0; i < tempLevel; i++){
                    if(i % 2 == 0){
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.BasicEnemy, handler));
                    }
                    else{
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.FastEnemy, handler));
                    }
                }
            }
            
            else if(tempLevel % 2 == 0){
                if(!game.isHardMode){
                    clearAll();
                }
                for(int i = 0; i < tempLevel; i++){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.FastEnemy, handler));
                }
            }
            
            else if(tempLevel % 1 == 0){
                hud.HEALTH += hud.getLevel() * 2;
                if(!game.isHardMode){
                    clearAll();
                }
                for(int i = 0; i < tempLevel; i++){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 32), ID.BasicEnemy, handler));
                }
            }
            
            //If the level is a multiple of 10, the health is restored
            if(hud.getLevel() % 10 == 0){
                HUD.HEALTH = 100;
            }
            
            //decrements the delay by 5 before clamping it with a minimum of 100
            delay -= 5;
            delay = (int)(Game.clamp(delay, 100, 500));
            
        }
    }
    /**
     * no params
     * removes all enemies from the game
     */
    public void clearAll(){
        //stores the initial size of the object list in an int variable
        int size =  handler.object.size();
        //removes all objects in the object list except for the one in position 0, which is the player
        for( int i = 1; i < size; i++){
            handler.removeObject(handler.object.get(1));
        }
    }
}
