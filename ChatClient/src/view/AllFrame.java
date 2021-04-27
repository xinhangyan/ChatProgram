package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllFrame {
    public static Frame loginFrame;
    public static Frame profileFrame;
    public static JPanel profileTopPanel;
    public static JPanel profileCenterPanel;
    public static JButton profileCenterPanelAddButton;
    public static JButton profileCenterPanelPendButton;
    public static JPanel ProfileUnderPanel;
    //profile 
    public static JLabel username;
    public static JLabel email;
    public static JLabel favorite;
    public static JLabel abortMe;
    public static JTextArea usernameText;
    public static JTextArea emailText;
    public static JTextArea favoriteText;
    public static JTextArea abortMeText;
    //firendslist
    public static JFrame friendListJFrame;
    public static Set<Component> friendListJFrameChildren = new HashSet();

}
