package view.login;

import listener.ExitListener;
import listener.LoginListener;
import listener.RegisterListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  This class creates login frame interacted with server.
 */

public class LoginFrame extends JFrame{
    public LoginFrame() {
        // set attributes
        setSize(400,300);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - 400)/2, (screenHeight-300)/2);


        Panel panel0 = new Panel(new GridLayout(2,1));
        Panel panel1 = new Panel(new GridLayout(2,3));
        Panel panel2 = new Panel(new FlowLayout());

        Button login = new Button("login");
        Button register = new Button("register");

        JLabel username = new JLabel("username",SwingConstants.RIGHT);
        JLabel password = new JLabel("password",SwingConstants.RIGHT);
        TextField textField = new TextField();
        TextField passwordField = new TextField();

        Label errorLabel = new Label();
        panel0.add(new Label("LOGIN",Label.CENTER));
        panel0.add(errorLabel);
        panel1.add(username);
        panel1.add(textField);
        panel1.add(new Panel());
        panel1.add(password,BorderLayout.EAST);
        panel1.add(passwordField);
        panel1.add(new Panel());
        panel2.add(login,BorderLayout.EAST);
        panel2.add(register,BorderLayout.EAST);

        setLayout(new GridLayout(3,1,20,20));
        add(panel0);
        add(panel1);
        add(panel2);

        //监听登录按钮点击事件
        login.addActionListener(new LoginListener(textField,passwordField,errorLabel));

        //监听注册按钮点击事件
        register.addActionListener(new RegisterListener(textField,passwordField,errorLabel));

        //关闭窗口时关闭socket连接和程序
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               new ExitListener().actionPerformed(null);
            }
        });

        setVisible(true);
    }
}

//注册弹窗
//class RegisterDiaLog extends JDialog{
//    public RegisterDiaLog() {
//        setSize(400,300);
//        this.setVisible(true);
//        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
//        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
//        setLocation((screenWidth - 400)/2, (screenHeight-300)/2+100);
//
//        Container contentPane = this.getContentPane();
//        contentPane.setLayout(null);
//        contentPane.add(new )
//    }
//}
