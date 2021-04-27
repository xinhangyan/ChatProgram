package view.profile;

import listner.EditProfileListener;
import models.User;
import view.AllFrame;
import works.ChatClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ProfileTopPanel extends JPanel {
    public ProfileTopPanel() {
        setBounds(0,0,400,250);
        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);
        setLayout(null);
        User user = ChatClient.user;

        JLabel image = new JLabel();
//                    URL resource = this.getClass().getResource(user.getPhotoURL());
        BufferedImage read = null;
        try {
            read = ImageIO.read(new URL("https://sf1-ttcdn-tos.pstatp.com/img/user-avatar/97cf4f165a904ca4b119d2de407f9e1e~300x300.image"));
            ImageIcon imageIcon = new ImageIcon(read);
            image.setIcon(imageIcon);
        } catch (IOException e) {

        }
        image.setBounds(10,10,90,90);

        JButton jButton = new JButton("edit");
        jButton.setBounds(300,10,80,20);

        JLabel userNameLab = new JLabel();
        userNameLab.setText("username:");
        userNameLab.setBounds(150,20,60,20);
        JLabel username = new JLabel();
        username.setText(user.getUsername());
        username.setBounds(220,20,100,20);

        JLabel emailLab = new JLabel();
        emailLab.setText("email:");
        emailLab.setBounds(150,50,60,20);
        JLabel email = new JLabel();
        email.setText(user.getEmail());
        email.setBounds(220,50,100,20);

        JLabel favoriteLab = new JLabel();
        favoriteLab.setText("favorite:");
        favoriteLab.setBounds(150,80,60,20);
        JLabel favorite = new JLabel();
        favorite.setText(user.getFavorite());
        favorite.setBounds(220,80,100,20);

        JLabel descLab = new JLabel();
        descLab.setText("abort me:");
        descLab.setBounds(10,100,150,50);
        JLabel desc = new JLabel();
        desc.setText(user.getDescription());
        desc.setBounds(30,130,300,50);

        AllFrame.username = username;
        AllFrame.email = email;
        AllFrame.favorite = favorite;
        AllFrame.abortMe = desc;

        add(jButton);
        add(image);
        add(username);
        add(userNameLab);
        add(email);
        add(emailLab);
        add(favorite);
        add(favoriteLab);
        add(desc);
        add(descLab);
        setVisible(true);

        jButton.addActionListener(new EditProfileListener(jButton,username,email,favorite,desc));
    }
}
