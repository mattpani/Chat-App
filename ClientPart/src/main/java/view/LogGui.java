package view;

import javax.swing.*;
import java.awt.*;

public class LogGui {

    private final JFrame frame = new JFrame("LOGIN");
    private final JPanel panel = new JPanel();
    private final GridBagLayout layout = new GridBagLayout();
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private final JTextField usernameInput = new JTextField(null);
    private final JLabel usernameLabel = new JLabel("Username");
    private final JButton buttonDo = new JButton("Log in");

    public LogGui(){
        this.logGui();
    }
    public void logGui(){
        this.frame.setSize(500,300);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.gridBagConstraints.fill = 2;
        this.gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        this.panel.setLayout(this.layout);


        this.addComponentToGrid(this.panel,new Component[]{this.usernameLabel,this.usernameInput,this.buttonDo},
                new int[][]{new int[2], {1,0},{0,1},{2,0}},
                new int[] {1,10,2},
                new int[] {1,1,2});
        this.frame.add(this.panel);
        this.frame.setVisible(true);
    }

    private void addComponentToGrid(JPanel panel, Component[] components, int[][] coords, int[] weightX, int[] width) {
        for(int i = 0; i < components.length; ++i) {
            this.gridBagConstraints.weightx = weightX[i];
            this.gridBagConstraints.gridx = coords[i][0];
            this.gridBagConstraints.gridy = coords[i][1];
            this.gridBagConstraints.gridwidth = width[i];
            panel.add(components[i], this.gridBagConstraints);
        }

    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JButton getButtonDo() {
        return this.buttonDo;
    }

    public JTextField getUsernameInput() {
        return this.usernameInput;
    }
}
