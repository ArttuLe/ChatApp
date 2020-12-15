package Client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * 
 * A Class for sending messages to the server/other clients
 */


public class Client extends Thread  {

	private String host;
	private int port;
	private String input;
	private String name;
	DataOutputStream toServer;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		System.out.println("Host name: " + host + " Port: " + port);
		try { 										//Connects to the Server, IOException if it fails to connect
			Scanner read = new Scanner(System.in);
			Socket clientSocket = new Socket(host, port);
			ClientListen listen = new ClientListen(clientSocket);
			listen.start(); 						//Start a separate thread for listening the server for messages
			System.out.println("...Connected!\nPlease enter your name: ");
			name = read.nextLine();
			name += ": ";
			System.out.println("Start chatting!");
			DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream()); 
				while(true){
					try{
					input = name+read.nextLine();
					toServer.writeUTF(input);
					toServer.flush();
					}catch(IOException e){
						e.printStackTrace();
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Couldn't connect to the server...");
		}
	}

}