import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Player{
    
    private GameFrame gf;
    private JPanel cp;
    private int keyChecker;

    private ClientSideConnection csc;

    public Player(){
        gf = new GameFrame();
        cp = (JPanel) gf.getContentPane();
        cp.setFocusable(true);
    }

    public void setUpGUI(){
        Container cp = gf.getContentPane();

        gf.setLayout(null);
        gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gf.setVisible(true);
        gf.setTitle("Final Project - Fernandez - Periña");
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
            }   catch(IOException ex){
                System.out.println("IO Exception from CSC constructor");
            }
        }
    }
}