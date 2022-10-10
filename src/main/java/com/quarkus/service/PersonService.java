package com.quarkus.service;

import com.quarkus.model.Person;

import javax.ws.rs.core.Response;

public interface PersonService {
    long managersCount();

    long developersCount();

    Response getManagerById(Long id);

    Response getDeveloperById(Long id);

    Response getAllManagers();

    Response getAllDevelopers();

    <T extends Person> Response addNewPerson(T t);

    Response deleteManagerById(Long id);

    Response deleteDeveloperById(Long id);
}
