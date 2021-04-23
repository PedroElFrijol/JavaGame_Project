package dev.virusfusion.tilegame;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height) {

        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }


    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // appears to center of screen
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); // make it so our JFrame can only have focus

        frame.add(canvas);
        frame.pack(); // resized the window a little bit so that the canvas can be seen fully
    }

    public Canvas getCanvas() { //accessing canvas publicly

        return canvas;
    }
    public JFrame getFrame() { // accessing the JFrame outside of the Display class

        return frame;
    }

}
