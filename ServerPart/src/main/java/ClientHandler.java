import ciphers.Ciphers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader inputFromClient;
    PrintWriter outputToClient;
    private HashMap<String, Ciphers> cipherMap;
    private Connection connection;

    public ClientHandler(Socket clientSocket, Connection connection) {
        this.connection = connection;
        this.socket = clientSocket;
    }

    public void run() {
        try {
            while (!socket.isClosed()) {
                outputToClient = new PrintWriter(socket.getOutputStream(), true);
                inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (socket.isConnected()) {
                    String message = inputFromClient.readLine();
                    if (message == "") {
                        continue;
                    }

                    new ClientMessageHandler(message,outputToClient,connection);
                }

                inputFromClient.close();
                outputToClient.close();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

}
