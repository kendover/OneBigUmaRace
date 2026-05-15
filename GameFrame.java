import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class GameFrame extends JFrame{
    private GameCanvas gc;
    private JLabel readyScreenLabel;
    private int width = 1024;
    private int height = 768;

    public GameFrame(){
        gc = new GameCanvas(width, height);
        this.setResizable(false);

        readyScreenLabel = new JLabel();
        readyScreenLabel.setHorizontalTextPosition(JLabel.CENTER);
        readyScreenLabel.setVerticalTextPosition(JLabel.CENTER);
        readyScreenLabel.setForeground(Color.WHITE);
        readyScreenLabel.setFont(new Font("Arial",Font.BOLD,50));
        readyScreenLabel.setOpaque(true);
        readyScreenLabel.setBackground(new Color(50, 50, 50, 180));
 
        this.add(gc);
        this.pack();
        this.setVisible(true);        
    }

    public GameCanvas getGameCanvas() {
        return gc;
    }

    public void setReadyScreenLabel(){
        readyScreenLabel.setText("message");
        readyScreenLabel.setVisible(true);
    }

    public void clearReadyScreenLabel(){
        readyScreenLabel.setVisible(false);
    }
}
