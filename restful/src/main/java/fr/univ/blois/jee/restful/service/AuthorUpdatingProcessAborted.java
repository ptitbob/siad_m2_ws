package fr.univ.blois.jee.restful.service;

import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by francois on 08/12/14.
 */
public class AuthorUpdatingProcessAborted extends Exception {

    public static final String AUTHOR_UPDATING_PROCESS_ERROR = "La mise à jour de l'auteur a échouée : %s";

    public AuthorUpdatingProcessAborted(int id) {
        super(String.format(AUTHOR_UPDATING_PROCESS_ERROR, String.format(AuthorNotExistException.AUTHOR_NOT_EXIST, id)));
    }
}
