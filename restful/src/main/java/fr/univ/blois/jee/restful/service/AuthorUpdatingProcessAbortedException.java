package fr.univ.blois.jee.restful.service;

/**
 * Created by francois on 08/12/14.
 */
public class AuthorUpdatingProcessAbortedException extends Exception {

    public static final String AUTHOR_UPDATING_PROCESS_ERROR = "La mise à jour de l'auteur a échouée : %s";

    public AuthorUpdatingProcessAbortedException(int id) {
        super(String.format(AUTHOR_UPDATING_PROCESS_ERROR, id));
    }
}
