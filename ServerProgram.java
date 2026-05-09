import java.io.*;
import java.net.*;

public class ServerProgram {
    private ServerSocket ss;
    private int numPlayers, maxPlayers, playersID, otherPlayer;
    private ServerSideConnection player1, player2;
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
                ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);
                if(numPlayers == 1){
                    player1 = ssc;
                } else{
                    player2 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();
                
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

    private class ServerSideConnection implements Runnable{
        
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;

        public ServerSideConnection(Socket s, int id) {
            socket = s;
            playerID = id;
            try{
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            }catch (IOException ex) {
                System.out.println("IOException from SSC Constructor");
            }
        }

        public void run(){
            try{
                dataOut.writeInt(playerID);
                dataOut.flush();
                while(true){
                    
                }
            }catch (IOException ex){
                System.out.println("IOException from run() SSC");
            }
        }
    }

}
