package listner;

import datebase.UserDatabase;
import view.BaseDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class OutputProfileListener extends BaseListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        UserDatabase singleton = UserDatabase.getSingleton();
        try {
            singleton.saveProfiles();
        } catch (IOException ioException) {
            new BaseDialog("output to profile.csv failed");
            ioException.printStackTrace();
        }
        new BaseDialog("output to profile.csv");
    }
}
