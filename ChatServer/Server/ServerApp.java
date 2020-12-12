package Server;
public class ServerApp {

	private static ServerApp app;
	private SocketListener socketlisten;
	private String serverIP = "127.0.0.1";
	private int serverPort = 3000;

	public ServerApp() {
		socketlisten = new SocketListener(serverIP, serverPort);
		socketlisten.start();
	}

	public static void main(String[] args) {

		System.out.println("Starting server...");
		app = new ServerApp();
		
	}

}