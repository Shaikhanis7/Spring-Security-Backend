package com.max.quizspring.service;

import com.max.quizspring.dto.ApplicationRequestDto;
import com.max.quizspring.dto.ApplicationResponseDto;
import com.max.quizspring.model.Application;
import com.max.quizspring.model.Job;
import com.max.quizspring.model.User;
import com.max.quizspring.repo.ApplicationRepository;
import com.max.quizspring.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    // Method to get the current logged-in user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        throw new RuntimeException("User not found");
    }

    public ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto) {
        Optional<Job> job = jobRepository.findById(applicationRequestDto.getJobId());

        if (job.isEmpty()) {
            throw new RuntimeException("Job with ID " + applicationRequestDto.getJobId() + " does not exist");
        }

        Application application = new Application();
        application.setStatus(applicationRequestDto.getStatus());
        application.setJob(job.get());
        application.setUser(getCurrentUser()); // Set the current user

        Application savedApplication = applicationRepository.save(application);
        return mapToDto(savedApplication);
    }

    public ApplicationResponseDto getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return mapToDto(application);
    }

    public List<ApplicationResponseDto> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ApplicationResponseDto updateApplication(Long id, ApplicationRequestDto applicationRequestDto) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Optional<Job> job = jobRepository.findById(applicationRequestDto.getJobId());

        if (job.isEmpty()) {
            throw new RuntimeException("Job with ID " + applicationRequestDto.getJobId() + " does not exist");
        }

        application.setStatus(applicationRequestDto.getStatus());
        application.setJob(job.get());
        application.setUser(getCurrentUser()); // Set the current user

        Application updatedApplication = applicationRepository.save(application);
        return mapToDto(updatedApplication);
    }

    public void deleteApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        applicationRepository.delete(application);
    }

    private ApplicationResponseDto mapToDto(Application application) {
        ApplicationResponseDto dto = new ApplicationResponseDto();
        dto.setId(application.getId());
        dto.setStatus(application.getStatus());
        dto.setJobId(application.getJob().getId());
        return dto;
    }
}
