/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.blois.jee.view;

import fr.univ.blois.jee.domain.User;
import fr.univ.blois.jee.exception.UserUnknownException;
import fr.univ.blois.jee.services.UserServices;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * BackBean utiliser pour le login d'un utilisateur et conserver l'utilisateur
 * connecte. Ce Back bean est de scope session car ses donnees servent tout le
 * long de la session utilisateur.
 *
 * @author francois
 */
@ManagedBean
@SessionScoped
public class UserBean {
    //Utilisateur connecte a l'application

    private User currentUser;
    // utiliser pour le login dans le formulaire de la page index.
    private String iptLogin;
    //utiliser pour le mot de passe dans le formulaire de la page index.
    private String iptPassword;

    @EJB
    private UserServices userServices;
    
    //Constante pour la navigation vers la page d'accueil.
    private static final String AFFICHE_ACCUEIL = "afficheAccueil";

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserBean() {
    }

    /**
     * Methode verifiant le couple login/mot de passe en base et envoyant la
     * navigation vers la page accueil si l'utilisateur existe.
     *
     * @return
     */
    public String checkLogin() {
        String navigation = null;
        try {
            currentUser = userServices.findByLoginAndPassword(iptLogin, iptPassword);
            //si login OK, on ne leve pas d'exception et positionne la navigation.
            navigation = AFFICHE_ACCUEIL;
        } catch (UserUnknownException userUnknownException) {
            //l'exception est leve avec le message dedans, on le recupere et 
            // positionne dnas le contexte
            FacesMessage message = new FacesMessage(userUnknownException.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            //la navigation reste null et affiche donc la meme page.
        }
        return navigation;
    }

    /**
     * Renvoie l'utilisateur de la session courante.
     *
     * @return
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Positionne l'utilisateur de la session courante.
     *
     * @param currentUser
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getIptLogin() {
        return iptLogin;
    }

    public void setIptLogin(String iptLogin) {
        this.iptLogin = iptLogin;
    }

    public String getIptPassword() {
        return iptPassword;
    }

    public void setIptPassword(String iptPassword) {
        this.iptPassword = iptPassword;
    }

}
