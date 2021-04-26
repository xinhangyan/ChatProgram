package listner;

import models.TransDto;
import models.User;
import works.ChatClient;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        if(!dto.isSuccess()){
            return;
        }
        ChatClient.user = dto.getUser();
    }
}
