package client;

import model.ClientMsgCallback;
import model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private Socket clientSocket;
    private PrintWriter outputToServer;
    private BufferedReader inputFromServer;
    private String ADDRESS = "127.0.0.1";
    private int PORT = 6666;
    private ClientMsgCallback callback;
    private boolean clientConnected;

    public SocketClient() throws IOException {
        clientConnected = true;
        this.socketClient();
    }

    public void socketClient() throws IOException {
        try {
            clientSocket = new Socket(ADDRESS, PORT);
            outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientConnected = true;
            InputHandler inputHandler = new InputHandler();
            Thread ok = new Thread(inputHandler);
            ok.start();
        }catch (IOException e){
            closeClient();
            e.printStackTrace();
        }
    }

    public void sendToServer(String toServer) {
        outputToServer.println(toServer);
    }

    public void closeClient() throws IOException {
        clientSocket.close();
        outputToServer.close();
        inputFromServer.close();
        clientConnected = false;
    }

    public void setCallback(ClientMsgCallback callback) {
        this.callback = callback;
    }

    class InputHandler implements Runnable{
        @Override
        public void run() {
            try{
                while(clientConnected){
                    String input = inputFromServer.readLine();
                    String[] split = input.split(";");
                    System.out.println(split[0]);
                    Message message = null;

                    String body ="";
                    if(split[0].equals("Send Message")){
                        body = split[1];
                    }
                    if(split[0].equals("Cipher") || split[0].equals("Decipher")){
                        body = input.replaceFirst(split[0] + ";", "");
                    }
                    if(split[0].equals("getUser")) {
                        body = split[1];
                    }

                    message = new Message(split[0], body);

                    if(callback != null)
                        callback.processMsg(message);
                }
            } catch (IOException e){
                try {
                    closeClient();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
