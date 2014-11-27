/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.blois.jee.view;

import fr.univ.blois.jee.domain.Activity;
import fr.univ.blois.jee.domain.Adhesion;
import fr.univ.blois.jee.domain.Category;
import fr.univ.blois.jee.domain.User;
import fr.univ.blois.jee.exception.UserUnknownException;
import fr.univ.blois.jee.services.ActivityServices;
import fr.univ.blois.jee.services.UserServices;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * Managed Bean utilise pour la gestion des activites.
 *
 * @author francois
 */
@ManagedBean
@SessionScoped
public class ActivityBean {

    //Liste de SelectItem pour alimenter la serie de case a cocher.
    private List<SelectItem> categoryList;
    //Liste des ID des Category cochees
    private List<Long> selectedCategoryList;
    //Liste des Activity pour le tableau de resultat de recherche.
    private List<Activity> activityList;
    // Binding de la dataTable de resultat.
    private HtmlDataTable activityListBinding;
    //Liste des adhesions de l'utilisateur.
    private List<Adhesion> adhesionListe;

    @EJB
    private ActivityServices activityServices;
    
    @EJB
    private UserServices userServices;

    //Constante pour la navigation
    private final static String AFFICHE_LISTE_ADHESIONS = "afficheListeAdhesions";

    /**
     * Creates a new instance of ActivityBean
     */
    public ActivityBean() {
    }
    
    /**
     * Methode appelee par clic du bouton Recherche de la page Search.
     *
     * @return
     */
    public String findActivities() {
        activityList = activityServices.findManyByCategories(selectedCategoryList);
        return null;
    }

    /**
     * Methode appelee par clic du lien "voir mes adhesions de la page accueil.
     *
     * @return
     */
    public String loadAdhesions() {
        //recuperation de l'utilisateur.
        User user = getConnectedUser();
        adhesionListe = activityServices.findAdhesionForUser(user.getId());
        //demande a l'EJB de charger l'utilisateur avec ses adhesions.
        return AFFICHE_LISTE_ADHESIONS;
    }

    /**
     * Methode appelee quand un utilisateur clique sur le lien d'adhesion a une
     * activité dans le tableau de resultat.
     *
     * @return
     */
    public String adhereActivity() {
        //Recuperation de l'evement associe à la ligne selectionnee
        //C'est a cela que sert le binding sur la datatable.
        Activity activity = (Activity) activityListBinding.getRowData();
        //recuperation de l'utilisateur
        User user = getConnectedUser();
        activityServices.adhereActivity(user, activity);
        FacesMessage message = new FacesMessage("Adhesion créé avec succès");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }

    /**
     * Methode renvoyant l'utilisateur identifier dans l'application.
     *
     * @return
     */
    private User getConnectedUser() {
        //pour cela on doit recuperer les UserBackBean
        //on recupere le FacesContext
        FacesContext context = FacesContext.getCurrentInstance();
        //on recupere le UserBackBean en utilisant le binding EL sur le managedBean declare.
        UserBean ubb = (UserBean) context.getApplication().createValueBinding("#{userBean}").getValue(context);
        return ubb.getCurrentUser();
    }

    /**
     * Renvoie la liste des Activity resultat de la recherche.
     *
     * @return
     */
    public List<Activity> getActivityList() {
        return activityList;
    }

    /**
     * Positionne la liste des Activity resultat de la recherche.
     *
     * @param activityList
     */
    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    /**
     * Renvoie la liste des Category pour les cases a cocher.
     *
     * @return
     */
    public List<SelectItem> getCategoryList() {
        //on fait un chargement que si utilisation (Lazy loading)
        if (categoryList == null) {
            categoryList = new ArrayList<>();
            List<Category> result = activityServices.getAllCategoriesOrderedByName();
            for (Category cat : result) {
                SelectItem si = new SelectItem(cat.getId(), cat.getName());
                categoryList.add(si);
            }
        }
        return categoryList;
    }

    /**
     * Positionne la liste des Category pour les cases a cocher.
     *
     * @param categoryList
     */
    public void setCategoryList(List<SelectItem> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * Renvoie la liste des Category selectionnees dans les cases a cocher.
     *
     * @return
     */
    public List<Long> getSelectedCategoryList() {
        return selectedCategoryList;
    }

    /**
     * Positionne la liste des Category selectionnees dans les cases a cocher.
     *
     * @param selectedCategoryList
     */
    public void setSelectedCategoryList(List<Long> selectedCategoryList) {
        this.selectedCategoryList = selectedCategoryList;
    }

    /**
     * Methode indiquant si le tableau de resultat doit etre affiché.
     *
     * @return
     */
    public boolean isRenderSearchResult() {
        return (activityList != null && !activityList.isEmpty());
    }

    /**
     * Renvoie la composant pour le binding de la datatable de recherche. Ce
     * binding permet de recuperer la ligne cliquee dans le tableau.
     *
     * @return
     */
    public HtmlDataTable getActivityListBinding() {
        return activityListBinding;
    }

    /**
     * Positionne le composant pour le binding de la datatable de recherche.
     *
     * @param activityListBinding
     */
    public void setActivityListBinding(HtmlDataTable activityListBinding) {
        this.activityListBinding = activityListBinding;
    }

    /**
     * Renvoie la liste des adhesions de l'utilisateur.
     *
     * @return
     */
    public List<Adhesion> getAdhesionListe() {
        return adhesionListe;
    }

    /**
     * Positionne la liste des adhesions de l'utilisateur.
     *
     * @param adhesionListe
     */
    public void setAdhesionListe(List<Adhesion> adhesionListe) {
        this.adhesionListe = adhesionListe;
    }

}
