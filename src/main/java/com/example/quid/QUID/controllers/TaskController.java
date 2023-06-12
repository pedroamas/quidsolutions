package com.example.quid.QUID.controllers;

import com.example.quid.QUID.domain.dtos.TaskDTO;
import com.example.quid.QUID.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskDTO>> list(@PathVariable int userId){
        List<TaskDTO> tasks= taskService.getTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TaskDTO> createTask(
            @PathVariable int userId,
            @RequestBody TaskDTO taskDTO
            ){
        taskDTO.setUserId(userId);
        TaskDTO task= taskService.createTask(taskDTO);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable int taskId){
          taskService.deleteTask(taskId);
        return ResponseEntity.ok("Deleted successfully");
    }
    @PatchMapping("/{taskId}")
    public ResponseEntity<String> completeTask(@PathVariable int taskId){
        taskService.completeTask(taskId);
        return ResponseEntity.ok("Deleted successfully");
    }
}
