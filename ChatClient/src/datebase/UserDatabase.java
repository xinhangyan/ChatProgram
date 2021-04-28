package datebase;

import models.User;
import view.AllFrame;
import view.BaseDialog;
import works.ChatClient;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *  This class serves for import and export profile information.
 */

public class UserDatabase {
    private static UserDatabase userDatabase;
    private final String DATABASE_FILENAME;
    private TreeSet<User> userList = new TreeSet<>();


    private UserDatabase() {
        this("ChatClient/profile.csv");
    }

    private UserDatabase(String pathToDatabase) {
        DATABASE_FILENAME = pathToDatabase;
    }

    public User getUser(String username){
        return userList.stream().filter(x->x.getUsername().equals(username)).findAny().orElse(null);
    }
    public synchronized void loadProfiles() throws IOException {
        File databaseFile = new File(DATABASE_FILENAME);
        System.out.println(databaseFile.getAbsolutePath());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(databaseFile));

        String line;
        bufferedReader.readLine(); // Skip firstLine
        while ((line = bufferedReader.readLine()) != null) {
            line = line+","+"*";//for null
            String[] data = line.split(",");
            userList.stream().filter(x->x.getUsername().equals(data[0])).findAny().ifPresent(x->{
                x.setDescription(data[1]);
                x.setEmail(data[2]);
                x.setFavorite(data[3]);
            });
        }

        bufferedReader.close();
    }

    public void saveProfiles() throws IOException {
        File databaseFile = new File(DATABASE_FILENAME);
        if(!databaseFile.exists()){
            databaseFile.createNewFile();
        }
        PrintWriter printWriter = new PrintWriter(databaseFile);
        printWriter.println("username,description,email,favorite");
        userList.add(ChatClient.user);
        for (User user : userList) {
            System.out.println(user.getSaveString());
            printWriter.println(user.getSaveString());
        }
        printWriter.close();
    }

    public synchronized void deleteUser(User user) {
        userList.remove(user);
    }

    public static UserDatabase getSingleton() {
        if (userDatabase == null) {
            userDatabase = new UserDatabase();
        }
        return userDatabase;
    }
}
