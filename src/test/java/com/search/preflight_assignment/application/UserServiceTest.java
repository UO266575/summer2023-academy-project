package com.search.preflight_assignment.application;

import com.search.preflight_assignment.domain.User;
import com.search.preflight_assignment.domain.UserAdapter;
import com.search.preflight_assignment.infrastructure.repositories.UserRepository;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private  UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldRetrieveEmptyUsers(){
        List<User> expected = Collections.emptyList();

        Mockito.when(userRepository.getUsers()).thenReturn(expected);

        List<User> result = userService.getUsers();

        assertEquals(expected, result);
    }

    @Test
    public void shouldRetrieveOneUser(){
        List<User> expected = List.of(new User(1L, "ton@ton.com", "Ton"));

        Mockito.when(userRepository.getUsers()).thenReturn(expected);

        List<User> result = userService.getUsers();

        assertEquals(expected, result);
    }

    @Test
    public void shouldNotFindUser(){
        Mockito.when(userRepository.getUserById(1L)).thenReturn(null);

        User result = userService.getUserById(1L);

        assertEquals(null, result);
    }

    @Test
    public void shouldFindOneUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        Mockito.when(userRepository.getUserById(1L)).thenReturn(user);

        User result = userService.getUserById(1L);

        assertEquals(user, result);
    }

    @Test
    public void shouldAddUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        Mockito.when(userService.getUserById(user.id())).thenReturn(user);

        boolean result = userService.addUser(user);

        assertEquals(true, result);
    }

    @Test
    public void shouldntAddUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        Mockito.when(userService.getUserById(user.id())).thenReturn(null);

        boolean result = userService.addUser(user);

        assertEquals(false, result);
    }

    @Test
    public void shouldUpdateUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        Mockito.when(userRepository.updateUser(user.id(), user)).thenReturn(true);

        boolean result = userService.updateUser(user.id(), user);

        assertEquals(true, result);
    }

    @Test
    public void shouldntUpdateUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        Mockito.when(userRepository.updateUser(user.id(), user)).thenReturn(false);

        boolean result = userService.updateUser(user.id(), user);

        assertEquals(false, result);
    }

    @Test
    public void shouldDeleteUser(){
        Mockito.when(userRepository.deleteUser(1L)).thenReturn(true);

        boolean result = userService.deleteUser(1L);

        assertEquals(true, result);
    }

    @Test
    public void shouldntDeleteUser(){
        Mockito.when(userRepository.deleteUser(1L)).thenReturn(false);

        boolean result = userService.deleteUser(1L);

        assertEquals(false, result);
    }
}