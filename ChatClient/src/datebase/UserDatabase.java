package datebase;

import models.User;
import works.ChatClient;

import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        if (!databaseFile.exists()) {
            throw new FileNotFoundException("Database file doesn't exist.");
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(databaseFile));

        String line;
        bufferedReader.readLine(); // Skip firstLine
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            userList.add(new User(data[0], data[1], data[2], data[3]));
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
