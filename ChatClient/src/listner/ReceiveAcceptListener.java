package listner;

import models.TransDto;
import works.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
        if(dto.isSuccess()){
            ChatClient.user.setPendingFriendRequests(dto.getUser().getPendingFriendRequests());
            jButton.setText("pend "+dto.getUser().getPendingFriendRequests().size());
        }
        System.out.println(dto.toString());
    }
}
