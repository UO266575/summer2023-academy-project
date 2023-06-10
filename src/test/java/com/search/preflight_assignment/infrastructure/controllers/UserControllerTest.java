package com.search.preflight_assignment.infrastructure.controllers;

import com.search.preflight_assignment.application.UserService;
import com.search.preflight_assignment.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setUp() {
        userController = new UserController(userService);
    }

    @Test
    public void shouldRetrieveEmptyUsers(){
        List<User> expected = Collections.emptyList();

        Mockito.when(userService.getUsers()).thenReturn(expected);

        List<User> result = userController.getUsers();

        assertEquals(expected, result);
    }

    @Test
    public void shouldRetrieveOneUser(){
        List<User> expected = List.of(new User(1L, "ton@ton.com", "Ton"));

        Mockito.when(userService.getUsers()).thenReturn(expected);

        List<User> result = userController.getUsers();

        assertEquals(expected, result);
    }

    @Test
    public void shouldNotFindUser(){
        ResponseEntity<User> expected = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Mockito.when(userService.getUserById(1L)).thenReturn(null);

        ResponseEntity<User> result = userController.getUser(1L);

        assertEquals(expected, result);
    }

    @Test
    public void shouldFindOneUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        ResponseEntity<User> expected = ResponseEntity.ok(user);

        Mockito.when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<User> result = userController.getUser(1L);

        assertEquals(expected, result);
    }

    @Test
    public void shouldAddUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        ResponseEntity<String> expected = ResponseEntity.ok("User created successfully");

        Mockito.when(userService.addUser(user)).thenReturn(true);

        ResponseEntity<String> result = userController.addUser(user);

        assertEquals(expected, result);
    }

    @Test
    public void shouldntAddUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create user");

        Mockito.when(userService.addUser(user)).thenReturn(false);

        ResponseEntity<String> result = userController.addUser(user);

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdateUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        ResponseEntity<String> expected = ResponseEntity.ok("User updated successfully");

        Mockito.when(userService.updateUser(user.id(), user)).thenReturn(true);

        ResponseEntity<String> result = userController.updateUser(user);

        assertEquals(expected, result);
    }

    @Test
    public void shouldntUpdateUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update user");

        Mockito.when(userService.updateUser(user.id(), user)).thenReturn(false);

        ResponseEntity<String> result = userController.updateUser(user);

        assertEquals(expected, result);
    }

    @Test
    public void shouldDeleteUser(){
        ResponseEntity<String> expected = ResponseEntity.ok("User deleted successfully");

        Mockito.when(userService.deleteUser(1L)).thenReturn(true);

        ResponseEntity<String> result = userController.deleteUser(1L);

        assertEquals(expected, result);
    }

    @Test
    public void shouldntDeleteUser(){
        ResponseEntity<String> expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to delete user");

        Mockito.when(userService.deleteUser(1L)).thenReturn(false);

        ResponseEntity<String> result = userController.deleteUser(1L);

        assertEquals(expected, result);
    }
}