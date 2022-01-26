package controllers;

import client.SocketClient;
import view.AppGui;
import ciphers.Ciphers;
import ciphers.RSA;
import ciphers.RightRightShift;
import ciphers.RightShift;
import model.ClientMsgCallback;
import model.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AppController extends Thread {

    private AppGui appGui;
    private Ciphers rsa = new RSA();
    private Ciphers right = new RightShift();
    private Ciphers rightRight = new RightRightShift();
    public static String whatToDo = "s";

    public AppController(String user, SocketClient socketClient) throws IOException {
        Ciphers[] ciphers = {right, rightRight, rsa};
        this.appGui = new AppGui(ciphers);
        JComboBox<Ciphers> comboBox = appGui.getComboBox();

        this.appGui.getBtnDo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputCiDe = AppController.this.appGui.getCipherInput().getText();
                Ciphers selectedItem = (Ciphers) comboBox.getSelectedItem();

                if (appGui.getRadioButtonCipher().isSelected()) {
                    whatToDo = "Cipher";
                }
                if (appGui.getDecipherButton().isSelected()) {
                    whatToDo = "Decipher";
                }
                socketClient.sendToServer(mesToString(inputCiDe, selectedItem, whatToDo, user));
            }
        });

        this.appGui.getSendMessage().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputMessage = AppController.this.appGui.getMessageInput().getText();

                socketClient.sendToServer(mesToString(inputMessage, null, "Send Message", user));
            }
        });

        socketClient.setCallback(new ClientMsgCallback() {
            @Override
            public void processMsg(Message message) {
                String action = message.getHeader();
                switch (action){
                    case "Send Message":
                        appGui.getTextArea().append(message.getBody() + "\n");
                        break;
                    default:
                        appGui.getCipherOutput().setText(message.getBody());
                }
            }
        });
    }

    public String mesToString(String toServer, Ciphers selectedItem, String action, String user) {
        String toServerMessage = action + "#" + toServer + "#" + selectedItem + "#" + user;

        return toServerMessage;
    }
}
