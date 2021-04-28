package listener;

import models.TransDto;
import works.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  This class defines possible actions after receiving friend acceptance.
 */

public class ReceiveAcceptListener extends BaseListener{
    JButton jButton;
    public ReceiveAcceptListener(JButton jButton) {
        super();
        this.jButton = jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        if(dto.isSuccess()){
            ChatClient.user.setPendingFriendRequests(dto.getUser().getPendingFriendRequests());
            jButton.setText("pend "+"("+ ChatClient.user.getPendingFriendRequests().size()+")");
        }
    }
}
