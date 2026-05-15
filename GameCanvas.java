import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class GameCanvas extends JComponent{
    private int width, height;
    private PlayerSprite playerSprite, playerSprite2;
    private FinishLine finishLine;
    private ReadyScreen readyScreen;
    private Image agnes, oguri;

    public GameCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));

        agnes = new ImageIcon(getClass().getResource("agnes1.jpeg")).getImage();
        oguri = new ImageIcon(getClass().getResource("oguri1.jpeg")).getImage();
    }

    public void setPlayerSprite(int playerID) {
        if (playerID == 1) {
            playerSprite = new PlayerSprite(agnes, 100, 256, 50, Color.decode("#98b2ff"));
            playerSprite2 = new PlayerSprite(oguri, 100, 512, 50, Color.decode("#ffb098"));
        } else {
            playerSprite = new PlayerSprite(oguri, 100, 256, 50, Color.decode("#ffb098"));
            playerSprite2 = new PlayerSprite(agnes, 100, 512, 50, Color.decode("#98b2ff"));
        }
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
        if (playerSprite != null) {
            playerSprite.drawSprite(g2d);
        }
        if (playerSprite2 != null) {
            playerSprite2.drawSprite(g2d);
        }
        if (finishLine != null) {
            finishLine.drawFinishLine(g2d);
        }
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
