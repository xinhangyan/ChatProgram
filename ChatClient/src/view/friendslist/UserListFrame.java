package view.friendslist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  This class sets attributes to the user list frame.
 */

public class UserListFrame extends JFrame {
    public UserListFrame() throws HeadlessException {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((screenWidth - 400)/2+400,(screenHeight-300)/2,380,600);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        setVisible(true);
    }
}
