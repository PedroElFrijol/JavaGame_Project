package dev.virusfusion.tilegame.entities;

import java.awt.*;

public abstract class Entity {

    protected float x, y; //protected means a private variable except classes that extend this class also has access to them

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
