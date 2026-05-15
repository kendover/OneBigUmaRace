import java.net.*;
import java.io.*;

public class ClientProgram {
    public ClientProgram(){
        try{
            Socket s = new Socket("localhost",8888); //Change "myserver" to IP port of the server computer, w/ quotation marks

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeUTF("Hello");
            out.flush();
            String msg = in.readUTF();
            System.out.println("Server says " + msg);
        } catch(IOException ex){
            System.out.println("Client error!");
        }
    }
}
