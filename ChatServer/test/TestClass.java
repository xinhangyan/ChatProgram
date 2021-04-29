import commands.*;
import models.TransDto;
import models.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.List;

public class TestClass {

    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("register");
        list.add("login");
        list.add("accept");
        list.add("set");
        list.add("info");
        list.add("list");
        list.add("addfriend");
        list.add("friendlist");
        list.add("userlist");
        list.add("reject");
        list.add("exit");
        try {
            Socket socket = new Socket("localhost", 6667);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println(ois.readObject());
            for (String target : list) {
                TransDto transDto = new TransDto();
                transDto.setTarget(target);
                transDto.setUsername("jack");
                transDto.setPassword("12345");
                transDto.setIds(new Integer[]{1,2,3});
                transDto.setUser(new User(1,"jack","12345"));
                oos.writeObject(transDto);
                oos.flush();
                TransDto transDto1 = (TransDto) ois.readObject();
                System.out.println("commend:"+target);
                System.out.println("response:"+transDto1);

                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
