package com.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    @GET
    @Produces
    public long count() {
        return personRepository.count();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Transactional
    public Response getPersonById(@PathParam("id") Long id) {
        var successful = false;
        Optional<Person> person = personRepository.findByIdOptional(id);
        if (person.isPresent()) {
            successful = true;
        }
        return successful ? Response.ok(person.get()).build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAll() {
        List<Person> all = personRepository.findAll().list();
        return Response.ok(all).build();
    }


    @POST
    @Path("/create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewPerson(Person person) {
        personRepository.persistAndFlush(person);
        return Response.ok(person).build();
    }

    @DELETE
    @Path("/delete")
    @Transactional
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonById(@QueryParam("id") Long id) {
        boolean successful = false;
        Optional<Person> person = personRepository.findByIdOptional(id);
        if (person.isPresent()) {
            successful = personRepository.deleteById(id);
        }
        return successful ? Response.ok(person.get()).build() : Response.status(Response.Status.BAD_REQUEST).build();
    }


}
