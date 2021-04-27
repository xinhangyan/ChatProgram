package view.profile;

import listner.ExitListener;
import view.AllFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProfileFrame extends JFrame {
    public ProfileFrame() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((screenWidth - 400)/2,(screenHeight-300)/2,410,390);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        ProfileTopPanel topPanel = new ProfileTopPanel();
        ProfileButtonPannel centerPannel = new ProfileButtonPannel();
        contentPane.add(topPanel);
        contentPane.add(centerPannel);
        AllFrame.profileTopPanel = topPanel;
        AllFrame.profileCenterPanel = centerPannel;


        addWindowListener(new WindowAdapter() {
            //关闭窗口时关闭socket连接和程序
            @Override
            public void windowClosing(WindowEvent e) {
                new ExitListener().actionPerformed(null);
            }
        });

        setVisible(true);
    }
}
