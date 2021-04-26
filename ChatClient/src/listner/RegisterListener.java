package listner;

import models.User;
import view.AllFrame;
import view.ProfileFrame;
import works.ChatClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

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
        String register = "register "+name.getText()+" "+password.getText();
        send(register);

    }

    @Override
    public void callBack(String msg) {
        //提示
        errorLabel.setText(msg);
        errorLabel.setAlignment(Label.CENTER);

        if(msg.contains(" added.")){
            //注册成功，隐藏登录面板，打开主面板，返回用户id赋值给user对象
            System.out.println("注册成功");
            AllFrame.profileFrame = new ProfileFrame();
            AllFrame.loginFrame.setVisible(false);
            ChatClient.user = Optional.ofNullable(ChatClient.user).orElse(new User());
            ChatClient.user.setId(Integer.parseInt(msg.trim().split(" ")[0]));
        }
        System.out.println(msg+"\n"+msg);
    }
}
