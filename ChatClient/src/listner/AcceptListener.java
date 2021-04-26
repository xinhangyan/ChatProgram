package listner;

import models.TransDto;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class AcceptListener extends BaseListener{
    JScrollPane panel;
    public AcceptListener(JScrollPane jPanel) {
        super();
        this.panel = jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setSource("FriendsListListner");
        transDto.setTarget("friendlist");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {

        if(dto.isSuccess()){
            User[] users = dto.getUsers();
            for (int i = 0; i < users.length; i++) {
                User user = users[i];
                JPanel jPanel = new JPanel();
                jPanel.setBounds(0,i*100,400,100);
                jPanel.setLayout(null);

                JLabel image = new JLabel();
                URL resource = this.getClass().getResource(user.getPhotoURL());
                ImageIcon imageIcon = new ImageIcon(resource);
                image.setIcon(imageIcon);
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
                panel.add(jPanel);
            }

        }
        System.out.println(dto.toString());
    }
}
