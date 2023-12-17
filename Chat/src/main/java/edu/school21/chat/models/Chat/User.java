package edu.school21.chat.models.Chat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class User {
    private Long userId;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializesRooms;

    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socializesRooms) {
        this.userId = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.socializesRooms = socializesRooms;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getSocializesRooms() {
        return socializesRooms;
    }

    public void setSocializesRooms(List<Chatroom> socializesRooms) {
        this.socializesRooms = socializesRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
