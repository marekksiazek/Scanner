package src.main.errors;


import src.main.config.SetupFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterError extends JFrame{
    public RegisterError() {
        JLabel msgConnectError = new JLabel();
        JButton btn = new JButton();

        msgConnectError.setText("REGISTRATION ERROR");
        msgConnectError.setFont(new Font("Arial", Font.PLAIN, 14));
        msgConnectError.setForeground(Color.RED);
        msgConnectError.setBounds(80, 30, 200, 26);

        btn.setText("OK");
        btn.setBounds(95, 80, 100, 26);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupFrame frame = new SetupFrame();
                RegisterError.this.dispose();
            }
        });


        this.setTitle("REGISTRATION ERROR");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 200);


        this.add(msgConnectError);
        this.add(btn);
    }
}
