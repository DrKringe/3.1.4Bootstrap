package ru.kata.spring.boot_security.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.createQuery("SELECT u FROM Role u WHERE u.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.createQuery("SELECT u FROM Role u WHERE u.id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
    }
}
