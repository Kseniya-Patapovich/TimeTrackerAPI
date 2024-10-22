package com.timetrackerapi.service;

import com.timetrackerapi.exception.UserNotFoundByIdException;
import com.timetrackerapi.exception.UserNotFoundByLoginException;
import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.dto.UserCreateDto;
import com.timetrackerapi.model.enums.Role;
import com.timetrackerapi.repository.TimeTrackerUserRepository;
import com.timetrackerapi.security.TimeTrackerUserDetails;
import com.timetrackerapi.utils.UserUtils;
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
    private final UserUtils userUtils;

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
        newUser.setPassword(userCreateDto.getPassword());
        newUser.setRole(role);
        newUser.setLocked(false);
        userRepository.save(newUser);
    }

    @Transactional
    public void updatePassword(String password) {
        TimeTrackerUserDetails userDetails = userUtils.getCurrentUser();
        TimeTrackerUser user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundByLoginException(userDetails.getUsername()));
        user.setPassword(password);
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        TimeTrackerUser user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
        userRepository.delete(user);
    }
}
