package com.example.quid.QUID.controllers;

import com.example.quid.QUID.domain.dtos.TaskDTO;
import com.example.quid.QUID.domain.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    TaskController taskController;
    @Autowired
    UserController userController;
    @GetMapping
    public String index(){
        return "redirect:/login";
    }

    @GetMapping("tasks/{id}")
    public String listTasks(@PathVariable int id, Model model)   {
        model.addAttribute("message", "¡Hola Mundo desde Spring Boot!");
        List<TaskDTO> tasks = taskController.list(id).getBody();
        model.addAttribute("tasks", tasks);
        model.addAttribute("userId", id);
        return "tasks";
    }

    @GetMapping("newtask/{userId}")
    public String newTask(@PathVariable int userId, Model model)   {
        model.addAttribute("taskDto", new TaskDTO());
        model.addAttribute("userId", userId);
        return "newtask";
    }
    @PostMapping("newtask/{userId}")
    public String newTaskSend(@PathVariable int userId, Model model,TaskDTO taskDTO)   {
        taskController.createTask(userId,taskDTO);
        model.addAttribute("taskDto", new TaskDTO());
        return "redirect:/tasks/"+userId;
    }
    @GetMapping("tasks/{userId}/task/{taskId}")
    public String deleteTask(@PathVariable int taskId,@PathVariable int userId, Model model)   {
        taskController.deleteTask(taskId);
        return "redirect:/tasks/"+userId;
    }
    @GetMapping("tasks/{userId}/completetask/{taskId}")
    public String completeTask(@PathVariable int taskId,@PathVariable int userId, Model model)   {
        taskController.completeTask(taskId);
        return "redirect:/tasks/"+userId;
    }

    @GetMapping("login")
    public String login( Model model)   {
        model.addAttribute("userDto", new UserDTO());
        return "login";
    }
    @PostMapping("login")
    public String validarLogin( Model model,UserDTO userDTO) {
        userDTO = userController.loginUser(userDTO).getBody();
        if(userDTO == null){
            model.addAttribute("errorMessage", "El usuario o la contraseña no existen");
            return login(model);
        }
        return "redirect:/tasks/"+userDTO.getId();
    }

    @GetMapping("registrarse")
    public String registrarse( Model model)   {
        model.addAttribute("userDto", new UserDTO());
        return "registrarse";
    }
    @PostMapping("registrarse")
    public String validarRegistro(Model model,UserDTO userDTO ) {
        try {
            userController.createUser(userDTO);
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return registrarse(model);
        }
        return "redirect:/login";

    }
}
