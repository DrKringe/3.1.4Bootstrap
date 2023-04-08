package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Integer[] userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (userRoles != null) {
            Arrays.stream(userRoles).forEach(id -> roles.add(roleService.getRoleById(id)));
        }
        System.out.println(userRoles.length);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        Map<Role, Boolean> roles = new TreeMap<>((Comparator<Role>) this :: sortingRole);
        roleService.getRoles().forEach(r -> roles.put(r, false));
        model.addAttribute("roles", roles);
        return "newUser";

    }


    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false) Integer[] userRoles) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (userRoles != null) {
            Arrays.stream(userRoles).forEach(id -> roles.add(roleService.getRoleById(id)));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    private int sortingRole(Role r1, Role r2) {
        return (int) (r1.getId() - r2.getId());
    }

}
