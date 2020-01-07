package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2019_20;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl.*;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_1920_n5_settimana_14ottobre_18ottobre extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_1920_n5_settimana_14ottobre_18ottobre l = new VincoliSostituzioni_1920_n5_settimana_14ottobre_18ottobre();
        MotoreSostituzioneAule3.doTaskFromTXT(
                l,
                new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/orario20192020/05 definitivo 06 ottobre-fixed/Orario Allocazione Aule.txt"),
                null,
                new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/orario20192020/05 definitivo 06 ottobre-fixed/Orario Professori Orizzontale Semplice.txt"),
                null, null, new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/orario20192020/05 definitivo 06 ottobre-fixed/output"),
                l.filtroAuleSpostamenti(), false, true);
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {


        //new CheckForTeacher_5oreAlGiorno().printReport(orarioTotale);
        //new CheckForClassroom_CoerenzaCapacitàClassiAule().printReport(orarioTotale);
        new CheckForTeacher_OreBucheTotali().printReport(orarioTotale);
        new CheckForTeacher_LezioniMateriaRipetute().printReport(orarioTotale);
        //new CheckForTeacher_ClassiRipetute().printReport(orarioTotale);
        //new CheckForTeacher_GiorniLiberi().printReport(orarioTotale);
        //new CheckForTeacher_AuleRipetute().printReport(orarioTotale);


//        if (true) throw new IllegalArgumentException("ERRORE");
    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                //FilterAule.LABORATORI_SEMPRE,
        };
    }

    public VincoliSostituzioni_1920_n5_settimana_14ottobre_18ottobre invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {


        dal = "14/10/2019";
        al = "18/10/2020";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());

        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _B11, EGiorno.values()));

        //=================== VINCOLI VARI

        //lunedi' giornata violenza donne circolare n.64


        //====================================================================================================================================
        // FERRIGNO
        //====================================================================================================================================
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "FERRIGNO D.", new RoomData[]{RoomData.B8, RoomData.B9, RoomData.B10, RoomData.B11}, EGiorno.values(), EOra.values()));


        //======================================================================
        //======================================================================
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "informatica",
                new ClassData[0],
                new String[]{},
                new RoomData[]{RoomData.A4_INF, RoomData.A1, RoomData.A5sharp}
        ));

        for (EGiorno value : EGiorno.values()) {
            l.add(new LessonConstraint_AulaNonDisponibile(RoomData.E30sharp, value, EOra.values()));
        }






       /* orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5A, EGiorno.LUNEDI, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5F, EGiorno.LUNEDI, EOra.QUARTA);

        orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5A, EGiorno.VENERDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5F, EGiorno.VENERDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA);
*/

        return this;
    }
}
