import java.awt.*;
import java.awt.geom.*;

import javax.swing.JFrame;
public class FinishLine {
    private int x, y, squareSize; //no need for w since it'll span the whole screen
    private int height = 768;

    public FinishLine(int x, int y){
        squareSize = 20;//adjust according to desired finish line
        this.x = x;
        this.y = y;
    }

    public void drawFinishLine(Graphics2D g2d) {
        for(int row = 0; row < (height/squareSize + 1); row++){
            for(int column = 0; column < 2; column++){
                if((row + column) % 2 == 0){
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(Color.BLACK);
                }
                g2d.fillRect(x + column * squareSize, y + row * squareSize, squareSize, squareSize);
            }
        }
    }

    public int getX(){
        return x;
    }
}
