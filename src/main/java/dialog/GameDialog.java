package dialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GameDialog extends JDialog {

    public GameDialog(String icon, String logo, String gameName, String appId){

        Image imgIcon = null;
        Image imgLogo = null;
        try{
            URL iconURL = new URL(icon);
            URL logoURL = new URL(logo);
            imgIcon = ImageIO.read(iconURL);
            imgLogo = ImageIO.read(logoURL);
        }
        catch(IOException e){
            System.err.print(e.getMessage());
        }
        setSize(new Dimension(800, 300));
        Point p = new Point(400, 400);
        setLocation(p.x, p.y);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Random Steam Game: " + gameName);
        setIconImage(imgIcon);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Name: " + gameName));
        panel.add(new JLabel("AppID: " + appId));
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(imgLogo));
        panel.add(image);
        //panel.add();
        getContentPane().add(panel);
        pack();
        setLocationByPlatform(true);
        setVisible(true);

    }
}
