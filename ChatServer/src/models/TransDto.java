package models;

import java.io.Serializable;

public class TransDto implements Serializable {
    private boolean isSuccess;
    private String source;
    private String target;
    private String msg;
    private User user;
    private User[] users;
    private String username;
    private String password;
    private static final long serialVersionUID = 1L;
    private Integer[] ids;

    public TransDto() {
    }

    public TransDto(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public TransDto(boolean isSuccess, String source, String target, String msg) {
        this.isSuccess = isSuccess;
        this.source = source;
        this.target = target;
        this.msg = msg;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
