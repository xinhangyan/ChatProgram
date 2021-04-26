package listner;

import models.TransDto;
import models.User;
import view.AllFrame;
import view.ProfileFrame;
import works.ChatClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class LoginListener extends BaseListener  {
    TextField name;
    TextField password;
    Label errorLabel;

    public LoginListener(TextField name, TextField password,Label errorLabel) {
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
        transDto.setSource("LoginListener");
        transDto.setTarget("login");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        //提示
        errorLabel.setText(dto.getMsg());
        errorLabel.setAlignment(Label.CENTER);

        if(dto.isSuccess()){
            //登录成功，隐藏登录面板，打开主面板
            System.out.println("登录成功");
            AllFrame.profileFrame = new ProfileFrame();
            AllFrame.loginFrame.setVisible(false);
            System.out.println("此处执行");
            ChatClient.user = dto.getUser();
        }
        System.out.println(dto.toString());
    }
}
