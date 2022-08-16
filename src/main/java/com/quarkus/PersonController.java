package com.quarkus;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/person")
public class PersonController {

    @Inject
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public long count() {
        return personService.count();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Transactional
    public Response getPersonById(@PathParam("id") Long id) {
        return personService.getPersonById(id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAll() {
        return personService.getAll();
    }


    @POST
    @Path("/create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewPerson(Person person) {
        return personService.addNewPerson(person);
    }

    @DELETE
    @Path("/delete")
    @Transactional
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonById(@QueryParam("id") Long id) {
        return personService.deletePersonById(id);
    }

}
