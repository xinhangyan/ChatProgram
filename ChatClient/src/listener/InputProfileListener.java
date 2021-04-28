package listener;

import datebase.UserDatabase;
import models.TransDto;
import models.User;
import view.AllFrame;
import view.BaseDialog;
import works.ChatClient;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

/**
 *  This class defines possible actions after clicking input(import) button.
 */

public class InputProfileListener extends BaseListener{
    public InputProfileListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User user = null;
        try {
            UserDatabase singleton = UserDatabase.getSingleton();
            singleton.loadProfiles();
            user = singleton.getUser(ChatClient.user.getUsername());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        if(user==null){
            new BaseDialog("load profile.csv failed");
            return;
        }

        ChatClient.user.setEmail(Optional.ofNullable(user.getEmail()).orElse(ChatClient.user.getEmail()));
        ChatClient.user.setFavorite(Optional.ofNullable(user.getFavorite()).orElse(ChatClient.user.getFavorite()));
        ChatClient.user.setDescription(Optional.ofNullable(user.getDescription()).orElse(ChatClient.user.getDescription()));

        TransDto transDto = new TransDto();
        transDto.setSource("InputProfileListener");
        transDto.setTarget("set");
        transDto.setUser(ChatClient.user);
        send(transDto);
    }

    @Override
    public void callBack(TransDto dto) {
        super.callBack(dto);
        AllFrame.email.setText(dto.getUser().getEmail());
        AllFrame.email.repaint();
        AllFrame.favorite.setText(dto.getUser().getFavorite());
        AllFrame.favorite.repaint();
        AllFrame.abortMe.setText(dto.getUser().getDescription());
        AllFrame.abortMe.repaint();
    }
}
