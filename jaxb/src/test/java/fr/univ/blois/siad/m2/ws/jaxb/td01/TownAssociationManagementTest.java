package fr.univ.blois.siad.m2.ws.jaxb.td01;

import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.TownAssociationManagement;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.Sport;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.club.SportType;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.Adherent;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.BoardMember;
import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.person.BoardMemberType;
import org.junit.Test;

public class TownAssociationManagementTest {

    @Test
    public void mainTest() {
        TownAssociationManagement townAssociationManagement = new TownAssociationManagement();

        // affecter un nom à la ville
        townAssociationManagement.setTownName("ville");

        // ajouter une association sportive à la gestion des association de la ville
        Sport sport = townAssociationManagement.addAssociation(new Sport("club sportif 1", "sport 1", SportType.TEAM, 2));
        sport.addAdherent(new BoardMember(1L, "name", "surname", "20140101", BoardMemberType.PRESIDENT));
        sport.addAdherent(new BoardMember(2L, "name", "surname", "20140101", BoardMemberType.TREASURER));
        sport.addAdherent(new Adherent(3L, "name", "surname", "20140601"));
        sport.addAdherent(new Adherent(4L, "name", "surname", "20140601"));
        sport.addAdherent(new Adherent(5L, "name", "surname", "20140601"));

        //  - avec 5 adhérent dont 1 president et un tresorier
        //  - et un compte de 2 équipes

        // ajouter une association culturelle à la gestion des association de la ville
        //  - avec x adhérent (min 4) dont 1 president, un tresorier et un secretaire
        //  - avec 1 donateur

        serializeTownAssociationManagementAsXml(townAssociationManagement);
    }

    private void serializeTownAssociationManagementAsXml(TownAssociationManagement townAssociationManagement) {
        // serialiser la gestion des association dans un fichier xml portant le nom de la ville
    }
}
