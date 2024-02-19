package com.littlesekii.auth.modules.user.repository;

import com.littlesekii.auth.modules.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public class UserRepository {

    Set<User> users = new HashSet<>();

    public User findById(UUID id) {
        return users.stream()
                .filter(user ->  user.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(user ->  user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void save(User user) {
        users.add(user);
    }

}
