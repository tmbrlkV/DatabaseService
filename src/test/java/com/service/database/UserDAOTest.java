package com.service.database;

import com.chat.util.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void newUser() throws Exception {
        String login = "kek";
        String password = "kek";
        User user = new User(login, password);
        assertEquals(user.getPassword(), userDAO.newUser(user).getPassword());
    }

    @Test
    public void getUser() throws Exception {
        String login = "user";
        String password = "user";
        User user = new User(login, password);
        assertEquals(user.getLogin(), userDAO.getUserByLoginPassword(user).getLogin());
    }

    @Test
    public void getUserWithInvalidData() throws Exception {
        String login = "invalid";
        String password = "invalid";
        User user = new User(login, password);
        assertFalse( userDAO.getUserByLoginPassword(user).validation());
    }

    @Test
    public void getExistedUser() throws Exception {
        String login = "user";
        String password = "user";
        assertFalse(userDAO.newUser(new User(login, password)).validation());
    }

    @Test
    public void getUserByLogin() throws Exception {
        String login = "user";
        String password = "user";
        User userByLogin = userDAO.getUserByLogin(new User(login));
        assertEquals(password, userByLogin.getPassword());
    }

    @Test
    public void getNonExistingUserByLogin() throws Exception {
        String login = "kek";
        assertNull(userDAO.getUserByLogin(new User(login)).getLogin());
    }
}