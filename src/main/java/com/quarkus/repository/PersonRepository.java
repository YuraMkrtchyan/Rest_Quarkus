package com.quarkus.repository;

import com.quarkus.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface PersonRepository<T extends Person> extends PanacheRepository<T> {

}