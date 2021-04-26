package listner;

import models.TransDto;
import models.User;
import works.ChatClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PendButtonListener extends BaseListener{
    JButton jButton;
    JDialog jDialog;
    public PendButtonListener(JButton jButton,JDialog jDialog) {
        super();
        this.jButton = jButton;
        this.jDialog = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setIds(ChatClient.user.getPendingFriendRequests().toArray(new Integer[0]));
        transDto.setSource("PendButtonListener");
        transDto.setTarget("userlist");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        if(dto.isSuccess()){
            User[] users = dto.getUsers();
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setSize(400,100*users.length);
            for (int i = 0; i < users.length; i++) {
//                try {
                    User user = users[i];
                    JPanel jPanel = new JPanel();
                    jPanel.setBounds(0,i*100,400,100);
                    jPanel.setLayout(null);

                    JLabel image = new JLabel();
//                    URL resource = this.getClass().getResource(user.getPhotoURL());
//                    BufferedImage read = ImageIO.read(new URL("https://juejin.cn/user/254742428662456"));
//                    System.out.println(read==null);
//                    ImageIcon imageIcon = new ImageIcon(read);
//                    image.setIcon(imageIcon);
                    image.setBounds(10,10,90,90);
                    JLabel username = new JLabel();
                    username.setText(user.getUsername());
                    username.setBounds(150,20,30,20);
                    JLabel desc = new JLabel();
                    desc.setText(user.getDescription());
                    desc.setBounds(150,50,150,50);
                    jPanel.add(image);
                    jPanel.add(username);
                    jPanel.add(desc);
                    jPanel.setVisible(true);
                    jScrollPane.add(jPanel);
                    jScrollPane.setVisible(true);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
            jDialog.add(jScrollPane);
            jDialog.setVisible(true);
        }
    }
}
