package fr.univ.blois.jee.restful.rest.api.tool;

import fr.univ.blois.jee.restful.service.ItemNotExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper d'exception concernant la non existance d'un auteur dans la base de donnée.
 *
 * A remarquer, nous changeons ici le type (content-type) renvoyé : L'erreur est un texte a afficher dans l'IHM du client (par exemple)
 */
@Provider
public class ItemNotExistExceptionWrapper implements ExceptionMapper<ItemNotExistException> {
    @Override
    public Response toResponse(ItemNotExistException exception) {
        return Response.status(580).entity(exception.getMessage()).type("text/plain").build();
    }
}
