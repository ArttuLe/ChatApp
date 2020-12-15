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

    private ArrayList<Socket> clients;
    private Socket socket;
    private String message;
    DataInputStream fromClient;
    DataOutputStream toClient;

    public Listener(Socket clientSocket, ArrayList<Socket> clients) throws IOException {
        this.socket = clientSocket;
        this.clients = clients;
    }
    


    public void run(){ 
        try {
            System.out.println("Client retrieved by the handler...");
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            DataInputStream fromClient = new DataInputStream(socket.getInputStream());
            clients.add(socket); 
            try {
                while(true){//Broadcast the received message to all the other clients
                message = fromClient.readUTF();
                System.out.println(message);
                for (int i = 0; i < clients.size();i++) {
                    Socket temp = (Socket) clients.get(i);
                    if(socket.equals(temp)){
                        continue;
                    }
                    DataOutputStream toClients = new DataOutputStream(temp.getOutputStream());
                    toClients.writeUTF(message);
                    toClients.flush();
                }
                }
            } catch (Exception e) {
                toClient.close();
                fromClient.close();
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connection failed...");
        }
    }

}

