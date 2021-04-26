package view;

import listner.ExitListener;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ProfileUnderPanel extends JScrollPane {
    public ProfileUnderPanel() {
        setBounds(0,300,400,500);
        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);


    }
}
