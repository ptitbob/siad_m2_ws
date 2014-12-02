package fr.univ.blois.jee.restful.rest.api;

import fr.univ.blois.jee.restful.domain.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by francois on 02/12/14.
 */
@Path("ping")
public class Ping {

    @GET
    public Response pong() {
        return Response.ok().entity("pong\n").build();
    }

    @GET
    @Path("re")
    public Response re() {
        return Response.ok().entity("re quoi ?").build();
    }

    @GET
    @Path("user")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUserTest() {
        return Response.ok().entity(new User(1, "user")).build();
    }

}
