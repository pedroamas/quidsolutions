package com.example.quid.QUID.services;

import com.example.quid.QUID.domain.commons.TaskStatus;
import com.example.quid.QUID.domain.dtos.TaskDTO;
import com.example.quid.QUID.domain.entities.Task;
import com.example.quid.QUID.domain.mappers.TaskMapper;
import com.example.quid.QUID.domain.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskMapper taskMapper;
    public List<TaskDTO> getTasks(int userId){
        List<TaskDTO> tasks = taskRepository.findByUserId(userId).stream().map(taskMapper::taskToTaskDto).collect(Collectors.toList());
        return tasks;
    }

    public TaskDTO createTask(TaskDTO taskDTO){
        Task task = taskMapper.taskDtoToTask(taskDTO);
        task = taskRepository.save(task);
        return taskMapper.taskToTaskDto(task);
    }

    public void deleteTask(int taskId){
        Task task = taskRepository.findById(taskId).get();
        task.setDeleted(new Date());
        taskRepository.save(task);
    }
    public void completeTask(int taskId){
        Task task = taskRepository.findById(taskId).get();
        task.setStatus(TaskStatus.COMPLETADA);
        taskRepository.save(task);
    }

}
