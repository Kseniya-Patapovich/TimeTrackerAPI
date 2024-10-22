package com.timetrackerapi.controller;

import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.dto.UserCreateDto;
import com.timetrackerapi.service.TimeTrackerUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/id")
    public TimeTrackerUser getUserById(@RequestParam long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody UserCreateDto userCreateDto) {
        userService.createNewUser(userCreateDto);
    }

    @PutMapping
    public void updatePassword(@RequestBody String password) {
        userService.updatePassword(password);
    }

    @PutMapping("/add_task")
    public void addTaskForUser(@RequestParam long userId, @RequestParam long taskId) {
        userService.addTaskForUser(userId, taskId);
    }

    @PutMapping("/remove_task")
    public void removeTaskFromUser(@RequestParam long userId, @RequestParam long taskId) {
        userService.removeTaskFromUser(userId, taskId);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }
}
