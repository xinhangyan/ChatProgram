package listner;

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
    public void send(String msg){
        try {
            ChatClient.writer.write(msg);
            ChatClient.writer.newLine();
            ChatClient.writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void callBack(String msg){

    }
}
