package com.timetrackerapi.service;

import com.timetrackerapi.exception.TaskNotFoundException;
import com.timetrackerapi.exception.UserNotFoundByIdException;
import com.timetrackerapi.model.Task;
import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.dto.TaskCreateDto;
import com.timetrackerapi.repository.TaskRepository;
import com.timetrackerapi.repository.TimeTrackerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TimeTrackerUserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public void createTask(TaskCreateDto taskCreateDto) {
        Task newTask = new Task();
        newTask.setName(taskCreateDto.getName());
        newTask.setDescription(taskCreateDto.getDescription());
        taskRepository.save(newTask);
    }

    @Transactional
    public void updateTaskName(long id, String name) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setName(name);
        taskRepository.save(task);
    }

    @Transactional
    public void updateTaskDescription(long id, String description) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setDescription(description);
        taskRepository.save(task);
    }

    @Transactional
    public void addEmployeeForTask(long taskId, long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        TimeTrackerUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
        task.getEmployees().add(user);
        taskRepository.save(task);
    }

    @Transactional
    public void removeEmployeeFromTask(long taskId, long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (userRepository.existsById(userId)) {
            task.getEmployees().remove(userId);
        } else {
            throw new UserNotFoundByIdException(userId);
        }
    }

    public void deleteTask(long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException(id);
        }
    }
}
