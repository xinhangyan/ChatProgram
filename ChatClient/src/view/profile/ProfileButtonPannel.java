package view.profile;

import listener.*;
import view.AllFrame;
import works.ChatClient;

import javax.swing.*;
import java.awt.*;

/**
 *  This class includes all buttons with corresponding features.
 */

public class ProfileButtonPannel extends JPanel {

    public ProfileButtonPannel() {
        setBounds(0,250,400,100);
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(3,2));

        JButton input = new JButton("import");
        input.setVisible(true);
        JButton output = new JButton("export");
        output.setVisible(true);

        JButton myFriends = new JButton("my friends");
        myFriends.setVisible(true);
        JButton allUser = new JButton("all user");
        allUser.setVisible(true);

        JButton pend = new JButton("received"+"("+ ChatClient.user.getPendingFriendRequests().size()+")");
        pend.setVisible(true);
        JButton send = new JButton("sent"+"("+ ChatClient.user.getSendingFriendRequests().size()+")");
        send.setVisible(true);
        add(input);
        add(output);
        add(allUser);
        add(myFriends);
        add(pend);
        add(send);
        setVisible(true);

        AllFrame.profileCenterPanelPendButton = pend;
        AllFrame.profileCenterPanelSendButton = send;

//        allUser.addActionListener();
        new ReceiveAcceptListener(pend);
        pend.addActionListener(new PendButtonListener());
        allUser.addActionListener(new AllUserListener());
        myFriends.addActionListener(new FriendsListListner());
        send.addActionListener(new WaitAccepButtontListener());
        input.addActionListener(new InputProfileListener());
        output.addActionListener(new OutputProfileListener());

    }
}
