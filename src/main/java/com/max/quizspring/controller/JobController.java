package com.max.quizspring.controller;

import com.max.quizspring.dto.JobRequestDto;
import com.max.quizspring.dto.JobResponseDto;
import com.max.quizspring.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponseDto> createJob(@RequestBody JobRequestDto jobRequestDto) {
        JobResponseDto jobResponseDto = jobService.createJob(jobRequestDto);
        return new ResponseEntity<>(jobResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJob(@PathVariable Long id) {
        JobResponseDto jobResponseDto = jobService.getJobById(id);
        return new ResponseEntity<>(jobResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDto>> getAllJobs() {
        List<JobResponseDto> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDto> updateJob(@PathVariable Long id, @RequestBody JobRequestDto jobRequestDto) {
        JobResponseDto jobResponseDto = jobService.updateJob(id, jobRequestDto);
        return new ResponseEntity<>(jobResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
