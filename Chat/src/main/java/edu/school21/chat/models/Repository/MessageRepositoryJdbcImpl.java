package edu.school21.chat.models.Repository;

import edu.school21.chat.models.Chat.Chatroom;
import edu.school21.chat.models.Chat.Message;
import edu.school21.chat.models.Chat.User;
import edu.school21.chat.models.Exception.NotSavedSubEntityException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessageRepository {
    private static Connection connection;

    public MessageRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        MessageRepositoryJdbcImpl.connection = dataSource.getConnection();
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        try(PreparedStatement userStatement = connection.prepareStatement("SELECT * FROM chat.message WHERE messageid = " + id);
            ResultSet resultSet = userStatement.executeQuery()) {
            resultSet.next();
            LocalDateTime date = null;
            if(resultSet.getTimestamp(5) != null) {
                date = resultSet.getTimestamp(5).toLocalDateTime();
            }
            System.out.println("Message : {\n" +
                    "  id=" + resultSet.getInt(1) + ",\n" +
                    "  " + findUserById(resultSet.getLong(2)) + ",\n" +
                    "  " + findRoomById(resultSet.getLong(3)) + ",\n" +
                    "  text=\"" + resultSet.getString(4) + "\",\n" +
                    "  dateTime=" + date + "\n" +
                    "}");
            return Optional.of(new Message(resultSet.getLong(1), findUserById(resultSet.getLong(2)), findRoomById(resultSet.getLong(3)),
                    resultSet.getString(4),  date));
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }

    private User findUserById(Long id) throws SQLException {
        try(PreparedStatement userStatement = connection.prepareStatement("SELECT * FROM chat.users WHERE userid = " + id);
            ResultSet resultSet = userStatement.executeQuery()) {
            resultSet.next();
            return new User(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }
    private Chatroom findRoomById(Long id) throws SQLException {
        try(PreparedStatement chatRoomStatement = connection.prepareStatement("SELECT * FROM chat.chatroom WHERE chatroomid = " + id);
            ResultSet resultSet = chatRoomStatement.executeQuery()) {
            resultSet.next();
            return new Chatroom(resultSet.getLong(1),
                    resultSet.getString(2),
                    null,
                    new ArrayList<>()
            );
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }

    @Override
    public void saveMessage(Message message) throws NotSavedSubEntityException, SQLException {
        checkMassage(message, false);
        String localDateTime = "'" + Timestamp.valueOf(message.getDateTime()) + "'";
        try (Statement chatRoomStatement = connection.createStatement()) {
            Long userId = message.getMessageAuthor().getUserId();
            Long roomId = message.getMessageRoom().getChatRoomId();
            ResultSet resultSet = chatRoomStatement.executeQuery("SELECT userid FROM chat.users WHERE userid = " + userId);
            if(!resultSet.next()) {
                throw new NotSavedSubEntityException("User id doesn't exist");
            }
            resultSet = chatRoomStatement.executeQuery("SELECT chatroomid FROM chat.chatroom WHERE chatroomid = " + roomId);
            if(!resultSet.next()) {
                throw new NotSavedSubEntityException("Chatroom id doesn't exist");
            }
            resultSet = chatRoomStatement.executeQuery("INSERT INTO chat.message(messageauthor, messageroom, messagetext, messagedatetime) VALUES (" +
                    userId + ", " + roomId + ", '" + message.getMessageText() + "', " + localDateTime + ") RETURNING messageid");
            resultSet.next();
            message.setMessageId(resultSet.getLong(1));
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }

    @Override
    public void updateMessage(Message message) throws SQLException {
        checkMassage(message, true);
        Long messageId = message.getMessageId();
        Long userId = message.getMessageAuthor().getUserId();
        Long roomId = message.getMessageRoom().getChatRoomId();
        Timestamp localDateTime = null;
        if (message.getDateTime() != null) {
            localDateTime = Timestamp.valueOf(message.getDateTime());
        }
        String messageText = message.getMessageText();
        try (PreparedStatement chatRoomStatement = connection.prepareStatement("UPDATE chat.message SET messageauthor=?, messageroom=?, messagetext=?, messagedatetime=? WHERE messageid=?")) {
            chatRoomStatement.setLong(1, userId);
            chatRoomStatement.setLong(2, roomId);
            chatRoomStatement.setString(3, messageText);
            chatRoomStatement.setTimestamp(4, localDateTime);
            chatRoomStatement.setLong(5, messageId);
            chatRoomStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }
    private void checkMassage(Message message, boolean isUpdate) throws SQLException {
        if(message.getMessageAuthor() == null && !isUpdate) {
            throw new NotSavedSubEntityException("Owner hasn't supported");
        }
        if(message.getMessageRoom() == null && !isUpdate) {
            throw new NotSavedSubEntityException("ChatRoom hasn't supported");
        }
        if(message.getMessageText() == null || message.getMessageText().length() < 1 && !isUpdate) {
            throw new NotSavedSubEntityException("Message text hasn't supported");
        }
        if(message.getDateTime() == null && !isUpdate) {
            throw new NotSavedSubEntityException("Date time hasn't supported");
        }
        try (Statement chatRoomStatement = connection.createStatement()) {
            Long userId = message.getMessageAuthor().getUserId();
            Long roomId = message.getMessageRoom().getChatRoomId();
            ResultSet resultSet = chatRoomStatement.executeQuery("SELECT * FROM chat.users WHERE userid = " + userId);
            if(!resultSet.next()) {
                throw new NotSavedSubEntityException("User id doesn't exist");
            }
            resultSet = chatRoomStatement.executeQuery("SELECT * FROM chat.chatroom WHERE chatroomid = " + roomId);
            if(!resultSet.next()) {
                throw new NotSavedSubEntityException("Chatroom id doesn't exist");
            }
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }
}
