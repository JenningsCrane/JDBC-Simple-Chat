package edu.school21.chat.models.Main;

import edu.school21.chat.models.Chat.User;
import edu.school21.chat.models.DataBaseLoad.DataBaseLoader;
import edu.school21.chat.models.Exception.NotSavedSubEntityException;
import edu.school21.chat.models.Repository.UsersRepositoryJdbcImpl;

import java.sql.*;
import java.util.List;

public class Program {
    public static void main(String[] args) throws SQLException {
            UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(DataBaseLoader.connectToDb());
        try {
            List<User> users = usersRepositoryJdbc.findAll(0,2);
            for (User user : users) {
                System.out.println(user.toString());
                System.out.println();
            }
        }  catch (NotSavedSubEntityException | SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}
