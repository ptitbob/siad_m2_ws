package fr.univ.blois.jee.restful.rest.api;

import fr.univ.blois.jee.restful.domain.Book;
import fr.univ.blois.jee.restful.domain.BookInformation;
import fr.univ.blois.jee.restful.domain.Genre;
import fr.univ.blois.jee.restful.domain.IllegalGenreSpotted;
import fr.univ.blois.jee.restful.service.ItemNotExistException;
import fr.univ.blois.jee.restful.service.BookStoreService;
import fr.univ.blois.jee.restful.service.layer.DeleteItemNotExist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

/**
 *
 */
@Path("book")
public class BookApi {

    /**
     * Service metier de la boutique de livres (injection de l'instance via CDI)
     * Ceci est possible grace au fichier beans.xml situé dans le WEB-INF de la webapp et qui a pour configuration d'exploration "all"
     */
    @Inject
    private BookStoreService bookStoreService;

    /**
     * Renvoi les détails d'un livre en utilisant l'identifiant passé en paramètre de la requête
     * @param id identifiant du livre
     * @return Livre serialisé
     */
    @GET
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public Book getBook(
            @QueryParam("id") int id
    ) {
        return bookStoreService.getBook(id);
    }

    /**
     * Renvoi les détails d'un livre en utilisant l'identifiant faisant parti de l'URI d'acces
     * @param id identifiant du livre (partie de l'URI)
     * @return Livre serialisé
     */
    @GET
    @Path("{id}")
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public Book getBookByIdInPath(
            @PathParam("id") int id
    ) {
        return bookStoreService.getBook(id);
    }

    /**
     * Renvoi la liste des livres
     * @return list de livre
     */
    @GET
    @Path("list")
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public List<BookInformation> getBookList() {
        return bookStoreService.getBookList();
    }

    /**
     * Création d'un livrez
     * @param title titre du livre
     * @param genreAsString genre du livre (sous forme de chaine)
     * @param authorId identifiant de l'auteur
     * @return Response ok contenant l'identifiant du livre
     * @throws IllegalGenreSpotted si le genre demandé n'exista pas
     * @throws ItemNotExistException si l'auteur n'a pas pu être localié dans la couche de persistance.
     */
    @POST
    @Produces(TEXT_PLAIN)
    @Consumes(value = APPLICATION_FORM_URLENCODED)
    public Response createBook(
        @FormParam("title") String title
        , @FormParam("genre") String genreAsString
        , @FormParam("authorId") int authorId
    ) throws IllegalGenreSpotted, ItemNotExistException {
        Genre genre = getGenreFromString(genreAsString);
        Book book = bookStoreService.addBook(title, genre, authorId);
        return Response.ok().entity(String.valueOf(book.getId())).build();
    }

    private Genre getGenreFromString(String genreAsString) throws IllegalGenreSpotted {
        try {
            Genre genre = Genre.valueOf(genreAsString.toUpperCase());
            return genre;
        } catch (IllegalArgumentException exception) {
            throw new IllegalGenreSpotted(genreAsString);
        }
    }

    /**
     * Modification d'un livre
     * @param id identifiant du livre a modifier
     * @param title titre du livre
     * @param genreAsString genre du livre (sous forme de chaine)
     * @return Response Ok si la mise à jour s'est déroulé sans problème
     * @throws IllegalGenreSpotted si le genre n'a pas été trouvé
     * @throws ItemNotExistException Si le livre ou l'auteur n'existe pas.
     */
    @PUT
    @Consumes(value = APPLICATION_FORM_URLENCODED)
    public Response updateBook(
            @FormParam("id") int id
            , @FormParam("title") String title
            , @FormParam("genre") String genreAsString
    ) throws IllegalGenreSpotted, ItemNotExistException {
        Genre genre = getGenreFromString(genreAsString);
        bookStoreService.updateBook(id, title, genre);
        return Response.ok().build();
    }

    /**
     * Effcement d'un livre
     * @param id identifiant du livre a effacé
     * @return Response Ok (dans tous les cas (ou presque, mais pas ici) - cf. regle de reponse d'un acces via verbe Delete)
     */
    @DELETE
    public Response deleteBook(
            @QueryParam("id") int id
    ) {
        try {
            bookStoreService.deleteBook(id);
        } catch (DeleteItemNotExist deleteItemNotExist) {
            // Ne rien faire car la methode DELETE d'un service REST doit être idempotent... ;)
        }
        return Response.ok().build();
    }
}
