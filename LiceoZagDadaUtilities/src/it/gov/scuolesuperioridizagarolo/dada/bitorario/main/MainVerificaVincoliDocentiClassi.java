package it.gov.scuolesuperioridizagarolo.dada.bitorario.main;

//import dada.bitorario.constraint.impl.*;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;

import java.io.IOException;

/**
 * Created by stefano on 25/09/2017.
 */
public class MainVerificaVincoliDocentiClassi {



    public static void main(String[] args) throws IOException {

        final BitOrarioGrigliaOrario orarioTotale = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi();
        new CheckForTeacher_5oreAlGiorno().printReport(orarioTotale);
        new CheckForClassroom_CoerenzaCapacitàClassiAule().printReport(orarioTotale);
        new CheckForTeacher_OreBucheTotali().printReport(orarioTotale);
        new CheckForTeacher_LezioniMateriaRipetute().printReport(orarioTotale);
        new CheckForTeacher_ClassiRipetute().printReport(orarioTotale);
        new CheckForTeacher_GiorniLiberi().printReport(orarioTotale);
        new CheckForTeacher_AuleRipetute().printReport(orarioTotale);


    }


}
