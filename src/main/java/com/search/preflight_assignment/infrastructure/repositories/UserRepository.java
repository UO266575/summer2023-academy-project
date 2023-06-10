package com.search.preflight_assignment.infrastructure.repositories;

import com.search.preflight_assignment.domain.User;
import com.search.preflight_assignment.domain.UserAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepository implements UserAdapter {

    private ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<Long, User>();

    @Override
    public List<User> getUsers(){
        return new ArrayList<User>(users.values());
    }

    @Override
    public User getUserById(Long id){
        return users.get(id);
    }

    @Override
    public void addUser(User user){
        users.put(user.id(), user);
    }

    @Override
    public boolean updateUser(Long id, User user){
        return users.put(getUserById(id).id(), user) != null;  //No sé si es redundante y aquí puede lanzar nullpointer
    }

    @Override
    public boolean deleteUser(Long id){
        return users.remove(id)!=null;
    }
}
