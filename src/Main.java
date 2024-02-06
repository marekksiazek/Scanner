package src;


import com.fazecast.jSerialComm.*;
import src.main.config.SetupFrame;
import src.main.dataHandler.CheckRegisterKey;
import src.main.dataHandler.DataReader;
import src.main.dataHandler.SetupCheck;
import src.main.errors.*;
import src.main.request.*;
import src.main.scan.Scan;

import java.io.File;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {

        boolean run = false;
        boolean startScan = false;
        SerialPort serialPort1 = null;
        String baseKey = "BASE KEY";

        InternetConnection internetConnection = new InternetConnection();
        boolean connection = internetConnection.netIsAvailable();

        if (!connection){
            ConnectionError connectionError = new ConnectionError();
        }else {
            SetupCheck setupCheck = new SetupCheck();
            Tray tray = new Tray();
            DataReader dataReader = new DataReader();

            String appKayFirst = dataReader.getAppKay();
            String secretKeyFirst = dataReader.getSecretKey();
            String URLBarcodeFirst = dataReader.getURLBarcode();

            if (appKayFirst.equals("") || secretKeyFirst.equals("") || URLBarcodeFirst.equals("")) {
                SetupFrame setupFrame = new SetupFrame();
            } else {
                String registerRequest = new RegisterRequest().getRegisterMSG();
                if (registerRequest.equals("err")){
                    RegisterError registerFrame = new RegisterError();
                }
                SerialPort[] serialPort = SerialPort.getCommPorts();

                if (registerRequest.equals(baseKey)){

                    String username = System.getProperty("user.name");
                    String pathToSetupFile = "C:\\Users\\" + username + "\\path\\to\\setup\\file.ini";
                    boolean fileExist = new File(pathToSetupFile).exists();

                    if (!fileExist){
                        CheckRegisterKey checkRegisterKey = new CheckRegisterKey();
                        String serialDisk = checkRegisterKey.getTrimSS();
                    }


                    if (fileExist) {
                        String appKey = dataReader.getAppKay();
                        String secretKey = dataReader.getSecretKey();
                        String URLBarcode = dataReader.getURLBarcode();
                        String comName = dataReader.getCom();

                        if (appKey.equals("")) {
                            AppKeyError error = new AppKeyError();
                        } else if (secretKey.equals("")) {
                            SecretKeyError error = new SecretKeyError();
                        } else if (URLBarcode.equals("")) {
                            UrlError error = new UrlError();
                        } else if (comName.equals("")){
                            ErrorBarcodeConnect error = new ErrorBarcodeConnect();
                        }
                        else {
                            RequestURL request = new RequestURL();
                            run = true;
                            startScan = true;

                            Scan scan = new Scan();
                            DateRequest dateRequest = new DateRequest();



                            while (run){
                                if (serialPort.length == 0) {
                                    ErrorBarcodeConnect frame = new ErrorBarcodeConnect();
                                } else {
                                    serialPort1 = SerialPort.getCommPort(DataReader.getComPort());
                                    serialPort1.setBaudRate(9600);
                                    serialPort1.setNumDataBits(8);
                                    serialPort1.setNumStopBits(1);
                                    serialPort1.setParity(SerialPort.NO_PARITY);
                                    serialPort1.openPort();
                                    startScan = true;
                                    run = false;
                                }
                            }

                            while (startScan){
                                scan.Serial_EventBaseReading(serialPort1);
                            }
                        }
                    }
                }
            }
        }
    }
}