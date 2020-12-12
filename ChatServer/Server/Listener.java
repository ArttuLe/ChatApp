package Server;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;



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
            // IO-streamit TCP yhteyden välillä lähettelyyn.
            InputStream iS = socket.getInputStream();
            OutputStream oS = socket.getOutputStream();
           ObjectOutputStream toClient = new ObjectOutputStream(oS);
           ObjectInputStream fromClient = new ObjectInputStream(iS);
           try{
           while(true){ //"keskustelu" asiakkaan kanssa tapahtuu täällä
            ArrayList<Messages> messages = (ArrayList<Messages>) fromClient.readObject();
			messages.forEach((msg)-> System.out.println(msg.getMessage()));
            }
           }catch(IOException | ClassNotFoundException e) { // Asiakkaalta tultava jotenkin IOException, että yhteys katkeaa
               toClient.close();
               fromClient.close();
           }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Disconnected...");
        }
    }

}

