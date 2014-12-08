package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person;

public class Donor extends Person {

    private int donationAmount;

    public Donor() {
    }

    public Donor(Long id, String name, String surname, int donationAmount) {
        super(id, name, surname);
        this.donationAmount = donationAmount;
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }
}
