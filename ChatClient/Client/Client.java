package Client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

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
			DataOutputStream toServer = new DataOutputStream(oS); 
			DataInputStream fromServer = new DataInputStream(iS);
			Scanner read = new Scanner(System.in); 
			try {
				while(input != "QUIT"){
					input= read.nextLine();
					toServer.writeUTF(input);
					toServer.flush();
				}
				toServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO Exception");
		}
	}

}