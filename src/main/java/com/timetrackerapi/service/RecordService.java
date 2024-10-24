package com.timetrackerapi.service;

import com.timetrackerapi.exception.RecordNotFoundException;
import com.timetrackerapi.exception.TaskNotFoundException;
import com.timetrackerapi.exception.UserNotFoundByLoginException;
import com.timetrackerapi.model.Record;
import com.timetrackerapi.model.Task;
import com.timetrackerapi.model.TimeTrackerUser;
import com.timetrackerapi.model.dto.RecordCreateDto;
import com.timetrackerapi.repository.RecordRepository;
import com.timetrackerapi.repository.TaskRepository;
import com.timetrackerapi.repository.TimeTrackerUserRepository;
import com.timetrackerapi.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final TaskRepository taskRepository;
    private final TimeTrackerUserRepository userRepository;
    private final UserUtils userUtils;

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Record getRecordById(long id) {
        return recordRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void createRecord(RecordCreateDto recordCreateDto) {
        Record record = new Record();
        Task task = taskRepository.findById(recordCreateDto.getTaskId()).orElseThrow(() -> new TaskNotFoundException(recordCreateDto.getTaskId()));
        TimeTrackerUser user = userRepository.findByLogin(userUtils.getCurrentUser().getUsername()).orElseThrow(() -> new UserNotFoundByLoginException(userUtils.getCurrentUser().getUsername()));
        if (user.getTasks().contains(task)) {
            record.setUser(user);
            record.setTask(task);
            record.setSpent(recordCreateDto.getSpent());
            recordRepository.save(record);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with id=" + user.getId() + " doesn't exist task with id=" + recordCreateDto.getTaskId());
        }
    }

    @Transactional
    public void trackTime(long id, double time) {
        Record record = recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        TimeTrackerUser user = userRepository.findByLogin(userUtils.getCurrentUser().getUsername()).orElseThrow(() -> new UserNotFoundByLoginException(userUtils.getCurrentUser().getUsername()));
        if (user.getRecords().contains(record)) {
            record.setSpent(time);
            recordRepository.save(record);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Current user doesn't contain record with id=" + id);
        }
    }

    public void deleteRecord(long id) {
        if (recordRepository.existsById(id)) {
            recordRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException(id);
        }
    }
}
