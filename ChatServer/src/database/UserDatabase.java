package database;

import models.TransDto;
import models.User;
import utils.CustomLogger;
import workers.ClientWorker;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserDatabase {
    private static UserDatabase userDatabase;
    private final String DATABASE_FILENAME;
    private TreeSet<User> userList = new TreeSet<>();
    private ArrayList<ClientWorker> activeSessions = new ArrayList<>();

    private final CustomLogger logger = CustomLogger.getSingleton();

    private UserDatabase() {
        this("ChatServer/database.csv");
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
        for (User otherUser : userList) {
            otherUser.getSendingFriendRequests().remove(user.getId());
            otherUser.getPendingFriendRequests().remove(user.getId());
            otherUser.getSendingFriendRequests().remove(user.getId());
        }
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

    public synchronized boolean removePending(ClientWorker clientWorker,String username) {

        User user = userList.stream().filter(x -> x.getUsername().equals(username)).findAny().orElse(null);
        if (user != null) {
            //删除用户自己列表里的
            clientWorker.getUser().getPendingFriendRequests().remove(user.getId());
            //删除对方用户列表里的
            user.getSendingFriendRequests().remove(clientWorker.getUser().getId());
            return true;
        }
        return false;
    }

    public synchronized User authenticateAndGetUser(String username, String password) {
        return userList.stream().filter(user -> user.getUsername().equals(username) &&
                user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public synchronized String friendUserByUsername(User user, String username) {
        User targetUser = userList.stream().filter(e -> e.getUsername().equals(username)).findAny().orElse(null);

        if (targetUser == null ||
                targetUser.getPendingFriendRequests().contains(user.getId()) ||
                user.getFriends().contains(targetUser.getId()) ) {
            return "Sorry, but maybe you already added them?";
        }

        if(user.getPendingFriendRequests().contains(targetUser.getId())){
            return "Sorry, but maybe he has in your pending?";
        }

        targetUser.getPendingFriendRequests().add(user.getId());
        user.getSendingFriendRequests().add(targetUser.getId());
        return "";
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

    public User[] getAllUsers() {
        return userList.stream().toArray(User[]::new);
    }

    public User[] getOnlineUserArray() {
        int size = activeSessions.size();
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            users[i] = activeSessions.get(i).getUser();
        }
        return users;
    }

    public User[] getFriends(User user) {
        TreeSet<Integer> friends = user.getFriends();
        return friends.stream().map(x -> {
            return userList.stream().filter(y -> y.getId() == x).findAny().orElse(null);
        }).filter(Objects::nonNull)
                .peek(y->y.setOnline(activeSessions.stream().anyMatch(z->z.getUser().getId()==y.getId())))
                .toArray(User[]::new);
    }

    public User[] getUsers(Integer[] usernames) {
        return Arrays.stream(usernames).map(x -> {
            return userList.stream().filter(y -> y.getId()==x).findAny().orElse(null);
        }).filter(Objects::nonNull)
                .peek(y->y.setOnline(activeSessions.stream().anyMatch(z->z.getUser().getId()==y.getId())))
                .toArray(User[]::new);
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
            int id = Integer.parseInt(data[0]);
            TreeSet<Integer> friendList = Arrays.stream(data[8].split("\\|"))
                                             .filter(e -> !"".equals(e))
                                             .mapToInt(Integer::parseInt)
                                             .boxed()
                                             .collect(Collectors.toCollection(
                                                     TreeSet::new
                                             ));
            TreeSet<Integer> requestList = Arrays.stream(data[9].split("\\|"))
                                              .filter(e -> !"".equals(e))
                                              .mapToInt(Integer::parseInt)
                                              .boxed()
                                              .collect(Collectors.toCollection(
                                                      TreeSet::new
                                              ));

            TreeSet<Integer> sendRequestList = Arrays.stream(data[10].split("\\|"))
                    .filter(e -> !"".equals(e))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toCollection(
                            TreeSet::new
                    ));
            userList.add(new User(id, data[1], data[2], data[3], data[4], data[5], data[6], Integer.parseInt(data[7]), friendList, requestList,sendRequestList));
        }

        bufferedReader.close();
    }

    public void saveProfiles() throws IOException {
        File databaseFile = new File(DATABASE_FILENAME);
        PrintWriter printWriter = new PrintWriter(databaseFile);
        printWriter.println("id,username,realName,password,description,email,favorite,isPrivate,friends,pendingFriendRequests,sendingFriendRequests");
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
