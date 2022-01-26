import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Vector;

public class SocketServer {
    private Socket clientSocket;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Connection connection;
    private int PORT = 6666;
    private int numOfClients = 1;
    private ServerSocket serverSocket = new ServerSocket(PORT);;
    private  Utility utility = new Utility();
    public static Vector<ClientHandler> listOfClients = new Vector<>();

    public SocketServer() throws IOException {
        utility.initHashMap();
        connection = databaseConnection.Connect();

        while (!serverSocket.isClosed()) {
            clientSocket = serverSocket.accept();
            System.out.println(numOfClients);
            ClientHandler handler = new ClientHandler(clientSocket, connection);
            listOfClients.add(handler);
            handler.start();
            numOfClients++;
        }
        serverSocket.close();
    }
}


