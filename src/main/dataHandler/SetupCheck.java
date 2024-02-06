package src.main.dataHandler;


import src.main.errors.ErrorSetupEnter;

import java.io.File;
import java.io.IOException;

public class SetupCheck {

    private String username = System.getProperty("user.name");
    private String pathToDirectory = "C:\\Users\\" + username + "\\Path\\to\\directory";
    private String pathToSetupFile = "C:\\Users\\" + username + "\\Path\\to\\setup\\file.ini";
    private boolean directoryBarcode = new File(pathToDirectory).exists();
    private boolean setupFile = new File(pathToSetupFile).exists();

    public SetupCheck() throws IOException {

            if (!directoryBarcode) {
                new File(pathToDirectory).mkdir();
            }

            if (!setupFile) {
                File file = new File(pathToSetupFile);
                boolean result;

                try {
                    result = file.createNewFile();

                    if (result) {
                        ErrorSetupEnter errorFrame = new ErrorSetupEnter();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

    }
}
