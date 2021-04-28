package listener;

import models.TransDto;
import view.AllFrame;
import works.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  This class defines possible actions after rejecting others' friend request.
 */

public class RejectListener extends BaseListener{
    String username;
    JButton accept;
    JButton reject;
    JPanel jPanel;
    String key;
    public RejectListener(String username,JButton accept,JButton reject,JPanel jPanel) {
        super(username);
        key = username;
        this.username = username;
        this.accept = accept;
        this.reject = reject;
        this.jPanel = jPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setSource("RejectListener"+key);
        transDto.setTarget("reject");
        transDto.setUsername(username);
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        if(dto.isSuccess()){
            ChatClient.user = dto.getUser();
            AllFrame.profileCenterPanelPendButton.setText("received"+"("+ChatClient.user.getPendingFriendRequests().size()+")");
            AllFrame.profileCenterPanelPendButton.repaint();
            AllFrame.profileCenterPanel.repaint();
            jPanel.remove(accept);
            jPanel.remove(reject);
            JLabel accept = new JLabel("rejected");
            accept.setBounds(accept.getBounds());
            jPanel.add(accept);
            jPanel.repaint();
            AllFrame.profileCenterPanelPendButton.setText("received"+"("+ ChatClient.user.getPendingFriendRequests().size()+")");
            AllFrame.profileCenterPanelPendButton.repaint();
        }
    }
}
