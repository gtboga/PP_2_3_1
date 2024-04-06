package application.dao;

import application.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void updateUser(int id, User user);

    void editUser(User user);

    User readUser(int id);

    User deleteUser(int id);

    void addUser(User user);
}