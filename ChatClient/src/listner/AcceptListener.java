package listner;

import models.TransDto;
import models.User;
import view.AllFrame;
import works.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class AcceptListener extends BaseListener{
    String username;
    JButton jButton;
    JPanel jPanel;
    String key;
    public AcceptListener(String username,JButton jButton,JPanel jPanel) {
        super(username);
        key = username;
        this.username = username;
        this.jButton = jButton;
        this.jPanel = jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setSource("AcceptListener"+key);
        transDto.setTarget("accept");
        transDto.setUsername(username);
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        if(dto.isSuccess()){
            ChatClient.user = dto.getUser();
            AllFrame.profileCenterPanelPendButton.setText("pend"+"("+ChatClient.user.getPendingFriendRequests().size()+")");
            AllFrame.profileCenterPanelPendButton.repaint();
            AllFrame.profileCenterPanel.repaint();
            jPanel.remove(jButton);
            JLabel accept = new JLabel("your friends");
            accept.setBounds(300,20,80,30);
            jPanel.add(accept);
            jPanel.repaint();
        }
    }
}
