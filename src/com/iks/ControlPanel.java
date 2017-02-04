package com.iks;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Prog on 03.02.2017.
 */
public class ControlPanel extends JFrame implements ActionListener{

    private Robot robot;
    private JTextArea mainScreen;

    public ControlPanel(Robot robot) throws HeadlessException {
        super("Control panel");
        this.robot = robot;

        setSize(800, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton addButton = new JButton("Add leg");
        addButton.addActionListener(this);
        addButton.setActionCommand("addLeg");
        JButton removeButton = new JButton("Remove leg");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("removeLeg");
        JButton statusButton = new JButton("Status");
        statusButton.addActionListener(this);
        statusButton.setActionCommand("status");
        JPanel panelButton = new JPanel();
        panelButton.add(addButton);
        panelButton.add(removeButton);
        panelButton.add(statusButton);

        JLabel label = new JLabel("Robot's control panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        mainScreen = new JTextArea();
        mainScreen.setBackground(Color.BLACK);
        mainScreen.setForeground(Color.YELLOW);
        mainScreen.setFont(new Font("Arial", Font.BOLD, 12));

        add(mainScreen, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
        add(label, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "status": {
                robot.printStatus();
                break;
            }
            case "addLeg": {
                robot.addLeg();
                break;
            }
            case "removeLeg":{
                robot.removeLeg();
                break;
            }
        }


    }

    public void addLineToMainScreen(String line){

        mainScreen.append(line + "\n");

    }
}
