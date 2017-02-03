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

    public ControlPanel(Robot robot) throws HeadlessException {
        super("Control panel");
        this.robot = robot;

        setSize(350, 200);
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

        JLabel label = new JLabel("Robot's control panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelButton = new JPanel();
        panelButton.add(addButton);
        panelButton.add(removeButton);
        panelButton.add(statusButton);

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
        }


    }
}
