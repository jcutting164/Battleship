import java.awt.Canvas;
import java.awt.Frame;

import java.awt.Dimension;
import java.io.Serializable;


import javax.swing.*;


public class Window extends Canvas implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7171449239035750291L;

    public Window(float width, float height, String title, Game game)
    {
        Frame frame = new Frame(title);
        frame.setPreferredSize(new Dimension((int) width, (int) height));
        frame.setMaximumSize(new Dimension((int) width, (int) height));
        frame.setMinimumSize(new Dimension((int) width, (int) height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.add(game);
        frame.setVisible(true);


        game.start();


    }


}