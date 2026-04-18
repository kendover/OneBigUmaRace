import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GameCanvas extends JComponent{
    private int width, height;

    public GameCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }
}
