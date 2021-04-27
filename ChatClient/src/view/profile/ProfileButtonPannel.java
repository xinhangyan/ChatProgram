package view.profile;

import datebase.UserDatabase;
import listner.*;
import view.AllFrame;
import works.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProfileButtonPannel extends JPanel {

    public ProfileButtonPannel() {
        setBounds(0,250,400,100);
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(3,2));

        JButton input = new JButton("input");
        input.setVisible(true);
        JButton output = new JButton("output");
        output.setVisible(true);

        JButton myFriends = new JButton("my friends");
        myFriends.setVisible(true);
        JButton allUser = new JButton("all user");
        allUser.setVisible(true);

        JButton pend = new JButton("pend"+"("+ ChatClient.user.getPendingFriendRequests().size()+")");
        pend.setVisible(true);
        JButton waitAccept = new JButton("wait accept"+"("+ ChatClient.user.getSendingFriendRequests().size()+")");
        waitAccept.setVisible(true);
        add(input);
        add(output);
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
        input.addActionListener(new InputProfileListener());
        output.addActionListener(new OutputProfileListener());

    }
}
