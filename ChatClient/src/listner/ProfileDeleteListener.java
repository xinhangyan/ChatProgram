package listner;

import models.TransDto;
import view.BaseDialog;
import works.ChatClient;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ProfileDeleteListener extends BaseListener{
    public ProfileDeleteListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setUsername(ChatClient.user.getUsername());
        transDto.setTarget("delete");
        transDto.setSource("ProfileDeleteListener");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        try {
            ChatClient.oos.close();
            ChatClient.ois.close();
            ChatClient.sock.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
