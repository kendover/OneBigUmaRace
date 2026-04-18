import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player{
    
    private JFrame f;
    private JPanel cp;
    private int keyChecker;

    public void Timer(){

    }

    public Player(){
        f = new JFrame();
        cp = (JPanel) f.getContentPane();
        cp.setFocusable(true);

    }

    public void setUpGUI(){
        f.setSize(320, 240);
        f.setTitle("Player");
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    

    public void addKeyBindings(){

        ActionMap am = cp.getActionMap();
        InputMap im = cp.getInputMap();


        AbstractAction moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent ae){
                System.out.println("Haru Urara stepped forward with her left leg!");

            }
        };


        AbstractAction moveRight = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("Haru Urara stepped forward with her right leg!");
            }
        };

        am.put("ml", moveLeft);
        am.put("mr", moveRight);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "ml");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "mr");

    }
}