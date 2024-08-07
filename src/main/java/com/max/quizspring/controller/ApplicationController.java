package com.max.quizspring.controller;

import com.max.quizspring.dto.ApplicationRequestDto;
import com.max.quizspring.dto.ApplicationResponseDto;
import com.max.quizspring.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<ApplicationResponseDto> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponseDto getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping
    public ApplicationResponseDto createApplication(@RequestBody ApplicationRequestDto applicationRequestDto) {
        return applicationService.createApplication(applicationRequestDto);
    }

    @PutMapping("/{id}")
    public ApplicationResponseDto updateApplication(@PathVariable Long id, @RequestBody ApplicationRequestDto applicationRequestDto) {
        return applicationService.updateApplication(id, applicationRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }
}
