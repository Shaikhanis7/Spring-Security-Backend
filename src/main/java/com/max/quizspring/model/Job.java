// package com.max.quizspring.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.util.List;

// @Data
// @Builder
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Table(name = "job")
// public class Job {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String title;
//     private String description;
//     private String location;  // Job location
//     private Double salary;    // Job salary
//     private String jobType;   // New attribute for job type

//     @ManyToOne
//     @JoinColumn(name = "company_id")
//     private Company company;

//     @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<Application> applications;
// }




package com.max.quizspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;  // Job location
    private Double salary;    // Job salary
    private String jobType;   // New attribute for job type

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;
}
