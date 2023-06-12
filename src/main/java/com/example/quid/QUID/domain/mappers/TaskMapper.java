package com.example.quid.QUID.domain.mappers;
import com.example.quid.QUID.domain.dtos.TaskDTO;
import com.example.quid.QUID.domain.dtos.UserDTO;
import com.example.quid.QUID.domain.entities.Task;
import com.example.quid.QUID.domain.entities.User;
import com.example.quid.QUID.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("taskMapper")
public class TaskMapper {
    @Autowired
    UserRepository userRepository;
    public TaskDTO taskToTaskDto(Task task){
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());

        taskDTO.setStatus(task.getStatus());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUserId(task.getUser().getId());
        return taskDTO;
    };

    public Task taskDtoToTask(TaskDTO taskDTO){
        Task task=new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        if(taskDTO.getStatus()!=null)
            task.setStatus(taskDTO.getStatus());
        task.setCreatedAt(taskDTO.getCreatedAt());
        User user =userRepository.findById(taskDTO.getUserId()).get();
        task.setUser(user);
        return task;
    };
}
