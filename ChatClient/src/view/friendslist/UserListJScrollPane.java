package view.friendslist;

import javax.swing.*;
import java.awt.*;

public class UserListJScrollPane extends JScrollPane {
    public UserListJScrollPane() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((screenWidth - 400)/2+400,(screenHeight-300)/2,380,600);
        setLayout(null);
    }
}
