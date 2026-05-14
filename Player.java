import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Player{

    //meta fields
    private GameFrame gf;
    private JPanel cp;
    private int playerID;
    private ClientSideConnection csc;
    private Socket socket;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private Container contentPane;

    //position/identifier fields
    private int foot;
    private PlayerSprite mainChar, mainOpp; 
    private double x, y;
    private boolean forward;
    private int width = 1024;
    private int height = 768;
    
    

    public Player(){
        foot = 1;
    }

    public void AnimationTimer(){
        Timer t = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                if(forward){
                    mainChar.moveH(3);
                    forward = false;
                } else if (!forward) {
                    mainChar.moveH(-1.5);
                }
                gf.getGameCanvas().repaint();
            }
        });
        t.start();
    }

    public void setUpGUI(){
        gf = new GameFrame();
        contentPane = gf.getContentPane();
        gf.setTitle("Final Project - Fernandez - Periña");
        contentPane.setSize(new Dimension(width, height));
        gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gf.setVisible(true);
        gf.getGameCanvas().setPlayerSprite();

        cp = (JPanel) gf.getContentPane();
        cp.setFocusable(true);

        assignSprite(); 
        addKeyBindings();
        AnimationTimer();

        cp.requestFocusInWindow();
    }

    public void assignSprite(){
        if(playerID == 1){
            mainChar = gf.getGameCanvas().getPlayerSprite();
            mainOpp = gf.getGameCanvas().getPlayerSprite2();
        } else{
            mainChar = gf.getGameCanvas().getPlayerSprite2();
            mainOpp = gf.getGameCanvas().getPlayerSprite();
        }
    }

    public void connectToServer(){
        try{
            socket = new Socket("localhost", 6767);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("Connected to server as Player ID #" + playerID + ".");
            if(playerID == 1) {
                System.out.println("Waiting for Player #2 to connect...");
            }
            rfsRunnable = new ReadFromServer(in); 
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();  
        }   catch(IOException ex){
            System.out.println("IO Exception from CSC constructor");
            ex.printStackTrace(System.out);
        }
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

    }

    private class ReadFromServer implements Runnable{
        
        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in){
            dataIn = in;
            System.out.println("RFS Runnable created");
        }
        public void run(){
            try{
                while(true){
                    double enemyX = dataIn.readDouble();
                    double enemyY = dataIn.readDouble();
                    if(mainOpp != null){
                        mainOpp.setX(enemyX);
                        mainOpp.setY(enemyY);
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from RFS run()");
            }
        }

        public void waitForStartMsg() {
            try {
                String startMsg = dataIn.readUTF();
                System.out.println("Message from server: " + startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();
                setUpGUI();
            } catch (IOException ex) {
                System.out.println("IOException from waitForStartMsg()");
            }
        }
    }

    private class WriteToServer implements Runnable{

        private DataOutputStream dataOut;

        public WriteToServer(DataOutputStream out){
            dataOut = out;
            System.out.println("WFS Runnable created");
        }
        public void run(){ //sends the x and y of our player sprite
            try{
                while(true){
                    if(mainChar != null){
                        dataOut.writeDouble(mainChar.getX());
                        dataOut.writeDouble(mainChar.getY());
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(25);
                    } catch(InterruptedException ex){
                        System.out.println("InterruptedException from WTS run()");
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from WTS run()");
            }
        }
    }
}