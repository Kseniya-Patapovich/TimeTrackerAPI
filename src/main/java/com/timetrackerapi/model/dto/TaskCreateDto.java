package com.timetrackerapi.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TaskCreateDto {
    @Size(min = 5, max = 15)
    private String name;

    @Size(min = 10)
    private String description;
}
