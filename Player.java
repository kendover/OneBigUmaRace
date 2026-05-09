import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Player{
    
    private GameFrame gf;
    private JPanel cp;
    private int foot;
    private int playerID;
    private ClientSideConnection csc;
    private PlayerSprite ps, ps2; 
    private double x, y;
    private boolean forward;
    
    

    public Player(){
        foot = 1;
        gf = new GameFrame();
        cp = (JPanel) gf.getContentPane();
        cp.setFocusable(true);
        gf.getGameCanvas().setPlayerSprite();
        ps = gf.getGameCanvas().getPlayerSprite();
        ps2 = gf.getGameCanvas().getPlayerSprite2();
    }

    public void AnimationTimer(){
        Timer t = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                if(forward){
                    ps.moveH(3);
                    forward = false;
                } else if (!forward) {
                    ps.moveH(-3);
                }
                gf.getGameCanvas().repaint();
            }
        });
        t.start();
    }

    public void setUpGUI(){
        Container cp = gf.getContentPane();
        gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gf.setVisible(true);
        gf.setTitle("Final Project - Fernandez - Periña");

        this.AnimationTimer();
    }

    public void connectToServer(){
        csc = new ClientSideConnection();
    }

    public void addKeyBindings(){

        ActionMap am = cp.getActionMap();
        InputMap im = cp.getInputMap();


        AbstractAction moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent ae){
                System.out.println("Haru Urara stepped forward with her left leg!");
                if (foot == 1){
                    forward = true;
                    foot = 2;
                }
                else{
                    System.out.println("Wrong leg bro");
                }
            }
        };


        AbstractAction moveRight = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("Haru Urara stepped forward with her right leg!");
                if (foot == 2) {
                    forward = true;
                    foot = 1;
                } else {
                    System.out.println("Wrong leg bro");
                }
            }
        };

        am.put("ml", moveLeft);
        am.put("mr", moveRight);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "ml");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "mr");

    }

    private class ClientSideConnection{
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        public ClientSideConnection(){
            System.out.println("client");
            try{
                socket = new Socket("localhost", 8888);
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                playerID = dataIn.readInt();
                System.out.println("Player ID #" + playerID);
            }   catch(IOException ex){
                System.out.println("IO Exception from CSC constructor");
                ex.printStackTrace(System.out);
            }
        }
    }
}