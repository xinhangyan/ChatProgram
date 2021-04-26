package view;

import listner.PendButtonListener;
import listner.ReceiveAcceptListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProfileCenterPannel extends JPanel {

    public ProfileCenterPannel() {
        setBounds(0,250,400,50);
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(1,2));

        JButton add = new JButton("add");
        add.setVisible(true);
        JButton pend = new JButton("pend");
        pend.setVisible(true);
        add(add);
        add(pend);
        setVisible(true);

        AllFrame.profileCenterPanelAddButton = add;
        AllFrame.profileCenterPanelPendButton = pend;


        add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFriendDialog();
            }
        });
        new ReceiveAcceptListener(pend);
        pend.addActionListener(new PendButtonListener(pend,new PendFriendListDialog()));
    }
}
