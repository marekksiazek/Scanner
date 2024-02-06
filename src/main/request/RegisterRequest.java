package src.main.request;


import src.main.dataHandler.CheckRegisterKey;
import src.main.dataHandler.DataReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class RegisterRequest {




    public RegisterRequest() throws IOException {
    }

    public String getRegisterMSG() throws IOException {
        try {
            URL url = new URL("URL" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay());
            HttpURLConnection con;


            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);


            String outputString =
                    "URL" + CheckRegisterKey.getDiskSerialNumber() + "&key=" + new DataReader().getAppKay();
            OutputStream os = con.getOutputStream();
            byte[] input = outputString.getBytes("utf-8");
            os.write(input, 0, input.length);

            InputStream content = con.getInputStream();
            String result = new BufferedReader(new InputStreamReader(content)).lines().parallel().collect(Collectors.joining("\n"));

            String trimResult = result.trim();
            return trimResult;
        } catch (IOException e) {
            return "error";
        }
    }


}