package com.timetrackerapi.controller;

import com.timetrackerapi.model.Task;
import com.timetrackerapi.model.dto.TaskCreateDto;
import com.timetrackerapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/id")
    public Task getTaskById(@RequestParam long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public void createTask(@RequestBody TaskCreateDto taskCreateDto) {
        taskService.createTask(taskCreateDto);
    }

    @PutMapping("/name")
    public void updateTaskName(@RequestParam long id, @RequestBody String name) {
        taskService.updateTaskName(id, name);
    }

    @PutMapping("/description")
    public void updateTaskDescription(@RequestParam long id, @RequestBody String description) {
        taskService.updateTaskDescription(id, description);
    }

    @PutMapping("/add_employee")
    public void addEmployeeForTask(@RequestParam long taskId, @RequestParam long userId) {
        taskService.addEmployeeForTask(taskId, userId);
    }

    @PutMapping("/remove_employee")
    public void removeEmployeeFromTask(@RequestParam long taskId, @RequestParam long userId) {
        taskService.removeEmployeeFromTask(taskId, userId);
    }

    @DeleteMapping
    public void deleteTask(@RequestParam long id) {
        taskService.deleteTask(id);
    }
}
