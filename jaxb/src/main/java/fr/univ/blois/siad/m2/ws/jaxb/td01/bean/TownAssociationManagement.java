package fr.univ.blois.siad.m2.ws.jaxb.td01.bean;

import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Association;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francois on 21/11/14.
 */
public class TownAssociationManagement {

    private String townName;

    private List<Association> associationList;

    public TownAssociationManagement() {
    }

    public TownAssociationManagement(String townName) {
        this.townName = townName;
        this.associationList = new ArrayList<>();
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
