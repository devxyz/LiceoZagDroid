package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine2;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl.CheckForClassroom_CoerenzaCapacitàClassiAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.output.NoteVariazioniBitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestAssegnazoneAuleEngine2 {
    public static void main(String[] args) throws IOException {

        File file = new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/09 completo con suppl 25 novembre/timetable_definitivo_20200107145557_09.12.2019_10.06.2020.json.zip");
        BitOrarioGrigliaOrario o = MainParserGeneraStampeOrario.readJsonFileOrarioAuleClassi(file);
        System.out.println(o.getClassi().size());
        if (true) return;

        LessonConstraintContainer l = new LessonConstraintContainer();
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        //======================================================================
        //======================================================================


        ArrayList<BitOrarioOraLezione> ris = AssegnazioneAuleEngine2.assegnazioneAule(o, l, false);

        //System.out.println("LEZIONI");
        BitOrarioGrigliaOrario orario_new = new BitOrarioGrigliaOrario("Prova");
        for (BitOrarioOraLezione x : ris) {
            orario_new.addLezione(x);
            //  System.out.println(x);
        }

        checkFinale(l, o, orario_new);

    }

    private static void checkFinale(LessonConstraintContainer l, BitOrarioGrigliaOrario o, BitOrarioGrigliaOrario orario_new) throws IOException {

        System.out.println();
        System.out.println("**********************\n>> Controllo vincoli utente");
        final List<String> strings = l.checkAllNotPassed(orario_new.getLezioni(), o);
        for (String x : strings) {
            System.out.println("  >> NON RISOLTO: " + x);
        }

        //controllo correttezza riassegnazione
        System.out.println("**********************\n>> Controllo capacità aule");
        new CheckForClassroom_CoerenzaCapacitàClassiAule().printReport(orario_new);

        System.out.println("**********************\n>> Controllo uso multiplo stessa aula - per errore programma");
        final String s1 = AbstractVincoliSostituzioni.checkAuleMultiple(orario_new);
        System.out.println(s1);

        System.out.println("**********************\n>> Controllo stessa aula per ore consecutive su stessa materia/classe/insegnante");
        {
            final List<LessonConstraint_OreConsecutiveStessaAula> genera = LessonConstraint_OreConsecutiveStessaAula.genera(orario_new);
            final ArrayList<BitOrarioOraLezione> lezioni = orario_new.getLezioni();

            for (LessonConstraint_OreConsecutiveStessaAula x : genera) {
                if (!x.checkAll(lezioni, orario_new))
                    System.out.println("   >> vincolo non soddisfatto: " + x);
            }
        }

        MotoreSostituzioneAule3.generaFileOutput(orario_new, o, "prova", new NoteVariazioniBitOrarioGrigliaOrario(),
                new File("/Users/stefano/Downloads/PROVE-ORARIO/"), null);

    }


}
