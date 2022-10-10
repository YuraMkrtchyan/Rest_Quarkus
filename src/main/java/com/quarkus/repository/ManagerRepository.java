package com.quarkus.repository;

import com.quarkus.model.Manager;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ManagerRepository implements PersonRepository<Manager> {
}
