package org.projects.sagar.todoapp.controller;

import jakarta.validation.Valid;
import org.projects.sagar.todoapp.exception.ToDoNotFoundException;
import org.projects.sagar.todoapp.exception.UserNotFoundException;
import org.projects.sagar.todoapp.model.ToDo;
import org.projects.sagar.todoapp.model.User;
import org.projects.sagar.todoapp.service.ToDoService;
import org.projects.sagar.todoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/todo-app")
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoController.class);
    private final ToDoService toDoService;
    private final UserService userService;

    @Autowired
    public ToDoController(ToDoService toDoService, UserService userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }

    @PostMapping("/users/{userID}/todo")
    ResponseEntity<?> addTodoItem(@PathVariable int userID, @Valid @RequestBody ToDo toDo){
        User user = userService.getUser(userID).orElseThrow(() -> new UserNotFoundException("User not found exception"));
        toDo.setUser(user);
        LOGGER.info("The user {} is {}", userID, user);
        toDoService.addTodoItem(toDo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{userID}/todos/{todoID}")
    ToDo retrieveToDo(@PathVariable int userID, @PathVariable int todoID){
        User user = userService.getUser(userID).orElseThrow(() -> new UserNotFoundException("User not found exception"));
        return toDoService.getTodo(todoID,userID).orElseThrow(() -> new ToDoNotFoundException("item not found"));

    }

    @PutMapping("/users/{userID}/todo/{todoID}")
    ResponseEntity<?> updateToDo(@PathVariable int userID, @PathVariable int todoID, @Valid @RequestBody ToDo toDo){
        User user = userService.getUser(userID).orElseThrow(() -> new UserNotFoundException("User not found exception"));
        toDoService.getTodo(todoID, userID).orElseThrow(() -> new ToDoNotFoundException("item not found"));
        toDo.setUser(user);
        toDo.setId(todoID);
        if (toDo.getCompleted()) {
            toDo.setCompletedDate(LocalDateTime.now());
        } else {
            toDo.setCompletedDate(null);
        }
        LOGGER.info("The item being updated is :::: {}", toDo);
        toDoService.addTodoItem(toDo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{userID}/todos")
    ResponseEntity<?> deleteToDos(@PathVariable int userID, @RequestParam(required = false) boolean completed){
        LOGGER.info("Delete todos invoked. Request param value {}", completed);
        User user = userService.getUser(userID).orElseThrow(() -> new UserNotFoundException("User not found exception"));
        if(!completed){
            user.getToDoList().clear();
            userService.saveUser(user); //orphan removal is set to true in the User model. So, this will result in removing all todo items from the user.
        } else {
            user.getToDoList().removeIf(list -> list.getCompleted());
            LOGGER.info("ToDo items remaining ---> {}", user.getToDoList());
            userService.saveUser(user);
        }
        return ResponseEntity.noContent().build();

    }

}
