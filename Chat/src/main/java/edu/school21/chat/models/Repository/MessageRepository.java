package edu.school21.chat.models.Repository;

import edu.school21.chat.models.Chat.Message;
import edu.school21.chat.models.Exception.NotSavedSubEntityException;

import java.sql.SQLException;
import java.util.Optional;

public interface MessageRepository {
    Optional<Message> findById(Long id) throws SQLException;
    void saveMessage(Message message) throws NotSavedSubEntityException, SQLException;
    void updateMessage(Message message) throws SQLException;
}
