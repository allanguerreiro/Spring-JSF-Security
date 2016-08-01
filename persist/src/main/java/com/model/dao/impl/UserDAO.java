package com.model.dao.impl;

import com.model.dao.IUserDAO;
import com.model.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * User DAO
 *
 * @author
 * @version 1.0.0
 */
@Named
@Slf4j
public class UserDAO implements IUserDAO {
    @Inject
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.save(user);
        log.info("Save a user!");
        trans.commit();
    }

    public void deleteUser(User user) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.delete(user);
        log.info("Delete a user!");
        trans.commit();
    }

    public void updateUser(User user) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.update(user);
        log.info("Update a user!");
        trans.commit();
    }

    public User getUserByUserName(String username) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();

        List<?> list = session
                .createQuery("from User where username=?").setParameter(0, username)
                .list();

        trans.commit();
        log.info("Select a user by username!");
        return (User) list.get(0);
    }

    public User getUserByUserNameAndPassword(String username, String password) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();

        String sql = "from User where username = :username and password = :password";
        User user = (User) session.createQuery(sql)
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
        trans.commit();
        log.info("Select a user by username!");
        return user;
    }

    public List<User> getUsers() {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<User> list = (List<User>) session.createQuery("from User").list();

        trans.commit();
        log.info("Select all users!");
        return list;
    }

    public void initiateResetPassword(User user) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();

        user.setPassword(UUID.randomUUID().toString());
        session.merge(user);
        trans.commit();
        log.info("Set action token for password reset mail for user {}", user.getUsername());
    }
}
