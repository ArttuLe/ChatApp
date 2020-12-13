package Server;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Class for handling the client connections.
 * 
 * 
 * 
 * 
 */
public class SocketListener extends Thread {
	private int port;
	private ArrayList<Socket> clientSockets;

	public SocketListener(int port) {
		this.port = port;
		clientSockets = new ArrayList<Socket>();

	}

	public void run() {
		ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("Server runnning at port: "+port+"...");

			} catch (IOException e) {
				System.out.println(e);
			}
			while(true){ //When connection is made, socket is handled over to another thread in order to listen for more clients
				try{
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client from "+clientSocket.getInetAddress()+" connected successfully...");
				clientSockets.add(clientSocket);
				Listener g = new Listener(clientSocket,clientSockets);
				System.out.println("Client handed over...Ready for new connections");
				g.start();
				}catch (IOException e){
					e.printStackTrace();
					System.out.println("Server error...");
				}
			}
			


	}
}