package models;

import java.io.Serializable;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class User implements Comparable<User>, Serializable {
    private Integer id;
    private String username;
    private String realName;
    private String password;
    private String description;
    private String photoURL; // Should use URL?
    private TreeSet<Integer> friends;
    private TreeSet<Integer> pendingFriendRequests;
    private boolean isPrivate;
    private static final long serialVersionUID = 2L;

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = ""; // Redacted for privacy
        this.realName = user.realName;
        this.description = user.description;
        this.friends = user.friends;
        this.pendingFriendRequests = user.pendingFriendRequests;
        this.photoURL = user.photoURL;
        this.isPrivate = user.isPrivate;
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = "Unnamed";
        this.description = "This user hasn't wrote anything yet.";
        this.friends = new TreeSet<>();
        this.pendingFriendRequests = new TreeSet<>();
        this.photoURL = "https://www.gravatar.com/avatar/?d=mp";
        this.isPrivate = false;
    }

    public User(Integer id, String username, String realName, String password, String description, TreeSet<Integer> friends, TreeSet<Integer> pendingFriendRequests) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.description = description;
        this.friends = friends;
        this.pendingFriendRequests = pendingFriendRequests;
        this.isPrivate = false;
        this.photoURL = "https://www.gravatar.com/avatar/?d=mp";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setFriends(TreeSet<Integer> friends) {
        this.friends = friends;
    }

    public void setPendingFriendRequests(TreeSet<Integer> pendingFriendRequests) {
        this.pendingFriendRequests = pendingFriendRequests;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public String getDescription() {
        return description;
    }

    public TreeSet<Integer> getFriends() {
        return friends;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TreeSet<Integer> getPendingFriendRequests() {
        return pendingFriendRequests;
    }

    public int getId() {
        return id;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public int compareTo(User o) {
        return this.getId() - o.getId();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", friends=" + friends +
                ", pendingFriendRequests=" + pendingFriendRequests +
                '}';
    }

    public String getSaveString() {
        StringBuilder friendsSb = new StringBuilder("|");
        StringBuilder pendingFriendRequestsSb = new StringBuilder("|");
        friends.forEach(e -> friendsSb.append(e).append("|"));
        pendingFriendRequests.forEach(e -> pendingFriendRequestsSb.append(e).append("|"));

        return id +
                "," + username +
                "," + realName +
                "," + password +
                "," + description +
                "," + friendsSb.toString() +
                "," + pendingFriendRequestsSb.toString();
    }
}
