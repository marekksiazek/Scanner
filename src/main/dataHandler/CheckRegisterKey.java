package src.main.dataHandler;

import java.io.*;

public class CheckRegisterKey {

        private String trimSS;
        public CheckRegisterKey() throws IOException {
             setTrimSS(getDiskSerialNumber());
        }

        public static String getDiskSerialNumber() throws IOException {

            String ss = null;
            String trimSS = null;

            Process p = Runtime.getRuntime().exec("cmd.exe /c vol");

            BufferedWriter writeer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            writeer.write("vol");
            writeer.flush();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            stdInput.readLine();

            if ((ss = stdInput.readLine()) !=null){
                trimSS = ss.substring(ss.length() - 9);

            }
            return trimSS;

        };

    public void setTrimSS(String trimSS) {
        this.trimSS = trimSS;
    }

    public String getTrimSS() {
        return trimSS;
    }
}
