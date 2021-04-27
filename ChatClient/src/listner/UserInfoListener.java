package listner;

import models.TransDto;
import works.ChatClient;

import java.awt.event.ActionEvent;

public class UserInfoListener extends BaseListener{
    public UserInfoListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TransDto transDto = new TransDto();
        transDto.setSource("UserInfoListener");
        transDto.setTarget("info");
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        if(!dto.isSuccess()){
            return;
        }
        ChatClient.user = dto.getUser();
    }
}
