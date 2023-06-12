package com.search.preflight_assignment.infrastructure.repositories;

import com.search.preflight_assignment.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Mock
    private ConcurrentHashMap<Long, User> users;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void shouldRetrieveEmptyUsers(){
        List<User> expected = Collections.emptyList();

        Mockito.when(users.values()).thenReturn(expected);

        List<User> result = userRepository.getUsers();

        assertEquals(expected, result);
    }

    @Test
    public void shouldRetrieveOneUser(){
        List<User> expected = List.of(new User(1L, "ton@ton.com", "Ton"));

        Mockito.when(users.values()).thenReturn(expected);

        List<User> result = userRepository.getUsers();

        assertEquals(expected, result);
    }

    @Test
    public void shouldNotFindUser(){
        Mockito.when(users.get(1L)).thenReturn(null);

        User result = userRepository.getUserById(1L);

        assertEquals(null, result);
    }

    @Test
    public void shouldFindOneUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        User expected = user;

        Mockito.when(users.get(1L)).thenReturn(user);

        User result = userRepository.getUserById(1L);

        assertEquals(expected, result);
    }

    @Test
    public void shouldDeleteUser(){
        User user = new User(1L, "ton@ton.com", "Ton");

        Mockito.when(users.remove(1L)).thenReturn(user);

        boolean result = userRepository.deleteUser(1L);

        assertEquals(true, result);
    }

    @Test
    public void shouldntDeleteUser(){
        Mockito.when(users.remove(1L)).thenReturn(null);

        boolean result = userRepository.deleteUser(1L);

        assertEquals(false, result);
    }
}