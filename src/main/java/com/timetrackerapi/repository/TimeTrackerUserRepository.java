package com.timetrackerapi.repository;

import com.timetrackerapi.model.TimeTrackerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTrackerUserRepository extends JpaRepository<TimeTrackerUser, Long> {
}
