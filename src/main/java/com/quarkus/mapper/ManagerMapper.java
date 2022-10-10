package com.quarkus.mapper;

import com.quarkus.dto.ManagerDto;
import com.quarkus.model.Manager;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ManagerMapper {
    public Manager toManager(ManagerDto managerDto) {
        Manager manager = new Manager();
        manager.setSalary(managerDto.getSalary());
        return manager;
    }

    public ManagerDto toManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setSalary(manager.getSalary());
        return managerDto;
    }

}
