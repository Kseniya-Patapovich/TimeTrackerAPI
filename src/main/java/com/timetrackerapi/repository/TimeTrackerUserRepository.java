package com.timetrackerapi.repository;

import com.timetrackerapi.model.TimeTrackerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeTrackerUserRepository extends JpaRepository<TimeTrackerUser, Long> {
    Optional<TimeTrackerUser> findByLogin(String login);
}
