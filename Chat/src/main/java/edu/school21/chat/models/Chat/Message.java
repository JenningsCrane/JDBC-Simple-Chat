package edu.school21.chat.models.Chat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long messageId;
    private User messageAuthor;
    private Chatroom messageRoom;
    private String messageText;
    private LocalDateTime dateTime;

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime dateTime){
        this.messageId = id;
        this.messageAuthor = author;
        this.messageRoom = room;
        this.messageText = text;
        this.dateTime = dateTime;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(User messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public Chatroom getMessageRoom() {
        return messageRoom;
    }

    public void setMessageRoom(Chatroom messageRoom) {
        this.messageRoom = messageRoom;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + messageId +
                ", messageOwner='" + messageAuthor + '\'' +
                ", chatRoom=" + messageRoom +
                ", messageText='" + messageText + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
