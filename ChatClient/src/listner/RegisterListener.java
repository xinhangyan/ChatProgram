package listner;

import models.TransDto;
import view.AllFrame;
import view.profile.ProfileFrame;
import works.ChatClient;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterListener extends BaseListener{
    TextField name;
    TextField password;
    Label errorLabel;

    public RegisterListener(TextField name, TextField password, Label errorLabel) {
        super();
        this.name = name;
        this.password = password;
        this.errorLabel = errorLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setUsername(name.getText());
        transDto.setPassword(password.getText());
        transDto.setSource("RegisterListener");
        transDto.setTarget("register");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        //提示
        errorLabel.setText(dto.getMsg());
        errorLabel.setAlignment(Label.CENTER);

        if(dto.isSuccess()){
            //注册成功，隐藏登录面板，打开主面板，返回用户id赋值给user对象
            System.out.println("注册成功");
            ChatClient.user = dto.getUser();
            AllFrame.profileFrame = new ProfileFrame();
            AllFrame.loginFrame.setVisible(false);
        }
    }
}
