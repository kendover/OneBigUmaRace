import java.awt.*;
import java.awt.geom.*;

public class ReadyScreen {
    private double width, height;
    private String readyScreenMsg;

    
    public ReadyScreen(double width, double height){
        this.width = width;
        this.height = height;
    }

    public void drawReadyScreen(Graphics2D g2d){
        Rectangle2D.Double ReadyScreen = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(new Color(128, 128, 128, 128));
        g2d.fill(ReadyScreen);
    }
}
