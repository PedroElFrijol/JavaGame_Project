package dev.virusfusion.tilegame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.virusfusion.tilegame.gfx.Assets;
import dev.virusfusion.tilegame.gfx.SpriteSheet;
import dev.virusfusion.tilegame.gfx.imageloader;
import dev.virusfusion.tilegame.input.KeyManager;
import dev.virusfusion.tilegame.states.GameState;
import dev.virusfusion.tilegame.states.MenuState;
import dev.virusfusion.tilegame.states.State;

public class Game implements Runnable {

    private Display display;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs; // A buffer strategy tells how to draw things to the screen

    // a buffer is a hidden computer screen within your computer

    private Graphics g; // allow an application to draw onto components that can be realized on various devices

    //States
    private State gameState;
    private State menuState;

    //Inputs
    private KeyManager keyManager;

    public Game(String title, int width, int height) {
        this.width = width; // (this) refers to a different type of variable with the same name
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
    }

    int x = 0;

    private void update() {

        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();

    }

    private void render() {

        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen

        g.clearRect(0, 0, width, height);

        //Draw Here!

        if(State.getState() != null)
            State.getState().render(g);

        //End Drawing
        bs.show(); // showing the hidden screen (buffer)
        g.dispose();
    }

    public void run() {

        init();

        int fps = 60; // frames per second
        double timePerTick = 1000000000 / fps; // 1 billion nano seconds in 1 second divided by the fps
        double delta = 0; // it tells the computer when and when not to call the update and render methods
        // delta is also the amount of time we have until we we have to call the update and render methods
        long now; // The long data type is a 64-bit two's complement integer
        long lastTime = System.nanoTime(); // return the current time of computer but in nano seconds
        long timer = 0;
        int ticks = 0;

        while(running) {
            now = System.nanoTime(); // now is the time of our computer so it should equal the amount of frames so it doesn't run fast or slow but the amount that is needed
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime; // add the amount of time that is passed since the other code was last ran
            lastTime = now; // we are all done with this part of the code so our "lastTime" should be the "now" variable which should be the time of our computer

            // "now - lastTime" will get the amount of time past since we last called "delta += (now - lastTime) / timePerTick;"
            // then were going to divide that amount of time by the maximum amount of time were allowed to have to call the update and render methods and were adding it to the delta variable
            // this is going to add to the delta variable essentially how much time we have until we have to call these update and render methods again

        if(delta >= 1) { // if "delta" is bigger than or equal to 1 that means we have to update or render in order to achieve 60 seconds
            update();
            render();
            ticks ++;
            delta--; // if update and render was successful than we can subtract by 1
        }

        if(timer >= 1000000000) { // if our timer has been running for 1 second, we want to see how many ticks we got in that one second
            System.out.println("FPS:" + ticks);
            ticks = 0;
            timer = 0;
        }
      }

        stop();

    }

    public KeyManager getKeyManager(){

        return keyManager; // return the keymanager object so other classes can access it

    }

    public synchronized void start() { // Synchronization in java is the capability to control the access of multiple threads to any shared resource

        if(running) // the return statement is here just in case it didn't start or stop, or so there is no bugs in the game
            return;

        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop() {
        if(!running)// ! = not
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
