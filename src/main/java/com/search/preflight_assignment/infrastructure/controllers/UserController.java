package com.search.preflight_assignment.infrastructure.controllers;

import com.search.preflight_assignment.application.UserService;
import com.search.preflight_assignment.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user) ? ResponseEntity.ok("User created successfully"):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        return userService.updateUser(user.id(), user) ? ResponseEntity.ok("User updated successfully"):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id)? ResponseEntity.ok("User deleted successfully"):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
    }

}
