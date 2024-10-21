package com.timetrackerapi.security;

import com.timetrackerapi.exception.UserNotFoundException;
import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.repository.TimeTrackerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final TimeTrackerUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TimeTrackerUser user = userRepository.findByLogin(username).orElseThrow(() -> new UserNotFoundException(username));
        return new TimeTrackerUserDetails(user);
    }
}
