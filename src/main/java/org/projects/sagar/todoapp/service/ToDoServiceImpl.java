package org.projects.sagar.todoapp.service;

import org.projects.sagar.todoapp.model.ToDo;
import org.projects.sagar.todoapp.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoServiceImpl.class);
    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public Optional<ToDo> getTodo(int id, int userID) {
        return toDoRepository.findByIdAndUserId(id, userID);
    }

    @Override
    public ToDo addTodoItem(ToDo toDo) {
        return toDoRepository.save(toDo);
    }


    @Override
    public void deleteItem(ToDo toDoItem) {
        toDoRepository.deleteAll();
    }

    @Override
    public void deleteItems(List<Integer> ids) {
        LOGGER.info("Inside the Delete ToDo Service Impl. The IDs to be deleted are ---> {}", ids);
        toDoRepository.deleteAllById(ids);
    }
}
