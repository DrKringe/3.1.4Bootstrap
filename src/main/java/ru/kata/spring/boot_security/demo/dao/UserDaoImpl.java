
package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByUserName(String userName) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        if(getUserById(id) != null) {
            entityManager.remove(getUserById(id));
        }
    }
}
