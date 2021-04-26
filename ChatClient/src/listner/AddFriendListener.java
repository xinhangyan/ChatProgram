package listner;

import models.TransDto;
import view.AllFrame;
import view.ProfileFrame;
import works.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddFriendListener extends BaseListener{
    JLabel jLabel;
    JTextField jTextField;
    JDialog jDialog;
    public AddFriendListener(JLabel jLabel,JTextField jTextField,JDialog jDialog) {
        super();
        this.jLabel = jLabel;
        this.jTextField = jTextField;
        this.jDialog = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setUsername(jTextField.getText());
        transDto.setSource("AddFriendListener");
        transDto.setTarget("addfriend");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        //提示
        jLabel.setText(dto.getMsg());
        System.out.println(dto.getMsg());
        if(dto.isSuccess()){
            //休眠一秒关闭弹窗
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jDialog.setVisible(false);

        }
        System.out.println(dto.toString());
    }
}
