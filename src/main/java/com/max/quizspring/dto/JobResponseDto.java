package com.max.quizspring.dto;

import lombok.Data;

@Data
public class JobResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long companyId; // The ID of the company associated with the job
}
