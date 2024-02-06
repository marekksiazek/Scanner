package src;

import src.main.config.SetupFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Tray {

    public Tray() throws IOException {



        SystemTray systemTray = SystemTray.getSystemTray();

        PopupMenu popMenu = new PopupMenu();
        MenuItem show = new MenuItem("Settings");
        MenuItem exit = new MenuItem("Exit");

        Image img =  Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("src/image.png"));
        TrayIcon trayIcon = new TrayIcon(img, "Scanner", popMenu);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SetupFrame setupFrame = new SetupFrame();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        popMenu.add(show);
        popMenu.add(exit);

        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(popMenu);

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


    }
}
