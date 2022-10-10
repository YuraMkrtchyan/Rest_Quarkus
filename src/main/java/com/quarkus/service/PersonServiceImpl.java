package com.quarkus.service;

import com.quarkus.dto.ManagerDto;
import com.quarkus.mapper.ManagerMapper;
import com.quarkus.model.Developer;
import com.quarkus.model.Manager;
import com.quarkus.model.Person;
import com.quarkus.repository.DeveloperRepository;
import com.quarkus.repository.ManagerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class PersonServiceImpl implements PersonService {
    ManagerRepository managerRepo;
    DeveloperRepository developerRepo;
    ManagerMapper managerMapper;

    @Inject
    public PersonServiceImpl(ManagerRepository managerRepo,
                             DeveloperRepository developerRepo,
                             ManagerMapper managerMapper) {
        this.managerRepo = managerRepo;
        this.developerRepo = developerRepo;
        this.managerMapper = managerMapper;
    }

    @Override
    public long managersCount() {
        return managerRepo.count();
    }

    @Override
    public long developersCount() {
        return developerRepo.count();
    }

    @Override
    public Response getManagerById(@PathParam("id") Long id) {
        var successful = false;
        var manager = managerRepo.findByIdOptional(id);
        if (manager.isPresent()) {
            successful = true;
        }

        return successful ? Response.ok(managerMapper.toManagerDto(manager.get())).build()
                : Response.status(Response.Status.BAD_REQUEST)
                .entity("No manager with such Id").build();
    }

    @Override
    public Response getDeveloperById(@PathParam("id") Long id) {
        var successful = false;
        var developer = developerRepo.findByIdOptional(id);
        if (developer.isPresent()) {
            successful = true;
        }
        return successful ? Response.ok(developer.get()).build() : Response.status(Response.Status.BAD_REQUEST)
                .entity("No developer with such Id").build();
    }

    @Override
    public Response getAllManagers() {
        var managers = managerRepo.findAll().list();
        return Response.ok(managers).build();
    }

    @Override
    public Response getAllDevelopers() {
        var developers = developerRepo.findAll().list();
        return Response.ok(developers).build();
    }

    @Transactional
    @Override
    public <T extends Person> Response addNewPerson(T person) {

        if (person instanceof Manager) {
            Manager manager = (Manager) person;
            managerRepo.persistAndFlush(manager);
            return Response.ok(manager).build();
        } else if (person instanceof Developer) {
            Developer dev = (Developer) person;
            developerRepo.persistAndFlush(dev);
            return Response.ok(dev).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity("cast problems").build();
    }


    @Transactional
    @Override
    public Response deleteManagerById(@QueryParam("id") Long id) {
        var successful = false;
        var manager = managerRepo.findByIdOptional(id);
        if (manager.isPresent()) {
            successful = managerRepo.deleteById(id);
        }
        return successful ? Response.ok(manager.get()).build() : Response.status(Response.Status.BAD_REQUEST)
                .entity("No manager with such Id").build();
    }

    @Override
    public Response deleteDeveloperById(Long id) {
        var successful = false;
        var developer = developerRepo.findByIdOptional(id);
        if (developer.isPresent()) {
            successful = managerRepo.deleteById(id);
        }
        return successful ? Response.ok(developer.get()).build() : Response.status(Response.Status.BAD_REQUEST)
                .entity("No developer with such Id").build();
    }
}
