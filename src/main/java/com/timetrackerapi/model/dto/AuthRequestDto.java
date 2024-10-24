package com.timetrackerapi.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthRequestDto {
    @Size(min = 6, max = 15)
    private String login;

    @Size(min = 8, max = 20)
    private String password;
}
