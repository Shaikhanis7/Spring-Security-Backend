package com.max.quizspring.dto;

import lombok.Data;

@Data
public class JobRequestDto {
    private String title;
    private String description;
    private String location;  // Job location
    private Double salary;    // Job salary
    private String jobType;   // New attribute for job type
    private Long companyId; 
}
