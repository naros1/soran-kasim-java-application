package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository allTaskRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskRepository saveTaskRepository;
    @Autowired
    private TaskRepository deleteTaskRepositiory;

    public List<Task> getAllTasks(){
        return allTaskRepository.findAll();
    }

    public Optional<Task> getTaskById(final Long id){
        return taskRepository.findById(id);
    }

    public Task saveTask(final Task task){
        return saveTaskRepository.save(task);
    }

    public void deleteTaskById(final Long id){
        deleteTaskRepositiory.deleteById(id);
    }

}
