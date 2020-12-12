package Client;





public class ClientApp{
    private static ClientApp app;
    private Client client;
    private String host = "127.0.0.1";
    private int port = 3000;

    public ClientApp(){
        client = new Client(host, port);
        client.start();

    }

    public static void main(String args[]){
        System.out.println("Connecting to the server...");
        app = new ClientApp();

    }
}
