package listner;

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
        send("info");
    }

    @Override
    public void callBack(String msg) {
        if("User does not exist.".equals(msg)){
            System.out.println("User does not exist.");
            return;
        }
        String[] data = msg.trim().split(",");
        int id = Integer.parseInt(data[0]);
        TreeSet<Integer> friendList = Arrays.stream(data[5].split("\\|"))
                .filter(e -> !"".equals(e))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(
                        TreeSet::new
                ));
        TreeSet<Integer> requestList = Arrays.stream(data[6].split("\\|"))
                .filter(e -> !"".equals(e))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(
                        TreeSet::new
                ));
        ChatClient.user = new User(id, data[1], data[2], data[3], data[4], friendList, requestList);
    }
}
