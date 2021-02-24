package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2019_20;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.vincoli.ConstraintUtil;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.FastSetRoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_AulaNonDisponibile;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_DocenteFermoInAulaDidatticaPerOre;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_1920_nYY_TEST extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_1920_nYY_TEST l = new VincoliSostituzioni_1920_nYY_TEST();
        MotoreSostituzioneAule3.doTaskFromJSon(
                l,
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/09 completo con suppl 25 novembre/timetable_definitivo_20200107145557_09.12.2019_10.06.2020.json.zip"),
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/09 completo con suppl 25 novembre/output3/"),
                l.filtroAuleSpostamenti(), true, true);
    }

    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {

    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {

        throw new IllegalArgumentException("DEBUG");
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_SE_LIBERI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                //FilterAule.LABORATORI_SEMPRE,
        };
    }

    private ClassData[] classiDaScludere() {
        ArrayList<ClassData> classi = new ArrayList<>();
        for (ClassData value : ClassData.values()) {
            if (value.numberOfStudents <= 24) {
                classi.add(value);
            }
        }
        return classi.toArray(new ClassData[classi.size()]);
    }

    public VincoliSostituzioni_1920_nYY_TEST invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {


        dal = "09/03/2020";
        //yyyy.MM.dd
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parse = f.parse(dal);
            System.out.println(parse);
            Calendar c = Calendar.getInstance();
            c.setTime(parse);
            c.add(Calendar.DAY_OF_WEEK, 4);
            al = f.format(c.getTime());

            System.out.println("DAL: " + dal + " AL " + al);

        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

        //al = "13/06/2020";


/*        for (ClassData classData : orarioTotale.getClassi()) {
            TreeMap<String, ArrayList<String>> docenti = orarioTotale.getConsiglioDiClasse(classData);
            for (Map.Entry<String, ArrayList<String>> e : docenti.entrySet()) {
                String nominativo = e.getKey();
                int beginIndex = nominativo.lastIndexOf(" ");
                String nome;
                String cognome;
                if (beginIndex < 0) {
                    cognome = nominativo;
                    nome = "";
                } else {
                    nome = nominativo.substring(beginIndex);
                    cognome = nominativo.substring(0, beginIndex);
                }
                ArrayList<String> materie = e.getValue();
                System.out.println(cognome + "\t" + nome + "\t" + classData._class + classData._section + "\t" + "liceo" + "\t" + materie);
                //System.out.println(classData._class + classData._section + "\tliceo\t" + nominativo + "\temail\t" + materie);
            }


        }
*/

        if (true) throw new IllegalArgumentException("NOOO");


        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());

        //gaetano Piccolo
        //    l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C16, EGiorno.LUNEDI, EOra.QUARTA, EOra.QUINTA, EOra.SESTA));
        //  l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C14, EGiorno.LUNEDI, EOra.QUARTA, EOra.QUINTA, EOra.SESTA));

        orarioTotale.classeInVisitaDidattica("Circolare n.110 - Incontro con il prof. Gaetano Piccolo aula C16", ClassData.CLASS_3E, EGiorno.LUNEDI, EOra.QUARTA, EOra.QUINTA);
        orarioTotale.classeInVisitaDidattica("Circolare n.110 - Incontro con il prof. Gaetano Piccolo aula C16", ClassData.CLASS_3F, EGiorno.LUNEDI, EOra.QUARTA, EOra.QUINTA);

        for (EGiorno eGiorno : EGiorno.valuesGiorniDiLezione()) {
            l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A1, eGiorno, EOra.values()));
        }

        EOra[] _10_12 = {EOra.TERZA, EOra.QUARTA};
        EOra[] _8_10 = {EOra.PRIMA, EOra.SECONDA};
        EOra[] _12_14 = {EOra.QUINTA, EOra.SESTA};

        //INVALSI

        classeINVALSI(ClassData.CLASS_5C, orarioTotale, "ITALIANO", ClassData.CLASS_5B, EGiorno.LUNEDI, _10_12);
        classeINVALSI(ClassData.CLASS_5A, orarioTotale, "ITALIANO", ClassData.CLASS_5D, EGiorno.MARTEDI, _8_10);
        classeINVALSI(ClassData.CLASS_5E, orarioTotale, "ITALIANO", ClassData.CLASS_5C, EGiorno.MERCOLEDI, _8_10);
        classeINVALSI(ClassData.CLASS_5B, orarioTotale, "ITALIANO", ClassData.CLASS_5A, EGiorno.GIOVEDI, _10_12);
        classeINVALSI(ClassData.CLASS_5D, orarioTotale, "ITALIANO", ClassData.CLASS_5E, EGiorno.VENERDI, _12_14);

        classeINVALSI(ClassData.CLASS_5C, orarioTotale, "MATEMATICA", ClassData.CLASS_5A, EGiorno.LUNEDI, _8_10);
        classeINVALSI(ClassData.CLASS_5A, orarioTotale, "MATEMATICA", ClassData.CLASS_5E, EGiorno.MARTEDI, _10_12);
        classeINVALSI(ClassData.CLASS_5E, orarioTotale, "MATEMATICA", ClassData.CLASS_5D, EGiorno.MERCOLEDI, _10_12);
        classeINVALSI(ClassData.CLASS_5B, orarioTotale, "MATEMATICA", ClassData.CLASS_5C, EGiorno.GIOVEDI, _12_14);
        classeINVALSI(ClassData.CLASS_5D, orarioTotale, "MATEMATICA", ClassData.CLASS_5B, EGiorno.VENERDI, _10_12);

        classeINVALSI(ClassData.CLASS_5C, orarioTotale, "INGLESE READING", ClassData.CLASS_5E, EGiorno.LUNEDI, _12_14);
        classeINVALSI(ClassData.CLASS_5A, orarioTotale, "INGLESE READING", ClassData.CLASS_5C, EGiorno.MARTEDI, _12_14);
        classeINVALSI(ClassData.CLASS_5E, orarioTotale, "INGLESE READING", ClassData.CLASS_5B, EGiorno.MERCOLEDI, _12_14);
        classeINVALSI(ClassData.CLASS_5B, orarioTotale, "INGLESE READING", ClassData.CLASS_5D, EGiorno.GIOVEDI, _8_10);
        classeINVALSI(ClassData.CLASS_5D, orarioTotale, "INGLESE READING", ClassData.CLASS_5A, EGiorno.VENERDI, _8_10);

        //=================== VINCOLI VARI

        //lunedi' giornata violenza donne circolare n.64

