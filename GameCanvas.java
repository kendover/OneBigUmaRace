import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class GameCanvas extends JComponent{
    private int width, height;
    private PlayerSprite playerSprite, playerSprite2;
    private FinishLine finishLine;
    private ReadyScreen readyScreen;

    public GameCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));
    }

    public void setPlayerSprite() {
        playerSprite = new PlayerSprite(100, 256, 50, Color.decode("#98b2ff"));
        playerSprite2 = new PlayerSprite(100, 512, 50, Color.decode("#ffb098"));
        repaint();
    }

    public void setFinishLine(){
        finishLine = new FinishLine(985, 0);
    }

    public void setReadyScreen(){
        readyScreen = new ReadyScreen(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double r = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(Color.decode("#4b9456"));
        g2d.fill(r);
        playerSprite.drawSprite(g2d);
        playerSprite2.drawSprite(g2d);
        finishLine.drawFinishLine(g2d);
        
    }

    public PlayerSprite getPlayerSprite(){
        return playerSprite;
    }

    public PlayerSprite getPlayerSprite2(){
        return playerSprite2;
    }

    public FinishLine getFinishLine(){
        return finishLine;
    }
}
