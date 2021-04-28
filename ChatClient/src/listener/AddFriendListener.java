package listener;

import models.TransDto;
import models.User;
import view.AllFrame;
import view.BaseDialog;
import works.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  This class defines possible actions after clicking add friend button.
 */

public class AddFriendListener extends BaseListener{
    JButton jButton;
    JPanel jPanel;
    User user;
    String key;

    public AddFriendListener(JButton jButton, JPanel jPanel, User user) {
        super(jButton.getText()+user.getId());
        key =jButton.getText()+user.getId();
        this.jButton = jButton;
        this.jPanel = jPanel;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setUsername(user.getUsername());
        transDto.setSource("AddFriendListener"+key);
        transDto.setTarget("addfriend");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        //提示
        if(dto.isSuccess()){
            ChatClient.user = dto.getUser();
            JLabel jLabel = new JLabel("sended");
            jLabel.setBounds(jButton.getBounds());
            jLabel.setVisible(true);
            jPanel.remove(jButton);
            jPanel.add(jLabel);
            jPanel.repaint();
            AllFrame.profileCenterPanelSendButton.setText("sent"+"("+ ChatClient.user.getSendingFriendRequests().size()+")");
            AllFrame.profileCenterPanelSendButton.repaint();
        } else {
            jButton.setVisible(false);
            new BaseDialog(dto.getMsg());
        }
    }
}
