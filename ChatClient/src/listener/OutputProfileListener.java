package listener;

import datebase.UserDatabase;
import view.BaseDialog;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 *  This class defines possible actions after clicking output(export) button.
 */

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
