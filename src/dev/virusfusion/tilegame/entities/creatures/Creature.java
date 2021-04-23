package dev.virusfusion.tilegame.entities.creatures;

import dev.virusfusion.tilegame.entities.Entity;

public abstract class Creature extends Entity {

    protected int health;


    public Creature(float x, float y) {
        super(x, y); // refers to whatever class you extended
        health = 10;
    }
}
