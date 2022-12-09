package org.projects.sagar.todoapp.repository;

import org.projects.sagar.todoapp.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    Optional<ToDo> findByIdAndUserId(int id, int userID);

}
