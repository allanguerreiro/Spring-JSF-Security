package com.service.impl;

import com.model.dao.IUserDAO;
import com.model.domain.User;
import com.service.IUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * User Service
 *
 * @author
 * @version 1.0.0
 */
@Named
@Transactional(readOnly = true)
public class UserService implements IUserService {

    @Getter @Setter
    @Inject
    IUserDAO userDAO;

    @Transactional(readOnly = false)
    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        getUserDAO().deleteUser(user);
    }

    @Transactional(readOnly = false)
    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public User getUserByUserName(String username) {
        return getUserDAO().getUserByUserName(username);
    }

    public List<User> getUsers() {
        return getUserDAO().getUsers();
    }

    public void initiateResetPassword(User user) {}

    public User getUserByUserNameAndPassword(String username, String password) {
        return getUserDAO().getUserByUserNameAndPassword(username, password);
    }
}
