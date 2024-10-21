package com.timetrackerapi.security;

import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.enums.Role;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class TimeTrackerUserDetails extends User {
    private final long id;

    private final Role role;

    public TimeTrackerUserDetails(TimeTrackerUser user) {
        super(user.getLogin(), user.getPassword(), !user.getLocked(), true, true, true, List.of(user.getRole()));
        this.id = user.getId();
        this.role = user.getRole();
    }
}
