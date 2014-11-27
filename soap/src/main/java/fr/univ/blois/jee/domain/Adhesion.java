/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ.blois.jee.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity representant une adhesion d'un utilisateur a un evenement.
 * @author francois
 */
@Entity
@Table(name="ADHESIONS")
@NamedQueries({
    @NamedQuery(name = Adhesion.ND_FIND_BY_USER, query = "select a from Adhesion a where a.user.id = :identifiant")
})
public class Adhesion implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="ADHESION_GENERATOR")
    @Column(name="ADHESION_ID")
    @TableGenerator(name="ADHESION_GENERATOR", table="SEQUENCES", 
                    pkColumnName="SEQUENCE_NAME", valueColumnName="SEQUENCE_VALUE",
                    pkColumnValue="ADHESION_SEQUENCE")
    private Long id;
    @ManyToOne
    @JoinColumn(name="USER_ID_FK", referencedColumnName="USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name="ACTIVITY_ID_FK", referencedColumnName="ACTIVITY_ID")
    private Activity activity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ADHESION_DATECRE")
    private Date dateCreation;

    public final static String ND_FIND_BY_USER = "Adhesion.loadForUser";
    /**
     * Constructeur par defaut.
     */
    public Adhesion() {
    }

    public Adhesion(User user, Activity activity) {
        this.user = user;
        this.activity = activity;
        this.dateCreation = new Date();
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
     * Renvoie l'evenement associe a cette adhesion.
     * @return
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Positionne l'evenement associe a cette adhesion.
     * @param event
     */
    public void setActivity(Activity event) {
        this.activity = event;
    }

    /**
     * Renvoie l'id de cette adhesion.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Positionne l'id de cette adhesion. Attention cet id est genere par JPA lors
     * de la premiere persistance de l'entity.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie l'utilisateur associe a cette adhesion.
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Positionne l'utilisateur associe a cette adhesion.
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Adhesion other = (Adhesion) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        if (this.activity != other.activity && (this.activity == null || !this.activity.equals(other.activity))) {
            return false;
        }
        if (this.dateCreation != other.dateCreation && (this.dateCreation == null || !this.dateCreation.equals(other.dateCreation))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 11 * hash + (this.user != null ? this.user.hashCode() : 0);
        hash = 11 * hash + (this.activity != null ? this.activity.hashCode() : 0);
        hash = 11 * hash + (this.dateCreation != null ? this.dateCreation.hashCode() : 0);
        return hash;
    }

    
    
}
