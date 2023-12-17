package edu.school21.chat.models.Repository;

import edu.school21.chat.models.Chat.Chatroom;
import edu.school21.chat.models.Chat.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private static Connection connection;
    private static final String SQL_QUERY = "SELECT Chatroom.chatroomid, Chatroom.chatroomname, Chatroom.chatroomowner, \"U*\".userid, \"U*\".login, \"U*\".password\n" +
            "FROM (SELECT * FROM Chat.Users LIMIT ? OFFSET ?) as \"U*\"\n" +
            "JOIN Chat.user_chatrooms ON user_chatrooms.userid = \"U*\".userid\n" +
            "JOIN Chat.Chatroom ON user_chatrooms.chatroomid = chatroom.chatroomid";

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        UsersRepositoryJdbcImpl.connection = dataSource.getConnection();
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY)) {
            List<User> users = new ArrayList<>();
            List<Chatroom> chatRooms = new ArrayList<>();
            int offset = page * size;

            preparedStatement.setLong(1, size);
            preparedStatement.setLong(2, offset);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = findUserById(users, resultSet.getLong("userid"));

                if (user == null) {
                    user = new User(
                            resultSet.getLong("userid"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            new ArrayList<>(),
                            new ArrayList<>()
                    );
                    users.add(user);
                }
                Chatroom chatRoom = findRoomById(chatRooms, resultSet.getLong("chatroomid"));

                if(chatRoom == null) {
                    chatRoom = new Chatroom(
                            resultSet.getLong("chatroomid"),
                            resultSet.getString("chatroomname"),
                            user,
                            null
                    );
                    chatRooms.add(chatRoom);
                }

                if (user.getUserId() == resultSet.getLong("chatroomowner")) {
                    user.getCreatedRooms().add(chatRoom);
                }

                user.getSocializesRooms().add(chatRoom);
            }
            return users;
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }
    private User findUserById(List<User> users, long userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
    private Chatroom findRoomById(List<Chatroom> chatRooms, long roomId) {
        for (Chatroom room : chatRooms) {
            if (room.getChatRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

}
