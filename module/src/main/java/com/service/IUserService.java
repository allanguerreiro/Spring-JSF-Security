package com.service;

import com.model.domain.User;

import java.util.List;

/**
 * User Service Interface
 *
 * @author
 * @version 1.0.0
 */

public interface IUserService {

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public User getUserByUserName(String username);

    public User getUserByUserNameAndPassword(String username, String password);

    public List<User> getUsers();

    public void initiateResetPassword(User user);
}
