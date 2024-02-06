package src.main.request;


import src.main.dataHandler.CheckRegisterKey;
import src.main.dataHandler.DataReader;
import src.main.errors.UrlError;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestURL {

    private int status;
    private String responseMSG;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseMSG() {
        return responseMSG;
    }

    public void setResponseMSG(String responseMSG) {
        this.responseMSG = responseMSG;
    }

    public RequestURL() throws IOException {

        URL url = new URL("URL" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay());
        HttpURLConnection con;

        {
            try {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);


                String outputString =
                        "URL" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay();
                OutputStream os = con.getOutputStream();
                byte[] input = outputString.getBytes("utf-8");
                os.write(input, 0, input.length);


                int status = con.getResponseCode();
                String responseMSG = con.getResponseMessage();

                if (status != 200) {
                    UrlError errorURL = new UrlError();
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }


}
