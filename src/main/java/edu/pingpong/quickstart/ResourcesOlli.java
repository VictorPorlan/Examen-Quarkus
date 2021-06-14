package edu.pingpong.quickstart;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

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
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/ordena")
    public Response postOrden(Orden orden){
        Orden ordenCheck = service.comanda(orden.getUser().nombre, orden.getItem().nombre);
        return Optional.ofNullable(ordenCheck).isPresent()?
                Response.status(Response.Status.CREATED).entity(ordenCheck).build():
                Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/pedidos/{usuaria}")
    public List<Orden> getPedidosUsuaria(@PathParam("usuaria")String usuaria){
        return service.cargaOrden(usuaria);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/item/{nombre}")
    public Response getItem(@PathParam("nombre") String nombre){
        Item item = service.cargaItem(nombre);
        return item.getNombre().equals("")?
                Response.status(Response.Status.NOT_FOUND).build():
                Response.status(Response.Status.OK).entity(item).build();
    }
}