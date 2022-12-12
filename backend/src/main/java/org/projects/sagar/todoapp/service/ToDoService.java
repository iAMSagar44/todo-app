package org.projects.sagar.todoapp.service;

import org.projects.sagar.todoapp.model.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoService {

    Optional<ToDo> getTodo(int id, int userID);
    ToDo addTodoItem(ToDo toDo);

    void deleteItem(ToDo toDoItem);

    void deleteItems(List<Integer> ids);
}
