package com.timetrackerapi.model.enums;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.server.ResponseStatusException;

public enum Role implements GrantedAuthority {
    ADMIN, USER;

    public static Role getByName(String name) {
        try {
            return Role.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Unexpected role value: " + name);
        }
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
