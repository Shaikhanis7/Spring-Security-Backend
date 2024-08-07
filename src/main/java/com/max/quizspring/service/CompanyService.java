package com.max.quizspring.service;

import com.max.quizspring.dto.CompanyRequestDto;
import com.max.quizspring.dto.CompanyResponseDto;
import com.max.quizspring.model.Company;
import com.max.quizspring.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        Company company = new Company();
        company.setName(companyRequestDto.getName());
        company.setContactEmail(companyRequestDto.getContactEmail());
        company.setContactPhone(companyRequestDto.getContactPhone());
        Company savedCompany = companyRepository.save(company);
        return mapToDto(savedCompany);
    }

    public CompanyResponseDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return mapToDto(company);
    }

    public List<CompanyResponseDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CompanyResponseDto updateCompany(Long id, CompanyRequestDto companyRequestDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        company.setName(companyRequestDto.getName());
        Company updatedCompany = companyRepository.save(company);
        return mapToDto(updatedCompany);
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        companyRepository.delete(company);
    }

    private CompanyResponseDto mapToDto(Company company) {
        CompanyResponseDto dto = new CompanyResponseDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        return dto;
    }
}
