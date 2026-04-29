import java.net.*;
import java.io.*;

public class ServerProgram {
    private ServerSocket ss;
    private int numPlayers;
    private double playerOneScore, playerTwoScore;

    public ServerProgram(){
        System.out.println("game server");
        numPlayers = 0;
        try{
            ss = new ServerSocket(8888);
        }   catch(IOException ex) {
            System.out.println("IOException from ServerProgram Constructor!");
        }   
    }    

    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");
            while(numPlayers < 2){
                Socket s = ss.accept();
                numPlayers++;
                System.out.println("Players #" + numPlayers + " has connected.");
            }
            System.out.println("Party is now full. No longer accepting connections.");
        }   catch(IOException ex){
            System.out.println("IOException from acceptionConnections()");
        }
    }
    
}
