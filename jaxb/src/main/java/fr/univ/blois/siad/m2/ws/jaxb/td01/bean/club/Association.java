package fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club;

import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.Adherent;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.BoardMember;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.Donor;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.Person;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Association {

    private String name;

    @XmlElementWrapper(name = "adherents")
    @XmlElements({
            @XmlElement(name = "donateur", type = Donor.class)
            , @XmlElement(name = "adherent", type = Adherent.class)
            , @XmlElement(name = "membre_bureau", type = BoardMember.class)
    })
    private Set<Person> adherentList;

    @XmlTransient
    private int adherentCount = Integer.MIN_VALUE;

    public Association() {
        this.adherentList = new HashSet<>();
    }

    public Association(String name) {
        this();
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
        getAdherentCount();
        adherentList.add(person);
        adherentCount++;
    }

    public int getAdherentCount() {
        if (adherentCount == Integer.MIN_VALUE) {
            adherentCount = adherentList.size();
        }
        return adherentCount;
    }
}
