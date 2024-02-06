package src.main.errors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorBarcodeConnect extends JFrame {

    public ErrorBarcodeConnect() {
        JLabel msgConnectError = new JLabel();
        JButton btn = new JButton();

        msgConnectError.setText("No barcode connection");
        msgConnectError.setFont(new Font("Arial", Font.PLAIN, 14));
        msgConnectError.setForeground(Color.RED);
        msgConnectError.setBounds(70, 30, 200, 26);

        btn.setText("Exit");
        btn.setBounds(95, 80, 100, 26);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        this.setTitle("No barcode connection");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 200);


        this.add(msgConnectError);
        this.add(btn);



    }



}
