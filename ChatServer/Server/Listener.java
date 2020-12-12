package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



/**
 * Class for handling all the messaging between clients
 * 
 * 
 * 
 */

public class Listener extends Thread{


    private String message;
    private Socket socket;

    public Listener(Socket clientSocket){
     this.socket = clientSocket;
    }

    public void run(){ 
        try {
            System.out.println("Client successfully connected...");
            // IO-streamit TCP yhteyden välillä lähettelyyn.
            InputStream iS = socket.getInputStream();
            OutputStream oS = socket.getOutputStream();
           DataOutputStream toClient = new DataOutputStream(oS);
           DataInputStream fromClient = new DataInputStream(iS);
        
           try{
           while(true){ //"Chatting" happens inside this loop
                String temp = fromClient.readUTF();
                System.out.println(temp);
            }
           }catch(IOException e) { // Asiakkaalta tultava jotenkin IOException, että yhteys katkeaa
               toClient.close();
               fromClient.close();
           }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Disconnected...");
        }
    }

}

