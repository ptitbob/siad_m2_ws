/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ.blois.jee.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity representant un evenement.
 * @author francois
 */
@Entity
@Table(name="ACTIVITIES")
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="ACTIVITY_GENERATOR")
    @Column(name="ACTIVITY_ID")
    @TableGenerator(name="ACTIVITY_GENERATOR", table="SEQUENCES", 
                    pkColumnName="SEQUENCE_NAME", valueColumnName="SEQUENCE_VALUE",
                    pkColumnValue="ACTIVITY_SEQUENCE")
    private Long id;
    @Column(name="ACTIVITY_NAME")
    private String name;
    @Column(name="ACTIVITY_DESC", length=100)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTIVITY_DATE")
    private Date dateEvent;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTIVITY_DTCREATE")
    private Date dateCreation;
    @ManyToMany
    @JoinTable(name="ACTIVITIES_CATEGORIES",
        joinColumns=@JoinColumn(name="AC_AC_ID",referencedColumnName="ACTIVITY_ID"),
        inverseJoinColumns=@JoinColumn(name="AC_CAT_ID", referencedColumnName="CATEGORY_ID"))
    private Set<Category> categoryList;
    @OneToMany(mappedBy="activity")
    private Set<Adhesion> adhesionList;

    /**
     * Constructeur sans argument.
     */
    public Activity() {
    }

    /**
     * Constructeur paramétré
     * @param name
     * @param description
     * @param dateEvent
     * @param dateCreation 
     */
    public Activity(String name, String description, Date dateEvent, Date dateCreation) {
        this.name = name;
        this.description = description;
        this.dateEvent = dateEvent;
        this.dateCreation = dateCreation;
        this.adhesionList = new HashSet<>();
        this.categoryList = new HashSet<>();
    }
    
    /**
     * Renvoie la date de creation.
     * @return
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Positionne la date de creation.
     * @param dateCreation
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Renvoie la date de l'evenement.
     * @return
     */
    public Date getDateEvent() {
        return dateEvent;
    }

    /**
     * Positionne la date de l'evenement.
     * @param dateEvent
     */
    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    /**
     * Renvoie la description de l'evenement.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Positionne la description de l'evenement.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Renvoie l'id de l'evenement.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Positionne l'id de l'evenement. Attention cet id est genere par JPA lors
     * de la premiere persistance de l'entity.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie le nom de l'evenement.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Positionne le nom de l'evenement.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie la liste des adhesions a un evenement. Cette liste est positionne
     * en LAZY loading et donc null si on ne force pas son chargement.
     * @return
     */
    public Set<Adhesion> getAdhesionList() {
        return adhesionList;
    }

    /**
     * Positionne la liste des adhesions à un evenement.
     * @param adhesionList
     */
    public void setAdhesionList(Set<Adhesion> adhesionList) {
        this.adhesionList = adhesionList;
    }

    /**
     * Renvoie la liste des categories d'un evenement. Cette liste est positionne
     * en LAZY loading et donc null si on ne force pas son chargement.
     * @return
     */
    public Set<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * Positionne la liste des categories d'un evenement.
     * @param categoryList
     */
    public void setCategoryList(Set<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Activity other = (Activity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.description != other.description && (this.description == null || !this.description.equals(other.description))) {
            return false;
        }
        if (this.dateEvent != other.dateEvent && (this.dateEvent == null || !this.dateEvent.equals(other.dateEvent))) {
            return false;
        }
        if (this.dateCreation != other.dateCreation && (this.dateCreation == null || !this.dateCreation.equals(other.dateCreation))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 59 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 59 * hash + (this.dateEvent != null ? this.dateEvent.hashCode() : 0);
        hash = 59 * hash + (this.dateCreation != null ? this.dateCreation.hashCode() : 0);
        return hash;
    }
    
    
}
