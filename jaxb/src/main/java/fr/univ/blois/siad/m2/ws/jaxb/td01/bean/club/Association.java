package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club;

import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.BoardMember;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.Person;

import java.util.Set;

public class Association {

    private String name;

    private Set<Person> adherentList;

    public Association() {
    }

    public Association(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getAdherentList() {
        return adherentList;
    }

    public void setAdherentList(Set<Person> adherentList) {
        this.adherentList = adherentList;
    }

    public void addAdherent(Person person) {
        adherentList.add(person);
    }
}
