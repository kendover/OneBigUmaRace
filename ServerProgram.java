import java.io.*;
import java.net.*;

public class ServerProgram {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private double playerOneScore, playerTwoScore;

    public ServerProgram(){
        System.out.println("game server");
        numPlayers = 0;
        maxPlayers = 2;
    
        try{
            ss = new ServerSocket(8888);
        }   catch(IOException ex) {
            System.out.println("IOException from ServerProgram constructor: unable to open port 8888");
            ex.printStackTrace(System.out);
            ss = null;
        }   
    }    

    public void acceptConnections(){
        if (ss == null) {
            System.out.println("Server socket is not initialized. Cannot accept connections.");
            return;
        }

        try{
            System.out.println("Waiting for connections...");
            while(numPlayers < maxPlayers){
                Socket s = ss.accept();
                numPlayers++;
                System.out.println("Players #" + numPlayers + " has connected.");

                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                out.writeInt(numPlayers);
                out.flush();
                System.out.println("Sent player ID " + numPlayers + " to client.");
            }
            System.out.println("Party is now full. No longer accepting connections.");
        
        }   catch(Exception ex){
            System.out.println("Exception in acceptConnections()");
            ex.printStackTrace(System.out);
        }
    }

    public int getNumPlayers(){
        return numPlayers;
    }

}
