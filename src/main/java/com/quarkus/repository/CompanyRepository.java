package com.quarkus.repository;

import com.quarkus.model.Company;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyRepository implements PanacheRepository<Company> {
}
