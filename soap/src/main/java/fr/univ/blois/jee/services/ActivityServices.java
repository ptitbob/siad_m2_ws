/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.blois.jee.services;

import fr.univ.blois.jee.domain.Activity;
import fr.univ.blois.jee.domain.Adhesion;
import fr.univ.blois.jee.domain.Category;
import fr.univ.blois.jee.domain.User;
import java.util.Calendar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author francois
 */
@Stateless
@LocalBean
public class ActivityServices {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Methode renvoyant la liste de toutes les Category triees par nom.
     *
     * @return
     */
    //Annotation indiquant la gestion des transactions.
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Category> getAllCategoriesOrderedByName() {
        //Construction de la requete
        Query query = entityManager.createNamedQuery(Category.FIND_ALL_ORDERED_BY_NAME);
        return (List<Category>) query.getResultList();
    }


    /**
     * Methode renvoyant la liste des activity correspondant a une liste de
     * category donnee.
     *
     * @param categoryIdList Liste des Id de category.
     * @return
     */
    //Annontation pour la gestion de la transaction
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Activity> findManyByCategories(List<Long> categoryIdList) {
        // A cause d'une limitation de jpa, on ne peut pas faire de requete de 
        //la forme : 
        //select a from Activity a left join a.categoryList c where c.id in :param
        //avec un parametre sous forme de liste
        //on est donc obliger de construire la requete.

        StringBuilder queryString = new StringBuilder("select distinct a from Activity a left join a.categoryList c where c.id in (");
        boolean needComma = false;
        for (Long l : categoryIdList) {
            if (needComma) {
                queryString.append(",");
            }
            queryString.append(l);
            needComma = true;
        }
        queryString.append(")");
        Query query = entityManager.createQuery(queryString.toString());

        return (List<Activity>) query.getResultList();
    }

    /**
     * Enregistre l'adhesions d'un utilisateur a un evenement.
     *
     * @param user l'utilisateur.
     * @param activity l'activity.
     */
    //Annotation pour la gestion de transaction
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void adhereActivity(User user, Activity activity) {
        //on cree l'adhesion
        Adhesion adhesion = new Adhesion();
        adhesion.setActivity(activity);
        adhesion.setDateCreation(Calendar.getInstance().getTime());
        adhesion.setUser(user);
        entityManager.persist(adhesion);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Adhesion> findAdhesionForUser(long userId) {
        Query query = entityManager.createNamedQuery(Adhesion.ND_FIND_BY_USER);
        query.setParameter(User.PARAM_ID, userId);
        return query.getResultList();
    }

}
