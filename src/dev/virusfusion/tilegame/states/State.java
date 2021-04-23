package dev.virusfusion.tilegame.states;

import dev.virusfusion.tilegame.Game;

import java.awt.*;

public abstract class State { //An abstract class is a class that is declared abstract —it may or may not include abstract methods.
    // Abstract classes cannot be instantiated, but they can be subclassed.

    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState() {

        return currentState;

    }

    //CLASS

    protected Game game;

    public State(Game game) {

        this.game = game;

    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
