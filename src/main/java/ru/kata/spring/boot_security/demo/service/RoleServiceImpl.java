package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roledao;

    @Autowired
    public RoleServiceImpl(RoleDao roledao) {
        this.roledao = roledao;
    }


    @Override
    @Transactional
    public Role getRoleByName(String userName) {
        return roledao.getRoleByName(userName);
    }

    @Override
    public Role getRoleById(int id) {
        return roledao.getRoleById(id);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roledao.saveRole(role);
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roledao.getRoles();
    }
}
