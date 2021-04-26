package listner;

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
        String login = "login "+name.getText()+" "+password.getText();
        send(login);
    }

    @Override
    public void callBack(String msg) {
        //提示
        errorLabel.setText(msg);
        errorLabel.setAlignment(Label.CENTER);

        if(msg.contains("are now logged in!")){
            //登录成功，隐藏登录面板，打开主面板
            System.out.println("登录成功");
            AllFrame.profileFrame = new ProfileFrame();
            AllFrame.loginFrame.setVisible(false);
            ChatClient.user = Optional.ofNullable(ChatClient.user).orElse(new User());
            ChatClient.user.setId(Integer.parseInt(msg.trim().split(" ")[0]));
        }
        System.out.println(msg+"\n"+msg);
    }
}
