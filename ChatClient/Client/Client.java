package Client;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import Server.Messages;

public class Client extends Thread  {

	private String host;
	private int port;
	private String input;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		System.out.println("Host name: " + host + " Port: " + port);
		try { 			// Yrittää yhdistää palvelimelle
			Socket clientSocket = new Socket(host, port);
			System.out.println("...Connected!");
			InputStream iS = clientSocket.getInputStream();
			OutputStream oS = clientSocket.getOutputStream();
			ObjectOutputStream toServer = new ObjectOutputStream(oS); 
			ObjectInputStream fromServer = new ObjectInputStream(iS);
			Scanner read = new Scanner(System.in); 
			try {
				while(input != "QUIT"){
    				String input = read.nextLine();
					ArrayList<Messages>messages = new ArrayList<>();
            		messages.add(new Messages(input));
            		toServer.writeObject(messages); //Olio lähetetään takaisin.
            		toServer.flush();//"Virta" tyhjennetään
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		/*	ArrayList<Messages> messages = (ArrayList<Messages>) fromServer.readObject();
			messages.forEach((msg)-> System.out.println(msg.getMessage())); */
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO Exception");
		}
	}

}