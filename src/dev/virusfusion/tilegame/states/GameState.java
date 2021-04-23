package dev.virusfusion.tilegame.states;

import dev.virusfusion.tilegame.Game;
import dev.virusfusion.tilegame.entities.creatures.Player;
import dev.virusfusion.tilegame.gfx.Assets;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(Game game) {
        super(game);
        player = new Player(game,100, 100);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }
}
