/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.blois.jee.services;

import fr.univ.blois.jee.domain.Activity;
import fr.univ.blois.jee.domain.Adhesion;
import fr.univ.blois.jee.domain.Category;
import fr.univ.blois.jee.domain.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author francois
 */
@Singleton
@Startup
public class ActivityPopulate {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Initialisation des données de la base de donnée.
     */
    @PostConstruct
    public final void initializeData() {
       fillData();
    } 

    private void fillData() {
        fillUser();
        fillCategory();
        fillActivity();
        fillAdhesion();
    }

    private void fillUser() {
        entityManager.persist(new User("DUPONT", "PIERRE", "PASS", "DUPONTP", getDate("20130923")));
        entityManager.persist(new User("DURAND", "PAUL", "PASS", "DURANDP", getDate("20130924")));
    }

    private Date getDate(String string) {
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(string);
        } catch (ParseException ex) {
            Logger.getLogger(ActivityPopulate.class.getName()).log(Level.SEVERE, null, ex);
            return new Date();
        }
    }

    private void fillCategory() {
        entityManager.persist(new Category("SPORT"));
        entityManager.persist(new Category("MUSIQUE"));
        entityManager.persist(new Category("CINEMA"));
        entityManager.persist(new Category("CULTURE"));
    }

    private void fillActivity() {
       Activity activity = new Activity("tournoi foot", "Tournois de football amateur gratuit", getDateTime("20131120-1400"), getDateTime("20131120-1700"));
       activity.getCategoryList().add(entityManager.find(Category.class, 1L));
       entityManager.persist(activity);
       activity = new Activity("comics history", "Restropective des super héros au cours du temps", getDateTime("20131201-0900"), getDateTime("20131212-1800"));
       activity.getCategoryList().add(entityManager.find(Category.class, 3L));
       activity.getCategoryList().add(entityManager.find(Category.class, 4L));
       entityManager.persist(activity);
       activity = new Activity(" Goldorak est mort", "Concert des Fatals picard", getDateTime("20131220-2000"), getDateTime("20131220-2300"));
       activity.getCategoryList().add(entityManager.find(Category.class, 2L));
       activity.getCategoryList().add(entityManager.find(Category.class, 4L));
       entityManager.persist(activity);
    }

    private Date getDateTime(String string) {
        try {
            return new SimpleDateFormat("yyyyMMdd-HHmm").parse(string);
        } catch (ParseException ex) {
            Logger.getLogger(ActivityPopulate.class.getName()).log(Level.SEVERE, null, ex);
            return new Date();
        }
    }

    private void fillAdhesion() {
        entityManager.persist(new Adhesion(entityManager.find(User.class, 1L), entityManager.find(Activity.class, 1L)));
        entityManager.persist(new Adhesion(entityManager.find(User.class, 1L), entityManager.find(Activity.class, 3L)));
        entityManager.persist(new Adhesion(entityManager.find(User.class, 2L), entityManager.find(Activity.class, 1L)));
    }
}
