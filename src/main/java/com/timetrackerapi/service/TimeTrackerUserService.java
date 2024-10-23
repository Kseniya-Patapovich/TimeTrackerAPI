package com.timetrackerapi.service;

import com.timetrackerapi.exception.TaskNotFoundException;
import com.timetrackerapi.exception.UserNotFoundByIdException;
import com.timetrackerapi.exception.UserNotFoundByLoginException;
import com.timetrackerapi.model.Task;
import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.dto.UserCreateDto;
import com.timetrackerapi.model.enums.Role;
import com.timetrackerapi.repository.TaskRepository;
import com.timetrackerapi.repository.TimeTrackerUserRepository;
import com.timetrackerapi.security.TimeTrackerUserDetails;
import com.timetrackerapi.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTrackerUserService {
    private final TimeTrackerUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtils userUtils;
    private final TaskRepository taskRepository;

    public List<TimeTrackerUser> getAllUsers() {
        return userRepository.findAll();
    }

    public TimeTrackerUser getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
    }

    @Transactional
    public void createNewUser(UserCreateDto userCreateDto) {
        Role role = Role.getByName(userCreateDto.getRole());
        TimeTrackerUser newUser = new TimeTrackerUser();
        newUser.setFirstName(userCreateDto.getFirstName());
        newUser.setLastName(userCreateDto.getLastName());
        newUser.setLogin(userCreateDto.getLogin());
        newUser.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        newUser.setRole(role);
        newUser.setLocked(false);
        userRepository.save(newUser);
    }

    @Transactional
    public void updatePassword(String password) {
        TimeTrackerUserDetails userDetails = userUtils.getCurrentUser();
        TimeTrackerUser user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundByLoginException(userDetails.getUsername()));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Transactional
    public void addTaskForUser(long userId, long taskId) {
        TimeTrackerUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (!user.getTasks().contains(task)) {
            user.getTasks().add(task);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with id=" + userId + " already contains task with id=" + taskId);
        }
    }

    @Transactional
    public void removeTaskFromUser(long userId, long taskId) {
        TimeTrackerUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (user.getTasks().contains(task)) {
            user.getTasks().remove(task);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id=" + userId + " not found task with id=" + taskId);
        }
    }

    public void deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundByIdException(id);
        }
    }
}
