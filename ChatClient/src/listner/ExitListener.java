package listner;

import view.AllFrame;
import view.ProfileFrame;
import works.ChatClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ExitListener extends BaseListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            send("exit");
            ChatClient.writer.close();
            ChatClient.reader.close();
            ChatClient.sock.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void callBack(String msg) {
        //提示

    }
}
