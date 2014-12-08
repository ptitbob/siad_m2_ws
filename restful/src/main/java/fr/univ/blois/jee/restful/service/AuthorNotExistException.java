package fr.univ.blois.jee.restful.service;

/**
 * Created by frobert on 05/12/2014.
 */
public class AuthorNotExistException extends Exception {

  public static final String AUTHOR_NOT_EXIST = "L'auteur identifié par le N° %d n'est pas référencé dans notre bibliothèque";

  public AuthorNotExistException(String message) {
    super(message);
  }

  public AuthorNotExistException(int authorId) {
    this(String.format(AUTHOR_NOT_EXIST, authorId));
  }
}
