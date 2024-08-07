package com.max.quizspring.dto;

import lombok.Data;

@Data
public class JobRequestDto {
    private String title;
    private String description;
    private Long companyId; // The ID of the company to which the job belongs
}
