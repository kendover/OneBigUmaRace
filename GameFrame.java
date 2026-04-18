import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame {
    private JFrame frame;
    private GameCanvas gc;
    
    public GameFrame(){
        frame = new JFrame();
        gc = new GameCanvas(1024, 768);
        
        frame.add(gc);
        frame.setVisible(true);
    }

    public void setUpGUI(){
        Container cp = frame.getContentPane();
        frame.setTitle("Final Project - Fernandez - Periña");
        frame.pack();        
    }

    public void setUpButtonListener() {

    }


}
