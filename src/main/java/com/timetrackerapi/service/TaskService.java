package com.timetrackerapi.service;

import com.timetrackerapi.exception.TaskNotFoundException;
import com.timetrackerapi.model.Task;
import com.timetrackerapi.model.dto.TaskCreateDto;
import com.timetrackerapi.model.dto.TaskUpdateDto;
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
    public void updateTask(long id, TaskUpdateDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (dto.getName() != null) {
            task.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }
        taskRepository.save(task);
    }

    public void deleteTask(long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException(id);
        }
    }
}
