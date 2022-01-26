package controllers;

import client.SocketClient;
import view.LogGui;
import model.ClientMsgCallback;
import model.Message;

import javax.swing.*;
import java.io.IOException;

public class LoginController {

    private LogGui logGui;
    private SocketClient socketClient;
    private String user;

    public LoginController() throws IOException {
        socketClient = new SocketClient();
        logGui = new LogGui();
        loginController();
    }

    public void loginController() {
        this.logGui.getButtonDo().addActionListener(e -> {
            user = logGui.getUsernameInput().getText();
            socketClient.sendToServer("getUser" + "#" + user + "#" + null + "#" + null);
        });

        socketClient.setCallback(new ClientMsgCallback() {
            @Override
            public void processMsg(Message message) {
                if (message.getHeader().equals("getUser")) {
                    if (message.getBody().equals("true")) {
                        logGui.getFrame().dispose();
                        try {
                            new AppController(user, socketClient);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String error  = "User does not exist.";
                        JOptionPane.showMessageDialog(null, error);
                    }
                }
            }
        });


    }
}
