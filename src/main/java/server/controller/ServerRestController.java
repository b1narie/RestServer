package server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.Role;
import server.model.User;
import server.service.RoleService;
import server.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ServerRestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        userService.deleteUser(userService.getUserById(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/login/{login}")
    public User getUserByName(@PathVariable("login") String login) {
        return userService.getUserByLogin(login);
    }
}
