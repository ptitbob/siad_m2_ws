package fr.univ.blois.siad.m2.ws.jaxb.td01.bean;

import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Association;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Cultural;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Sport;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francois on 21/11/14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TownAssociationManagement {

    private String townName;

    @XmlElementWrapper(name = "listAssociation")
    @XmlElements({
            @XmlElement(name = "club_sportif", type = Sport.class)
            , @XmlElement(name = "association_culturelle", type = Cultural.class)
    })
    private List<Association> associationList;

    public TownAssociationManagement() {
        this.associationList = new ArrayList<>();
    }

    public TownAssociationManagement(String townName) {
        this();
        this.townName = townName;
    }

    public List<Association> getAssociationList() {
        return associationList;
    }

    public void setAssociationList(List<Association> associationList) {
        this.associationList = associationList;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public <A extends Association> A addAssociation(A association) {
        associationList.add(association);
        return association;
    }

}
