package com.quarkus.service;

import com.quarkus.dto.CompanyDto;
import com.quarkus.dto.ManagerDto;
import com.quarkus.mapper.CompanyMapper;
import com.quarkus.mapper.ManagerMapper;
import com.quarkus.model.Company;
import com.quarkus.model.Manager;
import com.quarkus.repository.CompanyRepository;
import com.quarkus.repository.ManagerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class CompanyService {
    CompanyRepository companyRepository;
    ManagerRepository managerRepository;
    CompanyMapper companyMapper;
    ManagerMapper managerMapper;

    @Inject
    public CompanyService(CompanyRepository companyRepository,
                          ManagerRepository managerRepository,
                          CompanyMapper companyMapper,
                          ManagerMapper managerMapper) {
        this.companyRepository = companyRepository;
        this.managerRepository = managerRepository;
        this.companyMapper = companyMapper;
        this.managerMapper = managerMapper;
    }

    public Response getCompanyById(Long id) {
        Company company = companyRepository.findById(id);
        return Response.ok(companyMapper.toCompanyDto(company)).build();
    }

    public Response getManagerOfCompanyByCompanyId(Long id) {
        Company company = companyRepository.findById(id);
        Manager manager = company.getManager();
        ManagerDto managerDto = managerMapper.toManagerDto(manager);

        return Response.ok(managerDto).build();
    }


    @Transactional
    public Response createCompany(CompanyDto companyDto) {
//        if (companyDto == null || managerId == null) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity("Company name or manager id are not specified").build();
//        }
        Company company = companyMapper.toCompany(companyDto);
        companyRepository.persist(company);
        return Response.ok(companyDto).build();
    }

}
