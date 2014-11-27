package fr.univ.blois.jee.exception;

import javax.ejb.ApplicationException;

/**
 * Exception utilisee pour indiquer qu'un utilisateur n'est pas trouve, soit par 
 * son idenitifiant, soit la combinaison login+mot de passe.
 * @author francois
 */
//Annotation faisant que l'exception sera notifiee directement au client et pas 
//wrappee en EJBException. Le rollback false indique que la transaction ne sera 
//pas marquee en rollback.
@ApplicationException(rollback=false)
public class UserUnknownException extends Exception{
    /**
     * Constructeur avec un message a renvoyer au client;
     * @param message
     */
    public UserUnknownException(String message) {
        super(message);
    }
    /**
     * Constructeur par defaut.
     */
    public UserUnknownException() {
    }
    
    

}
