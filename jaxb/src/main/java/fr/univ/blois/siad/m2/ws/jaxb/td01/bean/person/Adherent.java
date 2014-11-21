package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person;

import java.util.Date;

public class Adherent extends Person {

    private String membershipDate; // date d'ahésion sous la forma YYYYMMDD

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }
}
