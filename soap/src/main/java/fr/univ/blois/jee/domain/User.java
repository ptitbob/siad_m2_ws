/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.univ.blois.jee.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity representant un utilisateur de l'application.
 * @author francois
 */
@Entity
@Table(name="USERS")
@NamedQueries({
    @NamedQuery(name=User.NQ_FIND_BY_LOGIN_AND_PASS, query="select u from User u where u.login=:login and u.password=:pass"),
    @NamedQuery(name = User.NQ_LOAD_BY_ID_WITH_ADHESIONS, query = "select u from User u left join fetch u.adhesionList al left join fetch al.activity  where u.id=:identifiant")
})
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="USER_GENERATOR")
    @TableGenerator(name="USER_GENERATOR", table="SEQUENCES", 
                    pkColumnName="SEQUENCE_NAME", valueColumnName="SEQUENCE_VALUE",
                    pkColumnValue="USER_SEQUENCE")

    @Column(name="USER_ID")
    private Long id;
    @Column(name="USER_LOGIN", length=10)
    private String login;
    @Column(name="USER_PWD", length=10)
    private String password;
    @Column(name="USER_NAME", length=25)
    private String name;
    @Column(name="USER_SURNAME", length=25)
    private String surname;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="USER_DTCREATE")
    private Date dateCreation;
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adhesion> adhesionList;

    /**
     * Constante utilisee pour nommer la requete cherchant par nom et mot de passe.
     */
    public static final String NQ_FIND_BY_LOGIN_AND_PASS="User.findByLoginAndPass";
    /**
     * Constante utilisee pour nommer la requete chargeant un utilisateur
     * en fonction de son ID et en forceant le chargement des adhesions.
     */
    public static final String NQ_LOAD_BY_ID_WITH_ADHESIONS="User.loadWithAdhesions";
    /**
     * Constante utilisee pour le parametre de login.
     */
    public static final String PARAM_LOGIN="login";
    /**
     * Constante utilisee pour le parametre de mot de passe.
     */
    public static final String PARAM_PASSWORD="pass";
    /**
     * Constante utilisee pour le parametre d'Id.
     */
    public static final String PARAM_ID="identifiant";
    
    /**
     * Constructeur par defaut.
     */
    public User() {
    }
    
    /**
     * Constructeur paramétré
     * @param name
     * @param surname
     * @param password
     * @param login
     * @param dateCreation 
     */
    public User(String name, String surname, String password, String login, Date dateCreation) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateCreation = dateCreation;
    }

    
    /**
     * Renvoie l'Id de l"utilisateur.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Positionne l'Id de l'utilisateur. Attention cet id est genere par JPA lors
     * de la premiere persistance de l'entity.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie le login de l'utilisateur.
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * Positionne le login de l'utilisateur.
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Renvoie le nom de l'utilisateur.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Positionne le nom de l'utilisateur.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie le mot de passe de l'utilisateur.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Positionne le mot de passe de l'utilisateur.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Renvoie le prenom de l'utilisateur.
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Positionne le prenom de l'utilisateur.
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Renvoie la date de creation de l'utilisateur.
     * @return
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Positionne la date de creation de l'utilisateur.
     * @param dateCreation
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Renvoie la liste des adhesions de l"utilisateur.Cette liste est positionne
     * en LAZY loading et donc null si on ne force pas son chargement.
     * @return
     */
    public Set<Adhesion> getAdhesionList() {
        return adhesionList;
    }

    /**
     * Positionne la liste des adhesions d'un utilisateur.
     * @param adhesionList
     */
    public void setAdhesionList(Set<Adhesion> adhesionList) {
        this.adhesionList = adhesionList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 67 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
}
