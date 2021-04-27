package listner;

import models.TransDto;
import works.ChatClient;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ExitListener extends BaseListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            send(new TransDto("ExitListener","exit"));
            ChatClient.oos.close();
            ChatClient.ois.close();
            ChatClient.sock.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void callBack(TransDto dto) {
        //提示
        super.callBack(dto);

    }
}
