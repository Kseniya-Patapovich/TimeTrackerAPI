package com.timetrackerapi.exception;

public class UserNotFoundByLoginException extends RuntimeException {
    public UserNotFoundByLoginException(String login) {
        super("User with login=" + login + " not found!");
    }
}
