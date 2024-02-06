package src.main.scan;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import src.main.dataHandler.DataReader;
import src.main.errors.ConnectionError;
import src.main.request.InternetConnection;
import src.main.request.KeyHack;
import src.main.request.RegisterRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class Scan {


    String dataBuffer ="";

    private String messages = "";
    RegisterRequest registerRequest = new RegisterRequest();

    public Scan() throws IOException {
    }

    public void Serial_EventBaseReading(SerialPort activePort) {
        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {

                messages += new String(serialPortEvent.getReceivedData());
                while (messages.contains("\n") || messages.contains("\r")){
                    InternetConnection internetConnection = new InternetConnection();
                    boolean connection = internetConnection.netIsAvailable();

                    if (!connection) {
                        ConnectionError connectionError = new ConnectionError();
                    }
                    String[] message = messages.split("\\n", 2);
                    messages = (message.length > 1) ? message[1] : "";
                    String trimMessage = message[0].substring(0, message[0].length()-1);
                    try {
                        KeyHack keyHack = new KeyHack();
                        String code = getBarcode(trimMessage);
                        message[0] = "";
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    public String getBarcode(String barcode) throws IOException {
        URL url = new URL(new DataReader().getURLBarcode().trim() + "?barcode=" + barcode + "&secretkey=" + new DataReader().getSecretKey());
        HttpURLConnection con;


        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        String outputString = new DataReader().getURLBarcode().trim() + "?barcode=" + barcode + "&secretkey=" + new DataReader().getSecretKey();
        OutputStream os = con.getOutputStream();
        byte[] input = outputString.getBytes("utf-8");
        os.write(input, 0, input.length);

        InputStream content = con.getInputStream();
        String result = new BufferedReader(new InputStreamReader(content)).lines().parallel().collect(Collectors.joining("\n"));
        String trimResult = result.substring(3);
        System.out.println(result);


        return trimResult;
    }


}
