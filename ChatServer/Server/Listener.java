package Server;

import java.util.*;
import java.io.*;
import java.net.Socket;

/**
 * Class for handling all the messaging between clients
 * 
 * 
 * 
 */

public class Listener extends Thread {

    private ArrayList<Socket> clientSockets;
    private Socket socket;
    private String message;
    DataInputStream fromClient;
    DataOutputStream toClient;

    public Listener(Socket clientSocket, ArrayList<Socket> clientSockets) throws IOException {
        this.socket = clientSocket;
        this.clientSockets = clientSockets;
    }

    public void write(String message) { // send a message to one client
        try {
            toClient.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        for (Socket socket : clientSockets) {
            try {
                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void run(){ 
        try {
            System.out.println("Client retrieved by the handler...");
            // IO-streams for sending messages through TCP
        try{
           while(true){ //"Chatting" happens inside this loop
               String temp = fromClient.readUTF();
                send(temp);
                
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

