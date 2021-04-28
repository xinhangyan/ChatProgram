package works;

import listener.BaseListener;
import models.TransDto;
import models.User;
import view.AllFrame;
import view.login.LoginFrame;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;

/** This is client class.
 *
 */

public class ChatClient extends Thread{
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;
    public static Socket sock;
    public static User user;
    public static HashMap<String, BaseListener> listenerMap = new HashMap<>();


    private void handle(InputStream input, OutputStream output) throws IOException, ClassNotFoundException {
        oos = new ObjectOutputStream(output);
        ois = new ObjectInputStream(input);
        System.out.println("[server] " +  ois.readObject());
        AllFrame.loginFrame = new LoginFrame();
        for (;;) {
            TransDto transDto = (TransDto) ois.readObject();
            BaseListener baseListener = listenerMap.get(transDto.getTarget());
            System.out.println("callback:"+transDto.toString());
            if(Objects.nonNull(baseListener)){
                baseListener.callBack(transDto);
            }
        }

    }

    @Override
    public void run() {
        try {
            sock = new Socket("localhost", 6667);
            try (InputStream input = sock.getInputStream()) {
                try (OutputStream output = sock.getOutputStream()) {
                    handle(input, output);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
