package com.quarkus.controller;

import com.quarkus.dto.CompanyDto;
import com.quarkus.service.CompanyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/company")
public class CompanyController {
    CompanyService companyService;

    @Inject
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyById(@QueryParam("id") Long id) {
        return companyService.getCompanyById(id);
    }

    @GET
    @Path("/manager")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getManagerOfCompanyByCompanyId(@QueryParam("id") Long id) {
        return companyService.getManagerOfCompanyByCompanyId(id);
    }


    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCompany
            (CompanyDto companyDto,
             @QueryParam("managerId") Long managerId) {
        return companyService.createCompany(companyDto);
    }

}
