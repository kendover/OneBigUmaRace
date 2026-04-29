import java.net.*;
import java.io.*;

public class GameServer {


    public static void main(String[] args) {
        ServerProgram sp = new ServerProgram();   
        sp.acceptConnections();
    }
}
