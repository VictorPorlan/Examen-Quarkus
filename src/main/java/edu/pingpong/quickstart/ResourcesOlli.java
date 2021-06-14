package edu.pingpong.quickstart;

import javax.inject.Inject;
import javax.persistence.Table;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class ResourcesOlli {

    @Inject
    ServiceOlli service;

    @GET
    @Path("/wellcome")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Wellcome Ollivanders!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/usuaria/{nombre}")
    public Response getUsuaria(@PathParam("nombre") String nombre){
        Usuaria usuaria = service.cargaUsuaria(nombre);
        return usuaria.getNombre().equals("")?
                Response.status(Response.Status.NOT_FOUND).build():
                Response.status(Response.Status.OK).entity(usuaria).build();
    }
}