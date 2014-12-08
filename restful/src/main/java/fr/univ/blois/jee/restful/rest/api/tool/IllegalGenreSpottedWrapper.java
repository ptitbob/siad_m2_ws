package fr.univ.blois.jee.restful.rest.api.tool;

import fr.univ.blois.jee.restful.domain.IllegalGenreSpotted;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mappeur de l'excpetion IllegalGenreSpotted en erreur 472 -> genre non disponible dans la bibliothèque ou mal demandé par l'utilisateur
 */
@Provider
public class IllegalGenreSpottedWrapper implements ExceptionMapper<IllegalGenreSpotted> {
    @Override
    public Response toResponse(IllegalGenreSpotted exception) {
        return Response.status(472).entity(exception.getMessage()).type("text/plain").build();
    }
}
