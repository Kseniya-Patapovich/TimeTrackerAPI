package com.timetrackerapi.service;

import com.timetrackerapi.exception.UserNotFoundByIdException;
import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.dto.UserCreateDto;
import com.timetrackerapi.model.enums.Role;
import com.timetrackerapi.repository.TimeTrackerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTrackerUserService {
    private final TimeTrackerUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<TimeTrackerUser> getAllUsers() {
        return userRepository.findAll();
    }

    public TimeTrackerUser getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
    }

    @Transactional
    public void createNewUser(UserCreateDto userCreateDto){
        Role role = Role.getByName(userCreateDto.getRole());
        TimeTrackerUser newUser = new TimeTrackerUser();
        newUser.setFirstName(userCreateDto.getFirstName());
        newUser.setLastName(userCreateDto.getLastName());
        newUser.setLogin(userCreateDto.getLogin());
        newUser.setPassword(userCreateDto.getPassword());
        newUser.setRole(role);
        newUser.setLocked(false);
        userRepository.save(newUser);
    }
}
