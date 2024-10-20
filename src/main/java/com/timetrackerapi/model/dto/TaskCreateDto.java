package com.timetrackerapi.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TaskCreateDto {
    @Size(min = 5, max = 15)
    private String name;

    private List<Long> usersId;
}
