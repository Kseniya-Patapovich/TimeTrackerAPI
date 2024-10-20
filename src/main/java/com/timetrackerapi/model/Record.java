package com.timetrackerapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double spent;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private TimeTrackerUser user;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
