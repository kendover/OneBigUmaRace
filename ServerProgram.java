import java.io.*;
import java.net.*;

public class ServerProgram {
    private ServerSocket ss;
    private int numPlayers, maxPlayers, readyPlayers;
    private double p1x, p1y, p2x, p2y;

    private Socket p1Socket, p2Socket;
    private ReadFromClient p1ReadRunnable;
    private ReadFromClient p2ReadRunnable;
    private WriteToClient p1WriteRunnable;
    private WriteToClient p2WriteRunnable;



    public ServerProgram(){
        System.out.println("===== GAME SERVER =====");
        numPlayers = 0;
        maxPlayers = 2;
        readyPlayers = 0;
        
        //update values if we decide to change starting location
        p1x = 100;
        p1y = 256;
        p2x = 100;
        p2y = 512;

        try{
            ss = new ServerSocket(6767);
        }   catch(IOException ex) {
            System.out.println("IOException from ServerProgram constructor: unable to open port 8888");
            //ex.printStackTrace(System.out);
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
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Players #" + numPlayers + " has connected."); 

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if(numPlayers == 1){
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else{
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();

                }
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

    private class ReadFromClient implements Runnable {
        private int playerID;
        private DataInputStream dataIn;

        public ReadFromClient(int pid, DataInputStream in){
            playerID = pid;
            dataIn = in;
            System.out.println("RFC " + pid + " Runnable created");
        } 

        public void run(){
            try{
                while(true){
                    if(playerID == 1){
                        p1x = dataIn.readDouble();
                        p1y = dataIn.readDouble();
                    } else{
                        p2x = dataIn.readDouble();
                        p2y = dataIn.readDouble();
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from RFC run");
            }
        }
    }

    private class WriteToClient implements Runnable {
        private int playerID;
        private DataOutputStream dataOut;

        public WriteToClient(int pid, DataOutputStream out){
            playerID = pid;
            dataOut = out;
            System.out.println("WTC " + pid + " Runnable created");
        } 

        public void run(){
            try{
                while(true){
                    if(playerID == 1){
                        dataOut.writeDouble(p2x);
                        dataOut.writeDouble(p2y);
                        dataOut.flush();
                    } else{
                        dataOut.writeDouble(p1x);
                        dataOut.writeDouble(p1y);
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(25);
                    } catch(InterruptedException ex){
                        System.out.println("Interrupted from WTC run()");
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from WTC run()");
            }
        }

        public void sendStartMsg(){
            try {
                dataOut.writeUTF("Both umas are now ready. Go!");
            } catch (IOException ex) {
                System.out.println("IOException from sendStartMsg()");
            }
        }
    }

}
