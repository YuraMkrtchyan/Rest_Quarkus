package com.quarkus.controller;

import com.quarkus.model.Developer;
import com.quarkus.model.Manager;
import com.quarkus.model.Person;
import com.quarkus.service.PersonServiceImpl;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/person")
public class PersonController {
    PersonServiceImpl serviceImpl;

    @Inject
    public PersonController(PersonServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public PersonController() {
    }

    @GET
    @Path("/managers_count")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    public long managersCount() {
        return serviceImpl.managersCount();
    }

    @GET
    @Path("/developers_count")
    @Produces(MediaType.TEXT_PLAIN)
    public long developersCount() {
        return serviceImpl.developersCount();
    }

    @GET
    @Path("/manager_{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getManagerById(@PathParam("id") Long id) {
        return serviceImpl.getManagerById(id);
    }

    @GET
    @Path("/developer_{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getDeveloperById(@PathParam("id") Long id) {
        return serviceImpl.getDeveloperById(id);
    }


    @GET
    @Path("/all_managers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllManagers() {
        return serviceImpl.getAllManagers();
    }

    @GET
    @Path("/all_developers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllDevelopers() {
        return serviceImpl.getAllDevelopers();
    }


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewPerson(
            Person person,
            @QueryParam("salary") Double salary,
            @QueryParam("devLanguage") String devLanguage,
            @QueryParam("type") String type) {
        if (type.equals("developer")) {
            Developer developer = new Developer(person.getName(), person.getBirth(), person.getStatus());
            developer.setDevLanguage(devLanguage);
            serviceImpl.addNewPerson(developer);
            return Response.ok(developer).build();
        } else if (type.equals("manager")) {
            Manager manager = new Manager(person.getName(), person.getBirth(), person.getStatus());
            manager.setSalary(salary);
            serviceImpl.addNewPerson(manager);
            return Response.ok(manager).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).
                entity("Wrong type. Look at the description.").build();
    }


    @DELETE
    @Path("/delete_manager")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteManagerById(@QueryParam("id") Long id) {
        return serviceImpl.deleteManagerById(id);
    }

    @DELETE
    @Path("/delete_developer")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeveloperById(@QueryParam("id") Long id) {
        return serviceImpl.deleteDeveloperById(id);
    }

}
