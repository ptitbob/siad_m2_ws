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

    @Inject
    private BookStoreService bookStoreService;

    @GET
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public Book getBook(
            @QueryParam("id") int id
    ) {
        return bookStoreService.getBook(id);
    }

    @GET
    @Path("{id}")
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public Book getBookByIdInPath(
            @PathParam("id") int id
    ) {
        return bookStoreService.getBook(id);
    }

    @GET
    @Path("list")
    @Produces(value = {APPLICATION_XML, APPLICATION_JSON})
    public List<BookInformation> getBookList() {
        return bookStoreService.getBookList();
    }

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
