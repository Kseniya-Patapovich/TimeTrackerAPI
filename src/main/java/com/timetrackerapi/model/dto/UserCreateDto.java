package com.timetrackerapi.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateDto {
    @Size(min = 6, max = 15)
    private String firstName;

    @Size(min = 6, max = 15)
    private String lastName;

    @NotNull
    private  String role;

    @Size(min = 6, max = 15)
    private String login;

    @Size(min = 8, max = 20)
    private String password;
}
