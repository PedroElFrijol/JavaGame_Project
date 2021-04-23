package dev.virusfusion.tilegame.tiles;

import dev.virusfusion.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256]; //array
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile RockTile = new RockTile(2);

    // static means you can access this from anywhere

    //CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y){

        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);

    }

    public boolean isSolid() {
        return false;

    }

    public int getId() {
        return id;
    }

}
