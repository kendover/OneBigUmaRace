import java.awt.*;

public class PlayerSprite {
    private Image img;
    private double x, y, size; 
    private Color color;

    public PlayerSprite(Image i, double a, double b, double s, Color c) {
        img = i;
        x = a;
        y = b;
        size = s;
        color = c;
    }

    public void drawSprite(Graphics2D g2d) {
        if (img != null) {
            g2d.drawImage(img, (int) x, (int) y, (int) size, (int) size, null);
        } else {
            g2d.setColor(color);
            g2d.fillRect((int) x, (int) y, (int) size, (int) size);
        }
    }

    public void moveH(double n) {x += n;}
    public void moveV(double n) {y += n;}
   
    public void setX(double n) { x = n; }
    public void setY(double n) { y = n; }

    public double getX() { return x; }
    public double getY() { return y; }
}
