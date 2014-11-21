package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person;

import java.util.Date;

public class Adherent extends Person {

    private String membershipDate; // date d'ahésion sous la forma YYYYMMDD

    public Adherent() {
    }

    public Adherent(Long id, String name, String surname, String membershipDate) {
        super(id, name, surname);
        this.membershipDate = membershipDate;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }
}
