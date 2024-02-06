package src.main.errors;


import src.main.config.SetupFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorSetupEnter extends JFrame {

    public ErrorSetupEnter() {
        JLabel msgConnectError = new JLabel();
        JLabel msgConnectTextError = new JLabel();
        JButton btn = new JButton();

        msgConnectError.setText("No settings entered");
        msgConnectError.setFont(new Font("Arial", Font.PLAIN, 14));
        msgConnectError.setForeground(Color.RED);
        msgConnectError.setBounds(100, 50, 200, 26);

        msgConnectTextError.setText("Restart the program after making the settings");
        msgConnectTextError.setFont(new Font("Arial", Font.PLAIN, 12));
        msgConnectTextError.setForeground(Color.BLACK);
        msgConnectTextError.setBounds(55, 110, 300, 26);

        btn.setText("OK");
        btn.setBounds(140, 200, 100, 26);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupFrame frame = new SetupFrame();
                ErrorSetupEnter.this.dispose();
            }
        });


        this.setTitle("No settings entered");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400, 300);


        this.add(msgConnectError);
        this.add(msgConnectTextError);
        this.add(btn);
    }

}
