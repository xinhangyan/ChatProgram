import works.ChatClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Runs the client class.
 *
 * CS18000 - PJ05 - Option2
 * @arthor Qingyuan Yan, yan327@purdue.edu; Yifei Jin, jin388@purdue.edu; Jiakun Yang, yang1800@purdue.edu
 *
 */

public class main {
    public static void main(String[] args) throws InterruptedException, IOException {
        ChatClient chatClient = new ChatClient();
        chatClient.start();

    }
}
