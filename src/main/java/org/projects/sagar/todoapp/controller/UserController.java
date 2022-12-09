package org.projects.sagar.todoapp.controller;

import jakarta.validation.Valid;
import org.projects.sagar.todoapp.exception.UserNotFoundException;
import org.projects.sagar.todoapp.model.ToDo;
import org.projects.sagar.todoapp.model.User;
import org.projects.sagar.todoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo-app")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> userList(){
        return userService.getUsers();
    }

    @GetMapping("/users/{userID}")
    public User retrieveUser(@PathVariable int userID){
        return userService.getUser(userID).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users/{userID}")
    public ResponseEntity<?> updateUser(@PathVariable int userID, @Valid @RequestBody User user){
        userService.getUser(userID).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setId(userID);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity<?> deleteUSer(@PathVariable int userID){
        userService.getUser(userID).orElseThrow(() -> new UserNotFoundException("User not found"));
        userService.deleteUser(userID);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/users/{userID}/todos")
    public List<ToDo> retrieveToDoList(@PathVariable int userID, @RequestParam (required = false) boolean completed){
        User user = userService.getUser(userID).orElseThrow(() -> new UserNotFoundException("User not found"));
        LOGGER.info("The retrieved User {} is {}", userID, user);
        if(!completed){
            return user.getToDoList();
        }
        return user.getToDoList().stream().filter(item -> item.getCompleted()).toList();
    }
}
