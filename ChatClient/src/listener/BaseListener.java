package listener;

import models.TransDto;
import works.ChatClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *  This class defines basic actions, including sending messages to server.
 */

public class BaseListener implements ActionListener {

    public BaseListener() {
        ChatClient.listenerMap.put(this.getClass().getSimpleName(),this);
        System.out.println("listenerMap register "+this.getClass().getSimpleName());
    }

    //同一个类实例化多个对象的时候，会导致callback回调出错，所以需要带点特征值
    public BaseListener(String args) {
        ChatClient.listenerMap.put(this.getClass().getSimpleName()+args,this);
        System.out.println("listenerMap register "+this.getClass().getSimpleName()+args);
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

    public void callBack(TransDto dto){

    }
}
