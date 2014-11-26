package fr.univ.blois.siad.m2.ws.jaxb.td01;

import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.TownAssociationManagement;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Cultural;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Sport;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.SportType;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.Adherent;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.BoardMember;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.BoardMemberType;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.Donor;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class TownAssociationManagementTest {

    @Test
    public void mainTest() {
        TownAssociationManagement townAssociationManagement = new TownAssociationManagement();

        // affecter un nom à la ville
        townAssociationManagement.setTownName("ville");

        // ajouter une association sportive à la gestion des association de la ville
        //  - avec 5 adhérent dont 1 president et un tresorier
        //  - et un compte de 2 équipes
        Sport sport = townAssociationManagement.addAssociation(new Sport("club sportif 1", "sport 1", SportType.TEAM, 2));
        sport.addAdherent(new BoardMember(1L, "name", "surname", "20140101", BoardMemberType.PRESIDENT));
        sport.addAdherent(new BoardMember(2L, "name", "surname", "20140101", BoardMemberType.TREASURER));
        sport.addAdherent(new Adherent(3L, "name", "surname", "20140601"));
        sport.addAdherent(new Adherent(4L, "name", "surname", "20140601"));
        sport.addAdherent(new Adherent(5L, "name", "surname", "20140601"));


        // ajouter une association culturelle à la gestion des association de la ville
        //  - avec x adhérent (min 4) dont 1 president, un tresorier et un secretaire
        //  - avec 1 donateur
        Cultural cultural = townAssociationManagement.addAssociation(new Cultural("association culturelle 1", "Photo"));
        cultural.addAdherent(new BoardMember(1L, "name", "surmane", "20140101", BoardMemberType.PRESIDENT));
        cultural.addAdherent(new BoardMember(2L, "name", "surmane", "20140101", BoardMemberType.TREASURER));
        cultural.addAdherent(new BoardMember(3L, "name", "surmane", "20140101", BoardMemberType.SECRETARY));
        cultural.addAdherent(new Adherent(4L, "name"," surname", "20140101"));
        cultural.addAdherent(new Donor(5L, "name", "surname", 2000));

        serializeTownAssociationManagementAsXml(townAssociationManagement);
    }

    private void serializeTownAssociationManagementAsXml(TownAssociationManagement townAssociationManagement) {
        if (townAssociationManagement != null) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(TownAssociationManagement.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty("jaxb.encoding",  "UTF-8") ;
                marshaller.setProperty("jaxb.formatted.output", true) ;
                marshaller.marshal(townAssociationManagement, new File(townAssociationManagement.getTownName() + "_associations.xml"));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        // serialiser la gestion des association dans un fichier xml portant le nom de la ville
    }
}
