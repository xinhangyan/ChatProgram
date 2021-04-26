package view;

import listner.AddFriendListener;

import javax.swing.*;
import java.awt.*;

public class AddFriendDialog extends JDialog {
    public AddFriendDialog() {
        setSize(300,200);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - 400)/2, (screenHeight-300)/2);
        setVisible(true);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        JLabel jLabel = new JLabel();
        jLabel.setBounds(50,50,300,20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(50,100,100,30);
        JButton jButton = new JButton("add");
        jButton.setBounds(155,100,60,30);
        contentPane.add(jTextField);
        contentPane.add(jButton);
        contentPane.add(jLabel);

        jButton.addActionListener(new AddFriendListener(jLabel,jTextField,this));
    }
}
