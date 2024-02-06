package src.main.config;

import com.fazecast.jSerialComm.*;
import src.main.dataHandler.DataReader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SetupFrame extends JFrame {

    public String username = System.getProperty("user.name");
    public SerialPort[] portLists = SerialPort.getCommPorts();
    String firstAppKeyInput;
    String firstSecretKeyInput;
    String firstUrlKeyInput;
    String firstComInput;




    public SetupFrame() {
        Border borderInput = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);


        String[] comLista = new String[portLists.length];
        for (int i=0; i < portLists.length; i++){
            String tempPort = portLists[i].getSystemPortName();
            comLista[i] = tempPort;
        }




        JLabel appKey = new JLabel("App key: ");
        JLabel secretKey = new JLabel("Secretkey:  ");
        JLabel urlBarcode = new JLabel("URL Barcode:  ");
        JLabel comList = new JLabel("Serial port list:  ");

        JTextField appKeyInput = new JTextField(150);
        JTextField secretKeyInput = new JTextField(150);
        JTextField urlBarcodeInput = new JTextField(150);
        JComboBox portList = new JComboBox(comLista);


        JButton buttonSave = new JButton("Save");

        // LABELS
        appKey.setFont(new Font("Arial", Font.PLAIN, 14));
        appKey.setForeground(Color.WHITE);
        appKey.setBounds(50, 50, 110, 26);

        secretKey.setFont(new Font("Arial", Font.PLAIN, 14));
        secretKey.setForeground(Color.WHITE);
        secretKey.setBounds(50, 100, 110, 26);

        urlBarcode.setFont(new Font("Arial", Font.PLAIN, 14));
        urlBarcode.setForeground(Color.WHITE);
        urlBarcode.setBounds(50, 150, 110, 26);

        comList.setFont(new Font("Arial", Font.PLAIN, 14));
        comList.setForeground(Color.WHITE);
        comList.setBounds(50, 200, 110, 26);


        // INPUTS
        appKeyInput.setBounds(200, 50, 300, 26);
        appKeyInput.setBorder(borderInput);
        appKeyInput.setFont(new Font("Arial", Font.PLAIN, 14));
        appKeyInput.setText(new DataReader().getAppKay());

        secretKeyInput.setBounds(200, 100, 300, 26);
        secretKeyInput.setBorder(borderInput);
        secretKeyInput.setFont(new Font("Arial", Font.PLAIN, 14));
        secretKeyInput.setText(new DataReader().getSecretKey());

        urlBarcodeInput.setBounds(200, 150, 300, 26);
        urlBarcodeInput.setBorder(borderInput);
        urlBarcodeInput.setFont(new Font("Arial", Font.PLAIN, 14));
        urlBarcodeInput.setText(new DataReader().getURLBarcode());

        portList.setBounds(200, 200, 300, 26);
        portList.setBorder(borderInput);
        portList.setFont(new Font("Arial", Font.PLAIN, 14));




        // BUTTON
        buttonSave.setBounds(450, 300, 70, 26);
        buttonSave.setBorder(borderInput);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appKeyInputValue = appKeyInput.getText();
                String secretKeyInputValue = secretKeyInput.getText();
                String urlBarcodeInputValue = urlBarcodeInput.getText();
                String portListValue = portList.getItemAt(portList.getSelectedIndex()).toString();

                try {
                    PrintWriter out = new PrintWriter("C:\\Users\\" + username + "\\Path\\to\\setup\\file.ini");
                    out.println(appKeyInputValue);
                    out.println(secretKeyInputValue);
                    out.println(urlBarcodeInputValue);
                    out.println(portListValue);
                    firstAppKeyInput = appKeyInputValue;
                    firstSecretKeyInput = secretKeyInputValue;
                    firstUrlKeyInput = urlBarcodeInputValue;
                    firstComInput = portListValue;

                    out.close();

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                System.exit(0);
            }
        });



        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 400);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        // LABELS
        this.add(appKey);
        this.add(secretKey);
        this.add(urlBarcode);
        this.add(comList);

        // INPUTS FIELDS
        this.add(appKeyInput);
        this.add(secretKeyInput);
        this.add(urlBarcodeInput);
        this.add(portList);

        //BUTTON
        this.add(buttonSave);

        }

    public String getFirstAppKeyInput() {
        return firstAppKeyInput;
    }

    public String getFirstSecretKeyInput() {
        return firstSecretKeyInput;
    }

    public String getFirstUrlKeyInput() {
        return firstUrlKeyInput;
    }

    public String getFirstComInput() {
        return firstComInput;
    }
}


