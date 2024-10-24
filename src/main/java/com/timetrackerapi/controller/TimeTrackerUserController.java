package com.timetrackerapi.controller;

import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.dto.UserCreateDto;
import com.timetrackerapi.service.TimeTrackerUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TimeTrackerUserController {
    private final TimeTrackerUserService userService;

    @GetMapping
    public List<TimeTrackerUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public TimeTrackerUser getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.createNewUser(userCreateDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@RequestBody String password) {
        userService.updatePassword(password);
    }

    @PutMapping("/{userId}/tasks/{taskId}/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addTaskForUser(@PathVariable long userId, @PathVariable long taskId) {
        userService.addTaskForUser(userId, taskId);
    }

    @PutMapping("/{userId}/tasks/{taskId}/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTaskFromUser(@PathVariable long userId, @PathVariable long taskId) {
        userService.removeTaskFromUser(userId, taskId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
