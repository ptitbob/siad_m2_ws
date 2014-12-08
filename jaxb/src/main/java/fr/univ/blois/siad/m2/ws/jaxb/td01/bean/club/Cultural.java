package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club;

public class Cultural extends Association {

    private String associationTarget; // Type d'association culturelle (musique, peinture)

    public Cultural() {
    }

    public Cultural(String name, String associationTarget) {
        super(name);
        this.associationTarget = associationTarget;
    }

    public String getAssociationTarget() {
        return associationTarget;
    }

    public void setAssociationTarget(String associationTarget) {
        this.associationTarget = associationTarget;
    }

}
