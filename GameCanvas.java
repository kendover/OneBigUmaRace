import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class GameCanvas extends JComponent{
    private int width, height;
    private PlayerSprite playerSprite;

    public GameCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));
    }

    public void setPlayerSprite(PlayerSprite ps) {
        playerSprite = ps;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double r = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.decode("#009A17"));
        g2d.fill(r);

        playerSprite.drawSprite(g2d);
        
    }
}
