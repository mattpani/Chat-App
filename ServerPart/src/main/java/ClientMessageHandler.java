import ciphers.Ciphers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientMessageHandler {
    private PrintWriter output ;
    private Connection connection;

    public ClientMessageHandler(String messageFromServer, PrintWriter outputToClient, Connection connection) throws SQLException {
        this.output = outputToClient;
        this.connection = connection;
        deserialization(messageFromServer);
    }

    public void deserialization(String messageFromServer) throws SQLException {
        String[] newString = messageFromServer.split("#");
        String action = newString[0];
        String clientMessage = newString[1];
        String selectedMethod = newString[2];
        String user = newString[3];

        handleDeserializedMessage(action, clientMessage, selectedMethod, user);
    }

    private void handleDeserializedMessage(String action, String clientMessage, String selectedMethod, String user) throws SQLException {
        Ciphers kindCipher = Utility.cipherMap.get(selectedMethod);
        switch (action) {
            case "Cipher":
                output.println("Cipher;" + kindCipher.cipher(clientMessage));
                break;

            case "Decipher":
                output.println("Decipher;" + kindCipher.cipher(clientMessage));
                break;

            case "Send Message":
                for (ClientHandler handler : SocketServer.listOfClients) {
                    String sup = "Send Message" + ";" + user + ":" + clientMessage;
                    handler.outputToClient.println(sup);
                }
                break;

            case "getUser":
                String sql = "SELECT * FROM users where user_name=" + "('" + clientMessage + "')";
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery(sql);
                if (result.next()) {
                    output.println("getUser;" + "true");
                    return;
                }
                output.println("getUser;" + "false");

        }
    }


}
