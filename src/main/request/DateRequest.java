package src.main.request;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DateRequest {

    private int status;
    private String responseMSG;



    public void setStatus(int status) {
        this.status = status;
    }


    public void setResponseMSG(String responseMSG) {
        this.responseMSG = responseMSG;
    }

    public DateRequest() throws IOException {
        setResponseMSG(responseMSG);
        setStatus(status);
    }

    public int getResponseCode() throws IOException {
        URL url = new URL("URL");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        setStatus(status);

        return status;
    }



    public  String getResponseMsg() throws IOException {
        URL url = new URL("URL");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        String contentString = content.toString();

        setResponseMSG(contentString);

        return contentString;
    }
}
