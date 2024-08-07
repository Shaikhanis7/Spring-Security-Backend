package com.max.quizspring.service;

import com.max.quizspring.dto.JobRequestDto;
import com.max.quizspring.dto.JobResponseDto;
import com.max.quizspring.model.Company;
import com.max.quizspring.model.Job;
import com.max.quizspring.repo.CompanyRepository;
import com.max.quizspring.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobResponseDto createJob(JobRequestDto jobRequestDto) {
        Optional<Company> company = companyRepository.findById(jobRequestDto.getCompanyId());
        if (company.isEmpty()) {
            throw new RuntimeException("Company with ID " + jobRequestDto.getCompanyId() + " does not exist");
        }

        Job job = new Job();
        job.setTitle(jobRequestDto.getTitle());
        job.setDescription(jobRequestDto.getDescription());
        job.setCompany(company.get());

        Job savedJob = jobRepository.save(job);
        return mapToDto(savedJob);
    }

    public JobResponseDto getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return mapToDto(job);
    }

    public List<JobResponseDto> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public JobResponseDto updateJob(Long id, JobRequestDto jobRequestDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Optional<Company> company = companyRepository.findById(jobRequestDto.getCompanyId());
        if (company.isEmpty()) {
            throw new RuntimeException("Company with ID " + jobRequestDto.getCompanyId() + " does not exist");
        }

        job.setTitle(jobRequestDto.getTitle());
        job.setDescription(jobRequestDto.getDescription());
        job.setCompany(company.get());

        Job updatedJob = jobRepository.save(job);
        return mapToDto(updatedJob);
    }

    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        jobRepository.delete(job);
    }

    private JobResponseDto mapToDto(Job job) {
        JobResponseDto dto = new JobResponseDto();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setCompanyId(job.getCompany().getId()); // Include company ID in response
        return dto;
    }
}
