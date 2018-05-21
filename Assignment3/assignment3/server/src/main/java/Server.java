
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    int serverPort = 6969;
    private List<ClientManager> observers = new ArrayList<>();
    private static Server server;


    private Server() {
    }

    public static Server getInstance() {
        if (server == null) {
            server = new Server();
        }

        return server;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Listening on port: " + this.serverPort);
            System.out.println(serverSocket.getInetAddress());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inStream = new ObjectInputStream(clientSocket.getInputStream());
                ClientManager clientManager = new ClientManager(outStream,inStream);
                Thread handleClientThread = new Thread(clientManager);
                handleClientThread.start();
                observers.add(clientManager);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = Server.getInstance();
        server.startServer();

    }

}
