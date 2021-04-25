package listner;

import works.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginListener extends BaseListener  {
    TextField name;
    TextField password;

    public LoginListener(TextField name, TextField password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String login = "login "+name.getText()+" "+password.getText();
        String returnMsg = send(login);
        if("You are now logged in!".equals(returnMsg)){
            //登录成功
        } else {
            //登录失败
        }
        System.out.println(login+"\n"+returnMsg);
    }
}
