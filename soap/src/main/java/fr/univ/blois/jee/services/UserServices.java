/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.blois.jee.services;

import fr.univ.blois.jee.domain.User;
import fr.univ.blois.jee.exception.UserUnknownException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Interface Local de l'EJB regroupant les services relatifs aux utilisateurs
 * (Chargement, creation...)
 * @author francois
 */
@Stateless
@LocalBean
public class UserServices {

    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Methode renvoyant un utilisateur apres recherche sur son login et mot de
     * passe.
     *
     * @param login le login de l'utilisateur.
     * @param password le mot de passe de l'utilisateur.
     * @return l'utilisateur trouve.
     * @throws fr.blois.activity.exceptions.UserUnknownException si aucun ou
     * plusieurs utilisateur sont trouves.
     */
    //Annotation indiquant le comportement transactionnel
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User findByLoginAndPassword(String login, String password) throws UserUnknownException {
        //il faut utiliser la requete nommer et positionner les parametres.
        Query query = entityManager.createNamedQuery(User.NQ_FIND_BY_LOGIN_AND_PASS);
        query.setParameter(User.PARAM_LOGIN, login);
        query.setParameter(User.PARAM_PASSWORD, password);
        //On doit récuperer un utilisateur, on utilise donc la methode getSingleResult
        //de Query mais on doit gerer les exceptions.
        User result = null;
        try {
            result = (User) query.getSingleResult();
        } catch (NoResultException nru) {
            // si aucun résultat n'est trouve
            throw new UserUnknownException("Aucun utilisateur ne correspond a ce couple login et mot de passe");
        } catch (NonUniqueResultException nure) {
            //plusieurs resultats sont trouves. Ce cas ne devrait pas arriver si on
            //verifie l'unicite des logins lors de la creation des users.
            throw new UserUnknownException("Plusieurs utilisateur correspondent a ce couple login et mot de passe");
        }
        return result;

    }

    /**
     * Methode renvoyant un utilisateur en forceant le chargement de ses
     * adhesions.
     *
     * @param userId id de l'utilisateur a charger.
     * @return l'utilisateur avec la liste des adhesions chargees.
     * @throws fr.blois.activity.exceptions.UserUnknownException si aucun
     * utilisateur ne correspond a l'Id.
     */
    //Annotation indiquant le comportement transactionnel
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User loadUserWithAdhesions(Long userId) throws UserUnknownException {
        //Pour forcer le chargement des adhesions (qui sont en fetch lazy), il faut
        //utiliser une NamedQuery qui force le chargement.
        Query query = entityManager.createNamedQuery(User.NQ_LOAD_BY_ID_WITH_ADHESIONS);
        query.setParameter(User.PARAM_ID, userId);
        //on requete sur l'ID et doit donc avoir un seul resultat.
        //toutefois on gere l'exception d'Id non trouvé.
        User result = null;
        try {
            result = (User) query.getSingleResult();
        } catch (NoResultException nre) {
            throw new UserUnknownException("Aucun utilisateur ne correspond a cet Identifiant");
        }
        return result;
    }

}
