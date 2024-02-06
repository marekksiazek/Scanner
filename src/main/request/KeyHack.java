package src.main.request;


import src.main.errors.AppKeyError;

import java.io.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class KeyHack {

    private String firstLine;
    private String baseKey = "BASE KEY";
    private String username = System.getProperty("user.name");

    private String pathToSetupFile = "C:\\Users\\" + username + "\\Path\\to\\setup\\file";
    private boolean setupFile = new File(pathToSetupFile).exists();

    public KeyHack() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = new DateRequest().getResponseMsg();
        String newFormat = format.trim();
        RegisterRequest registerRequest = new RegisterRequest();

        boolean result;


        if(registerRequest.getRegisterMSG().equals(baseKey)){
            File file = new File("C:\\Users\\" + username + "\\Path\\to\\data\\file" + newFormat + ".txt");

            if (!file.exists()) {
                result = file.createNewFile();
                PrintWriter out = new PrintWriter("C:\\Users\\" + username + "\\Path\\to\\data\\file" + newFormat + ".txt");

                out.println(sha256(newFormat)+baseKey);
                out.close();
            } else {
                File fileHash = new File("C:\\Users\\" + username + "\\path\\to\\data\\file" + newFormat + ".txt");

                String line = System.getProperty("line.separator");
                ArrayList<String> strArr = new ArrayList<>();

                String firstLine;
                BufferedReader br = null;
                String baseHash = sha256(newFormat) + baseKey;
                try {
                    br = new BufferedReader(new FileReader(fileHash));
                    firstLine = br.readLine();

                    if (!firstLine.equals(baseHash)) {
                        AppKeyError appKeyError = new AppKeyError();
                        fileHash.deleteOnExit();
                        }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }else {
            AppKeyError appKeyError = new AppKeyError();
        }
    }

    public  String sha256(String newFormat) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(newFormat.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
