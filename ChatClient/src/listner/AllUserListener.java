package listner;

import models.TransDto;
import models.User;
import view.AllFrame;
import view.friendslist.UserListFrame;
import view.friendslist.UserListJScrollPane;
import works.ChatClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class AllUserListener extends BaseListener{
    private JFrame jFrame;
    JScrollPane jScrollPane;
//    private JScrollPane jScrollPane;
    public AllUserListener() {
        super();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.jFrame = Objects.isNull(AllFrame.friendListJFrame)?new UserListFrame():AllFrame.friendListJFrame;
        AllFrame.friendListJFrame = this.jFrame;
//        this.jScrollPane = Optional.ofNullable(this.jScrollPane).orElse(new UserListJScrollPane());
        TransDto transDto = new TransDto();
        transDto.setSource("AllUserListener");
        transDto.setTarget("list");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        ChatClient.user = dto.getUser();
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

            JButton add = new JButton("add");
            add.setBounds(260,10,80,30);

            jPanel.add(image);
            jPanel.add(username);
            jPanel.add(userNameLab);
            jPanel.add(email);
            jPanel.add(emailLab);
            jPanel.add(favorite);
            jPanel.add(favoriteLab);
            jPanel.add(desc);
            jPanel.add(descLab);
            isAddedOrPending(jPanel,add,user);
            jPanel.setBorder(BorderFactory.createBevelBorder(0));
            jPanel.setVisible(true);
            jPanel1.add(jPanel);

            add.addActionListener(new AddFriendListener(add,jPanel,user));
        }
        JScrollPane jScrollPane = new JScrollPane(jPanel1);
        jScrollPane.setVisible(true);
        AllFrame.friendListJFrameChildren.forEach(x->jFrame.remove(x));
        AllFrame.friendListJFrameChildren.clear();
        AllFrame.friendListJFrameChildren.add(jScrollPane);
        jFrame.getContentPane().add(jScrollPane);
        jFrame.setVisible(true);
    }

    void isAddedOrPending(JPanel jPanel,JButton jButton,User user){
        boolean isAdded = ChatClient.user.getSendingFriendRequests().contains(user.getId());
        if(isAdded){
            JLabel jLabel = new JLabel("sended");
            jLabel.setBounds(jButton.getBounds());
            jLabel.setVisible(true);
            jPanel.add(jLabel);
        } else if(ChatClient.user.getPendingFriendRequests().contains(user.getId())){
            jButton.setText("accept");
            jPanel.add(jButton);
        } else if(ChatClient.user.getFriends().contains(user.getId())){
            JLabel jLabel = new JLabel("your friends");
            jLabel.setBounds(jButton.getBounds());
            jLabel.setVisible(true);
            jPanel.add(jLabel);
        } else {
            jPanel.add(jButton);
        }

    }

}
