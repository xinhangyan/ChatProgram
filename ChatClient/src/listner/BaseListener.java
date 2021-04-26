package listner;

import models.TransDto;
import works.ChatClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class BaseListener implements ActionListener {

    public BaseListener() {
        ChatClient.listenerMap.put(this.getClass().getSimpleName(),this);
        System.out.println("listenerMap register "+this.getClass().getSimpleName());
    }

    //send message to server
    public void send(TransDto dto){
        try {
            ChatClient.oos.writeObject(dto);
            ChatClient.oos.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void callBack(TransDto msg){

    }
}
