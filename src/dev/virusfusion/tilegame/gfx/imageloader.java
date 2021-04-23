package dev.virusfusion.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageloader {

    public static BufferedImage loadImage(String path) {

        try { // just in case any errors happen (which there were)
            return ImageIO.read(imageloader.class.getResource(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
