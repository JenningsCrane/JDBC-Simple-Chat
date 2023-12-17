package edu.school21.chat.models.Chat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long chatRoomId;
    private String chatRoomName;
    private User chatRoomOwner;
    private List<Message> chatRoomMessages;

    public Chatroom(Long id, String name, User owner, List<Message> messages) {
        this.chatRoomId = id;
        this.chatRoomName = name;
        this.chatRoomOwner = owner;
        this.chatRoomMessages = messages;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public User getChatRoomOwner() {
        return chatRoomOwner;
    }

    public void setChatRoomOwner(User chatRoomOwner) {
        this.chatRoomOwner = chatRoomOwner;
    }

    public List<Message> getChatRoomMessages() {
        return chatRoomMessages;
    }

    public void setChatRoomMessages(List<Message> chatRoomMessages) {
        this.chatRoomMessages = chatRoomMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatRoom = (Chatroom) o;
        return Objects.equals(chatRoomId, chatRoom.chatRoomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatRoomId);
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + chatRoomId +
                ", chatName='" + chatRoomName + '\'' +
                ", chatOwner='" + chatRoomOwner + '\'' +
                '}';
    }
}
