package models;

import java.io.Serializable;
import java.util.TreeSet;

public class User implements Comparable<User>, Serializable {
    private Integer id;
    private String username;
    private String realName;
    private String password;
    private String description;
    private String photoURL; // Should use URL?
    private String email;
    private String favorite;
    private TreeSet<Integer> friends;
    private TreeSet<Integer> pendingFriendRequests;
    private TreeSet<Integer> sendingFriendRequests;
    private Integer isPrivate;//0、所有人可见 1、好友可见 2、自己可见
    private boolean online;
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
        this.sendingFriendRequests = new TreeSet<>();
        this.photoURL = "https://www.gravatar.com/avatar/?d=mp";
        this.email = "";
        this.favorite = "";
        this.isPrivate = 0;
    }

    public User(Integer id, String username, String realName, String password, String description, String email, String favorite, Integer isPrivate, TreeSet<Integer> friends, TreeSet<Integer> pendingFriendRequests, TreeSet<Integer> sendingFriendRequests) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.description = description;
        this.photoURL = "https://www.gravatar.com/avatar/?d=mp";
        this.email = email;
        this.favorite = favorite;
        this.friends = friends;
        this.pendingFriendRequests = pendingFriendRequests;
        this.sendingFriendRequests = sendingFriendRequests;
        this.isPrivate = isPrivate;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
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

    public void setPrivate(Integer aPrivate) {
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

    public Integer isPrivate() {
        return isPrivate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public TreeSet<Integer> getSendingFriendRequests() {
        return sendingFriendRequests;
    }

    public void setSendingFriendRequests(TreeSet<Integer> sendingFriendRequests) {
        this.sendingFriendRequests = sendingFriendRequests;
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
                ", photoURL='" + photoURL + '\'' +
                ", email='" + email + '\'' +
                ", favorite='" + favorite + '\'' +
                ", friends=" + friends +
                ", pendingFriendRequests=" + pendingFriendRequests +
                ", sendingFriendRequests=" + sendingFriendRequests +
                ", isPrivate=" + isPrivate +
                ", online=" + online +
                '}';
    }

    public String getSaveString() {
        StringBuilder friendsSb = new StringBuilder("|");
        StringBuilder pendingFriendRequestsSb = new StringBuilder("|");
        StringBuilder sendingFriendRequestsSb = new StringBuilder("|");
        friends.forEach(e -> friendsSb.append(e).append("|"));
        pendingFriendRequests.forEach(e -> pendingFriendRequestsSb.append(e).append("|"));
        sendingFriendRequests.forEach(e -> sendingFriendRequestsSb.append(e).append("|"));

        return id +
                "," + username +
                "," + realName +
                "," + password +
                "," + description +
                "," + email +
                "," + favorite +
                "," + isPrivate +
                "," + friendsSb.toString() +
                "," + pendingFriendRequestsSb.toString() +
                "," + sendingFriendRequestsSb.toString();

    }
}
