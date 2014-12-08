package fr.univ.blois.jee.restful.rest.api;

import fr.univ.blois.jee.restful.domain.Author;
import fr.univ.blois.jee.restful.domain.User;
import fr.univ.blois.jee.restful.service.AuthorNotExistException;
import fr.univ.blois.jee.restful.service.AuthorUpdatingProcessAborted;
import fr.univ.blois.jee.restful.service.BookStoreService;
import fr.univ.blois.jee.restful.service.layer.DeleteItemNotExist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.Response.Status.CREATED;

/**
 * API Rest de gestion des auteurs
 */
@Path("author")
public class AuthorApi {

    /**
     * Service metier de la boutique de livres (injection de l'instance via CDI)
     */
    @Inject
    private BookStoreService bookStoreService;

    /**
     * Methode de recupération d'un auteur en utilisant un paramètre de requête GET
     * @param id id de l'auteur
     * @return Auteur
     * @throws AuthorNotExistException si l'auteur n'a pas été localisé. DOIT RENVOYER UNE ERREUR 580
     */
    @GET
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public Author getAuthorById(
        @QueryParam(value = "id") int id
    ) throws AuthorNotExistException {
        return bookStoreService.getAuthor(id);
    }

    /**
     * Methode de recupération d'un auteur indiqué dans l'URL (position finale)
     * @param id id de l'auteur
     * @return Auteur
     * @throws AuthorNotExistException si l'auteur n'a pas été localisé. DOIT RENVOYER UNE ERREUR 580
     */
    @GET
    @Path("{id}")
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public Author getAuthorByIdInPath(
            @PathParam("id") int id
    ) throws AuthorNotExistException {
        return bookStoreService.getAuthor(id);
    }

    /**
     * Renvoi la liste des auteurs
     * @return
     */
    @GET
    @Path("list")
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public List<User> getAuthorList() {
        return bookStoreService.getAuthorList();
    }

    /**
     * Vreation d'un auteur (sans livres associé)
     * @param name nom de l'auteur
     * @return 201 en cas de création (pour l'instant pas d'erreur de création à traiter)
     */
    @POST
    @Produces(TEXT_PLAIN)
    @Consumes(value = APPLICATION_FORM_URLENCODED)
    public Response createAuthor(
            @FormParam(value = "name") String name
    ) {
        Author author = bookStoreService.addAuthor(name);
        return Response.status(CREATED).entity(author.getId()).build();
    }

    /**
     * Modification de l'auteur. On concidère que le client maitrise la mise à jour de son côté, et comme nous ne réalisons pas d'opération en plus de la MàJ,
     * nous nous contentons de renvoyer un status Ok en cas de reussite
     * @param id id de l'auteur à modifier
     * @param name nom de l'auteur (donnée modifiable)
     * @return Response avec un code 200 (reponse vide)
     * @throws AuthorUpdatingProcessAborted en cas d'erreur de mise à jour
     */
    @PUT
    @Consumes(value = APPLICATION_FORM_URLENCODED)
    public Response updateAuthor(
            @FormParam("id") int id
            , @FormParam("name") String name
    ) throws AuthorUpdatingProcessAborted {
        bookStoreService.updateAuthor(id, name);
        return Response.ok().build();
    }

    /**
     * Effacement d'un auteur
     * @param id id de l'auteur à effacer
     * @return reponse avec un code 200 si aucune erreur n'a eu lieu
     */
    @DELETE
    @Consumes(value = APPLICATION_FORM_URLENCODED)
    public Response deleteAuthor(
            @QueryParam("id") int id
    ) {
        try {
            bookStoreService.deleteAuthor(id);
        } catch (DeleteItemNotExist deleteItemNotExist) {
            // Ne rien faire, une requete delete DOIT être idempotent - Ceci pour notre exemple...
        }
        return Response.ok().build();
    }

}
