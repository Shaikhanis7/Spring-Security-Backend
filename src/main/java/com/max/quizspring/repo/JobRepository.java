package com.max.quizspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.max.quizspring.model.Job;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyId(Long companyId);
}
