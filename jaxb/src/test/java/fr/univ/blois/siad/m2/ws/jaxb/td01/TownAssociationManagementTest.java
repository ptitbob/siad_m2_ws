package fr.univ.blois.siad.m2.ws.jaxb.td01;

import fr.univ.blois.siad.m2.ws.jaxb.td01.bean.TownAssociationManagement;
import org.junit.Test;

public class TownAssociationManagementTest {

    @Test
    public void mainTest() {
        TownAssociationManagement townAssociationManagement = new TownAssociationManagement();

        // affecter un nom à la ville

        // ajouter une association sportive à la gestion des association de la ville
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
