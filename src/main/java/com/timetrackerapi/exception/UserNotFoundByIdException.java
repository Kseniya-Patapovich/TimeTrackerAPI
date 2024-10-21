package com.timetrackerapi.exception;

public class UserNotFoundByIdException extends RuntimeException{
    public UserNotFoundByIdException(long id) {
        super("User with id=" + id + " not found!");
    }
}
