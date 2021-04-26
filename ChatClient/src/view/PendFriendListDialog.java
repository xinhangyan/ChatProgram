package view;

import listner.PendButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PendFriendListDialog extends JDialog {
    public PendFriendListDialog() {
        setSize(400,300);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - 400)/2, (screenHeight-300)/2);
        setVisible(true);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

    }
}
