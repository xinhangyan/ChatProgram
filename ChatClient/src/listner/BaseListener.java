package listner;

import works.ChatClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class BaseListener implements ActionListener {
    //send message to server
    public String send(String msg){
        String s = "try again!";
        try {
            ChatClient.writer.write(s);
            ChatClient.writer.newLine();
            ChatClient.writer.flush();
            s = ChatClient.reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return s;
    }

    //exit program when close windows
    private void windowClose(Frame frame){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
