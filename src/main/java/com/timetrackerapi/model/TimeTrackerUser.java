package com.timetrackerapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class TimeTrackerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 6, max = 15)
    private String firstName;

    @Column
    @Size(min = 6, max = 15)
    private String lastName;

    @Column
    @Email
    private String email;

    @Column
    @Size(min = 8, max = 20)
    private String password;
}
