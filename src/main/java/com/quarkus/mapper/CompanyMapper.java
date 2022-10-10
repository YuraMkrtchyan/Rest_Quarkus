package com.quarkus.mapper;

import com.quarkus.dto.CompanyDto;
import com.quarkus.model.Company;
import com.quarkus.repository.ManagerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CompanyMapper {
    ManagerRepository managerRepository;

    @Inject
    public CompanyMapper(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Company toCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setManager(managerRepository.findById(companyDto.getManagerId()));
        return company;
    }

    public CompanyDto toCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setManagerId(company.getManager().getId());
        return companyDto;
    }

}
