package fr.univ.blois.jee.restful.domain;

/**
 * Exception levée lorsqu'un genre litteraire n'existe pas.
 */
public class IllegalGenreSpotted extends Exception {
    public IllegalGenreSpotted(String genreSpotted) {
        super(String.format("Le genre \"%s\" n'exista pas", genreSpotted));
    }
}
