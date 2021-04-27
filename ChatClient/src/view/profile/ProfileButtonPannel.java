package view.profile;

import listner.*;
import view.AllFrame;
import works.ChatClient;

import javax.swing.*;
import java.awt.*;

public class ProfileButtonPannel extends JPanel {

    public ProfileButtonPannel() {
        setBounds(0,250,400,50);
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(2,2));


        JButton myFriends = new JButton("my friends");
        myFriends.setVisible(true);
        JButton allUser = new JButton("all user");
        allUser.setVisible(true);

        JButton pend = new JButton("pend"+"("+ ChatClient.user.getPendingFriendRequests().size()+")");
        pend.setVisible(true);
        JButton waitAccept = new JButton("wait accept"+"("+ ChatClient.user.getPendingFriendRequests().size()+")");
        waitAccept.setVisible(true);
        add(allUser);
        add(myFriends);
        add(pend);
        add(waitAccept);
        setVisible(true);

        AllFrame.profileCenterPanelPendButton = pend;

//        allUser.addActionListener();
        new ReceiveAcceptListener(pend);
        pend.addActionListener(new PendButtonListener());
        allUser.addActionListener(new AllUserListener());
        myFriends.addActionListener(new FriendsListListner());
        waitAccept.addActionListener(new WaitAccepButtontListener());

    }
}
