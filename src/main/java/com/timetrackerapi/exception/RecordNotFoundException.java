package com.timetrackerapi.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(long id) {
        super("Record with id=" + id + " not found!");
    }
}
