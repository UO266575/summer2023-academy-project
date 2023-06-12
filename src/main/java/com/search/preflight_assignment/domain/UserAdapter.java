package com.search.preflight_assignment.domain;

import java.util.List;

public interface UserAdapter {

    List<User> getUsers();

    User getUserById(Long id);

    void addUser(User user);

    void updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
