package database;

import models.User;
import utils.CustomLogger;
import workers.ClientWorker;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UserDatabase {
    private static UserDatabase userDatabase;
    private final String DATABASE_FILENAME;
    private TreeSet<User> userList = new TreeSet<>();
    private ArrayList<ClientWorker> activeSessions = new ArrayList<>();

    private final CustomLogger logger = CustomLogger.getSingleton();

    private UserDatabase() {
        this("../database.csv");
    }

    private UserDatabase(String pathToDatabase) {
        DATABASE_FILENAME = pathToDatabase;
    }

    public synchronized boolean isUserWithUsernamePresent(String username) {
        return userList.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public synchronized User addUser(String username, String password) {
        int maxId = this.userList.stream().mapToInt(User::getId).max().orElse(0);
        User user = new User(maxId + 1, username, password);
        userList.add(user);
        return user;
    }

    public synchronized void deleteUser(User user) {
        userList.remove(user);
    }

    public List<String> getUsernamesByIds(TreeSet<Integer> ids) {
        return userList.stream().filter(e -> ids.contains(e.getId())).map(User::getUsername).collect(Collectors.toList());
    }

    public synchronized User getUser(String username) {
        User user = userList.stream().filter(usr -> usr.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return null;
        }
        return new User(user);
    }

    public synchronized User authenticateAndGetUser(String username, String password) {
        return userList.stream().filter(user -> user.getUsername().equals(username) &&
                user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public synchronized boolean friendUserByUsername(User user, String username) {
        User targetUser = userList.stream().filter(e -> e.getUsername().equals(username)).findAny().orElse(null);

        if (targetUser == null ||
                targetUser.getPendingFriendRequests().contains(user.getId()) ||
                user.getFriends().contains(targetUser.getId())) {
            return false;
        }

        targetUser.getPendingFriendRequests().add(user.getId());

        return true;
    }

    public synchronized boolean friendUserById(User user, int id) {
        User targetUser = userList.stream().filter(e -> e.getId() == id).findAny().orElse(null);

        if (targetUser == null ||
                targetUser.getPendingFriendRequests().contains(user.getId()) ||
                user.getFriends().contains(targetUser.getId())) {
            return false;
        }

        targetUser.getPendingFriendRequests().add(user.getId());

        return true;
    }

    public void addSession(ClientWorker clientWorker) {
        activeSessions.add(clientWorker);
    }

    public void removeSession(ClientWorker clientWorker) {
        activeSessions.remove(clientWorker);
    }

    public ArrayList<ClientWorker> getActiveSessions() {
        return activeSessions;
    }

    public ArrayList<String> getOnlineUsers() {
        ArrayList<String> onlineUsers = new ArrayList<>();
        activeSessions.forEach(e -> {
            User user = e.getUser();
            onlineUsers.add(user != null ? user.getUsername() : "Anonymous User from " + e.getRemoteAddress());
        });
        return onlineUsers;
    }

    public synchronized void loadProfiles() throws IOException {
        File databaseFile = new File(DATABASE_FILENAME);
        if (!databaseFile.exists()) {
            throw new FileNotFoundException("Database file doesn't exist.");
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(databaseFile));

        String line;
        bufferedReader.readLine(); // Skip firstLine
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
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
            userList.add(new User(id, data[1], data[2], data[3], data[4], friendList, requestList));
        }

        bufferedReader.close();
    }

    public void saveProfiles() throws IOException {
        File databaseFile = new File(DATABASE_FILENAME);
        PrintWriter printWriter = new PrintWriter(databaseFile);
        printWriter.println("id,username,realName,password,description,friends,pendingFriendRequests");
        for (User user : userList) {
            printWriter.println(user.getSaveString());
        }
        printWriter.close();
    }

    public static UserDatabase getSingleton() {
        if (userDatabase == null) {
            userDatabase = new UserDatabase();
        }
        return userDatabase;
    }
}
