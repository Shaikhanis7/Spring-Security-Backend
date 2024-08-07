package com.max.quizspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.max.quizspring.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
