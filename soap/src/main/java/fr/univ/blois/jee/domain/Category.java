package fr.univ.blois.jee.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Entity representant les categories qualifiant un evevement.
 * @author francois
 */
@Entity
@Table(name="CATEGORIES")
@NamedQueries({
    @NamedQuery(name=Category.FIND_ALL_ORDERED_BY_NAME, query="select c from Category c order by c.name")
})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="CATEGORY_GENERATOR")
    @Column(name="CATEGORY_ID")
    @TableGenerator(name="CATEGORY_GENERATOR", table="SEQUENCES", 
                    pkColumnName="SEQUENCE_NAME", valueColumnName="SEQUENCE_VALUE",
                    pkColumnValue="CATEGORY_SEQUENCE")

    private Long id;
    @Column(name="CATEGORY_NAME")
    private String name;
    @ManyToMany(mappedBy="categoryList")
    private List<Activity> activityList;
    
    //constantes pour les requetes
    /**
     * Constante pour la requete renvoyant les Category triees par nom.
     */
    public static final String FIND_ALL_ORDERED_BY_NAME="Category.findAllOrderedByName";
    
    /**
     * Constructeur par defaut.
     */
    public Category() {
    }

    /**
     * Constructeur paramétré
     * @param name 
     */
    public Category(String name) {
        this.name = name;
    }

    
    /**
     * Renvoie l'Id d'une categorie.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Positionne l'Id d'une categorie. Attention cet id est genere par JPA lors
     * de la premiere persistance de l'entity.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie le nom d'une categorie.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Positionne le nom d'une categorie.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie la liste des activites associees a la categorie. Cette liste est positionne
     * en LAZY loading et donc null si on ne force pas son chargement.
     * @return
     */
    public List<Activity> getActivityList() {
        return activityList;
    }

    /**
     * Positionne la liste des activites associees a une categorie.
     * @param eventList
     */
    public void setActivityList(List<Activity> eventList) {
        this.activityList = eventList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.activityList != other.activityList && (this.activityList == null || !this.activityList.equals(other.activityList))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 79 * hash + (this.activityList != null ? this.activityList.hashCode() : 0);
        return hash;
    }
    
    
}
