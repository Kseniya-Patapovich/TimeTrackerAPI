package com.timetrackerapi.controller;

import com.timetrackerapi.model.Record;
import com.timetrackerapi.model.dto.RecordCreateDto;
import com.timetrackerapi.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/id")
    public Record getRecordById(@RequestParam long id) {
        return recordService.getRecordById(id);
    }

    @PostMapping
    public void createRecord(@RequestBody RecordCreateDto dto) {
        recordService.createRecord(dto);
    }

    @PutMapping
    public void trackTime(@RequestParam long id, @RequestParam double time) {
        recordService.trackTime(id, time);
    }

    @DeleteMapping
    public void deleteRecord(@RequestParam long id) {
        recordService.deleteRecord(id);
    }
}
