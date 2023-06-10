package com.search.preflight_assignment.application;

import com.search.preflight_assignment.domain.User;
import com.search.preflight_assignment.domain.UserAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserAdapter userAdapter;

    public UserService(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    public List<User> getUsers() {
        return userAdapter.getUsers();
    }

    public User getUserById(Long id) {
        return userAdapter.getUserById(id);
    }

    public boolean addUser(User user) {
        if (getUserById(user.id()) == null) {
            userAdapter.addUser(user);
            return true;
        }
        return false;
    }

    public boolean updateUser(Long id, User user) {
        return userAdapter.updateUser(id, user);
    }

    public boolean deleteUser(Long id) {
        return userAdapter.deleteUser(id);
    }
}
