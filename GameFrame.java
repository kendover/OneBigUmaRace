import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame{
    private GameCanvas gc;
    private int width = 1024;
    private int height = 768;
    
    public GameFrame(){
        gc = new GameCanvas(width, height);
        this.setResizable(false);        
        this.add(gc);
        this.pack();
        this.setVisible(true);
    }
}
