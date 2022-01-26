package view;

import ciphers.Ciphers;

import javax.swing.*;
import java.awt.*;

public class AppGui {

    private final JFrame frame = new JFrame("SIFRATOR");
    private final JPanel panel = new JPanel();
    private final GridBagLayout layout = new GridBagLayout();
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private final JRadioButton cipherButton = new JRadioButton("Cipher");
    private final JRadioButton decipherButton = new JRadioButton("Decipher");
    private final JTextField cipherInput = new JTextField(null);
    private final JTextField cipherOutput = new JTextField(null);
    private final JLabel labelInput = new JLabel("Input");
    private final JLabel labelOutput = new JLabel("Output");
    private final JComboBox<Ciphers> comboBox;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final JButton buttonDo = new JButton("Do the thing");

    private final JTextArea textArea = new JTextArea();
    private final JLabel chatRoom = new JLabel("Chat room");
    private final JButton sendMessage = new JButton("Send Message");
    private final JTextField messageInput = new JTextField(null);
    private final JLabel labelMessageInput = new JLabel("Message");


    public AppGui(Ciphers[] ciphers) {

        this.comboBox = new JComboBox<Ciphers>(ciphers);
        this.appGuiInit();
    }

    public void appGuiInit() {
        this.cipherOutput.setEditable(false);
        this.frame.setSize(500, 650);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.buttonGroup.add(this.cipherButton);
        this.buttonGroup.add(this.decipherButton);
        this.gridBagConstraints.fill = 2;
        this.gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        this.panel.setLayout(this.layout);
        this.textArea.setPreferredSize(new Dimension(250, 250));
        this.textArea.setEditable(false);

        this.addComponentToGrid(this.panel, new Component[]{this.labelInput, this.cipherInput, this.labelOutput, this.cipherOutput, this.cipherButton, this.decipherButton, this.comboBox, this.buttonDo, this.chatRoom, this.textArea, this.labelMessageInput, this.messageInput, this.sendMessage},
                new int[][]{new int[2], {1, 0}, {0, 1}, {1, 1}, {0, 2}, {0, 3}, {1, 2}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {1,7 }, {0, 9}},
                new int[]{1, 10, 1, 10, 5, 5, 7, 3, 10, 10, 2, 7, 1},
                new int[]{1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3}
        );
        this.frame.add(this.panel);
        this.frame.setVisible(true);

    }

    private void addComponentToGrid(JPanel panel, Component[] components, int[][] coords, int[] weightX, int[] width) {
        for (int i = 0; i < components.length; ++i) {
            this.gridBagConstraints.weightx = weightX[i];
            this.gridBagConstraints.gridx = coords[i][0];
            this.gridBagConstraints.gridy = coords[i][1];
            this.gridBagConstraints.gridwidth = width[i];
            panel.add(components[i], this.gridBagConstraints);
        }

    }


    public JButton getBtnDo() {

        return this.buttonDo;
    }

    public JRadioButton getRadioButtonCipher() {

        return this.cipherButton;
    }

    public JComboBox<Ciphers> getComboBox() {

        return this.comboBox;
    }

    public JTextField getCipherInput() {

        return this.cipherInput;
    }

    public JTextField getCipherOutput() {

        return this.cipherOutput;
    }

    public JRadioButton getDecipherButton() {
        return this.decipherButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getSendMessage(){
        return sendMessage;
    }

    public JTextField getMessageInput() {
        return messageInput;
    }
}
