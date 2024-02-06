package src.main.dataHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

    public static String userName = System.getProperty("user.name");
    public static File file = new File("C:\\Users\\" + userName + "\\path\\to\\setup\\file.ini");
    private String firstLine;
    private String secondLine;
    private String thirdLine;
    private String comPort;





    public DataReader() {
        setFirstLine(getAppKay());
        setSecondLine(getSecondLine());
        setThirdLine(getURLBarcode());
        setComPort(getComPort());
    }

    public String getAppKay(){
        String line = System.getProperty("line.separator");
        ArrayList<String> strArr = new ArrayList<>();

        String firstLine;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            firstLine = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return  firstLine;
    }

    public String getSecretKey(){
        String line = System.getProperty("line.separator");
        ArrayList<String> strArr = new ArrayList<>();

        String secondLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            secondLine = br.readLine();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  secondLine;

    }

    public String getURLBarcode(){
        String line = System.getProperty("line.separator");
        ArrayList<String> strArr = new ArrayList<>();

        String thirdLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            br.readLine();
            thirdLine = br.readLine();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  thirdLine;

    }

    public static String getComPort(){
        String line = System.getProperty("line.separator");
        ArrayList<String> strArr = new ArrayList<>();

        String comLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            br.readLine();
            br.readLine();
            comLine = br.readLine();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  comLine;

    }
    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public String getCom() {
        return comPort;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getThirdLine() {
        return thirdLine;
    }

    public void setThirdLine(String thirdLine) {
        this.thirdLine = thirdLine;
    }

    public void setComPort(String comPort) {
        this.comPort = comPort;
    }
}
