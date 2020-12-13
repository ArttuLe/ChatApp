package Server;

import java.io.*;
import java.net.Socket;



/**
 * Class for handling all the messaging between clients
 * 
 * 
 * 
 */

public class Listener extends Thread{


    private Socket socket;

    public Listener(Socket clientSocket){
     this.socket = clientSocket;
    }

    public void run(){ 
        try {
            System.out.println("Client successfully connected...");
            // IO-streams for sending messages through TCP
            InputStream iS = socket.getInputStream();
            OutputStream oS = socket.getOutputStream();
           DataOutputStream toClient = new DataOutputStream(oS);
           DataInputStream fromClient = new DataInputStream(iS);
        
           try{
           while(true){ //"Chatting" happens inside this loop
                String temp = fromClient.readUTF();
                System.out.println(temp);
            }
           }catch(IOException e) { //Connection closes at IOException which will be caused by the client
               toClient.close();
               fromClient.close();
           }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Disconnected...");
        }
    }

}