/*
        //====================================================================================================================================
        // FERRIGNO
        //====================================================================================================================================
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "FERRIGNO D.", new RoomData[]{RoomData.B8, RoomData.B9, RoomData.B10, RoomData.B11}, EGiorno.values(), EOra.values()));


        for (EGiorno value : EGiorno.values()) {
            l.add(new LessonConstraint_AulaNonDisponibile(RoomData.E30sharp, value, EOra.values()));
        }

*/
        ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(orarioTotale.getLezioni());
        ConstraintUtil.sortLezioniPerClasse(lezioni);
        List<FastSetRoomData> f2 = ConstraintUtil.validRoom(l, orarioTotale, lezioni);

        for (int i = 0; i < 100; i++) {
            BitOrarioOraLezione x1 = lezioni.get(i);
            FastSetRoomData x2 = f2.get(i);
            if (x2 != null)
                System.out.println(x1.toStringShort() + " " + x2.toStringAule());
            /*else
                System.out.println(x1.toStringShort() + " NULL");

             */
        }

        throw new IllegalArgumentException("DEBUG");
        //return this;
    }

    public void classeINVALSI(ClassData secondaria, BitOrarioGrigliaOrario orarioTotale, String materia, ClassData principale, EGiorno giorno, EOra[] fascia) {
        String note = "Circolare n.128 - prova INVALSI " + materia + " classe " + principale + " e INGLESE LISTENING per 4 studenti ogni ora classe " + secondaria + " - AULA A1";
        orarioTotale.classeInVisitaDidattica(note, principale, giorno, fascia);
    }
}
