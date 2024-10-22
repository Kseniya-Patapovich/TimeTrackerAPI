package com.timetrackerapi.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecordCreateDto {
    @NotNull
    @Min(1)
    @Max(720)
    private Double spent;

    @NotNull
    private Long taskId;
}
