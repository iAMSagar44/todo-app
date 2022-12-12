package org.projects.sagar.todoapp.service;

import org.projects.sagar.todoapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    Optional<User> getUser(Integer id);

    User saveUser(User user);

    void deleteUser(Integer id);

}
