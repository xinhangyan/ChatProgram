package listner;

import models.TransDto;
import models.User;
import view.AllFrame;
import view.friendslist.UserListFrame;
import works.ChatClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class WaitAccepButtontListener extends BaseListener{
    private JFrame jFrame;
    public WaitAccepButtontListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.jFrame = Objects.isNull(AllFrame.friendListJFrame)?new UserListFrame():AllFrame.friendListJFrame;
        AllFrame.friendListJFrame = this.jFrame;

        TransDto transDto = new TransDto();
        transDto.setIds(ChatClient.user.getSendingFriendRequests().toArray(new Integer[0]));
        transDto.setSource("WaitAccepButtontListener");
        transDto.setTarget("userlist");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);

        User[] users = dto.getUsers();
        JPanel jPanel1 = new JPanel();
        jPanel1.setSize( 380, 200*users.length);
        jPanel1.setLayout(null);

        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            JPanel jPanel = new JPanel();
            jPanel.setBounds(0,i*200,380,200);
            jPanel.setLayout(null);

            JLabel image = new JLabel();
//                    URL resource = this.getClass().getResource(user.getPhotoURL());
            BufferedImage read = null;
            try {
                read = ImageIO.read(new URL("https://sf1-ttcdn-tos.pstatp.com/img/user-avatar/97cf4f165a904ca4b119d2de407f9e1e~300x300.image"));
                ImageIcon imageIcon = new ImageIcon(read);
                image.setIcon(imageIcon);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setBounds(10,10,90,90);

            JLabel userNameLab = new JLabel();
            userNameLab.setText("username:");
            userNameLab.setBounds(120,20,60,20);
            JLabel username = new JLabel();
            username.setText(user.getUsername());
            username.setBounds(190,20,100,20);

            JLabel emailLab = new JLabel();
            emailLab.setText("email:");
            emailLab.setBounds(120,50,60,20);
            JLabel email = new JLabel();
            email.setText(user.getEmail());
            email.setBounds(190,50,100,20);

            JLabel favoriteLab = new JLabel();
            favoriteLab.setText("favorite:");
            favoriteLab.setBounds(120,80,60,20);
            JLabel favorite = new JLabel();
            favorite.setText(user.getFavorite());
            favorite.setBounds(190,80,100,20);

            JLabel descLab = new JLabel();
            descLab.setText("abort me:");
            descLab.setBounds(10,100,150,50);
            JLabel desc = new JLabel();
            desc.setText(user.getDescription());
            desc.setBounds(30,130,300,50);

            Label statusLabel = new Label(user.isOnline()?"online":"offline");
            statusLabel.setBounds(260,10,80,30);

            jPanel.add(statusLabel);
            jPanel.add(image);
            jPanel.add(username);
            jPanel.add(userNameLab);
            jPanel.add(email);
            jPanel.add(emailLab);
            jPanel.add(favorite);
            jPanel.add(favoriteLab);
            jPanel.add(desc);
            jPanel.add(descLab);
            jPanel.setBorder(BorderFactory.createBevelBorder(0));
            jPanel.setVisible(true);
            jPanel1.add(jPanel);
        }
        JScrollPane jScrollPane = new JScrollPane(jPanel1);
        jScrollPane.setVisible(true);
        AllFrame.friendListJFrameChildren.forEach(x->jFrame.remove(x));
        AllFrame.friendListJFrameChildren.clear();
        AllFrame.friendListJFrameChildren.add(jScrollPane);
        jFrame.getContentPane().add(jScrollPane);
        jFrame.setVisible(true);
    }
}
