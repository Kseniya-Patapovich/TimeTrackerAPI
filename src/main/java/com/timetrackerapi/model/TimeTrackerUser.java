package com.timetrackerapi.model;

import com.timetrackerapi.model.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TimeTrackerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 6, max = 15)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 6, max = 15)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Size(min = 6, max = 15)
    private String login;

    @Column(nullable = false)
    @Size(min = 8, max = 20)
    private String password;

    @Column(name = "user_role", nullable = false)
    private Role role;

    @ManyToMany(mappedBy = "employees", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Task> tasks;
}
