package fr.univ.blois.jee.restful.service;

/**
 * Created by frobert on 05/12/2014.
 */
public class AuthorNotExistException extends Exception {
  public AuthorNotExistException(String message) {
    super(message);
  }
}
