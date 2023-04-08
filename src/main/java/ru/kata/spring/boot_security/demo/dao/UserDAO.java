package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByUserName(String userName);
    void saveUser(User user);
    User update(User user);
    void delete(int id);
}
