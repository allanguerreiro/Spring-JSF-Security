package com.model.dao;

import com.model.domain.User;

import java.util.List;

/**
 * Iser DAO Interface
 *
 * @author
 * @version 1.0.0
 */
public interface IUserDAO {
    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public User getUserByUserName(String username);

    public List<User> getUsers();

    public void initiateResetPassword(User user);

    public User getUserByUserNameAndPassword(String username, String password);
}
