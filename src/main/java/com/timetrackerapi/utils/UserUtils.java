package com.timetrackerapi.utils;

import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.repository.TimeTrackerUserRepository;
import com.timetrackerapi.security.TimeTrackerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final TimeTrackerUserRepository userRepository;

    public TimeTrackerUserDetails getCurrentUser() {
        return (TimeTrackerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
