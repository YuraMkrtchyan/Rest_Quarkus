package com.quarkus.repository;

import com.quarkus.model.Developer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeveloperRepository implements PersonRepository<Developer> {

}
