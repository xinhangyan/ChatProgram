package view;

import listner.ExitListener;
import listner.FriendsListListner;
import listner.UserInfoListener;
import works.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProfileFrame extends JFrame {
    public ProfileFrame() {
        setSize(400,800);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((screenWidth - 400)*2/3,(screenHeight-800)/2,400,800);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        ProfileTopPanel topPanel = new ProfileTopPanel();
        ProfileCenterPannel centerPannel = new ProfileCenterPannel();
        ProfileUnderPanel underPanel = new ProfileUnderPanel();
        contentPane.add(topPanel);
        contentPane.add(centerPannel);
        contentPane.add(underPanel);
        AllFrame.profileTopPanel = topPanel;
        AllFrame.ProfileUnderPanel = underPanel;
        AllFrame.profileCenterPanel = centerPannel;


        addWindowListener(new WindowAdapter() {
            //关闭窗口时关闭socket连接和程序
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(topPanel.getX()+" "+topPanel.getY());
                new ExitListener().actionPerformed(null);
            }

            //打开窗口时查询用户信息
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("打开了窗口");
                new FriendsListListner(underPanel).actionPerformed(null);
            }
        });

        setVisible(true);
    }
}
