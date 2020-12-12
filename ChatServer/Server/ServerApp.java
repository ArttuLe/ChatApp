package Server;

public class ServerApp {

	private static ServerApp app;
	private SocketListener socketlisten;
	private int serverPort = 3000;

	public ServerApp() {
		socketlisten = new SocketListener(serverPort);
		socketlisten.start();
	}

	public static void main(String[] args) {

		System.out.println("Starting server...");
		app = new ServerApp();
		
	}

}