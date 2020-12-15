package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
/**
 * Class for Listening messages coming from the server.
 * 
 * 
 * 
 */
public class ClientListen extends Thread{


    private Socket socket;
    private DataInputStream fromServer;

    public ClientListen(Socket socket) throws IOException {
        this.socket = socket;
        fromServer = new DataInputStream(socket.getInputStream());
	}
    @Override
    public void run(){

        try {
            while(true){
                String message = fromServer.readUTF();
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




    }




}