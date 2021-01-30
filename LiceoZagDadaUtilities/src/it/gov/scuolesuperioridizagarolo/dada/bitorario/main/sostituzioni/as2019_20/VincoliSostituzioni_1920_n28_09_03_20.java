package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2019_20;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_1920_n28_09_03_20 extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_1920_n28_09_03_20 l = new VincoliSostituzioni_1920_n28_09_03_20();

/*
        MotoreSostituzioneAule3.doTaskFromTXT(
                l,
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/08 completo 19 ottobre/Orario Allocazione Aule.txt"),
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/08 completo 19 ottobre/Orario Ore a Disposizione.txt"),
                null,
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/08 completo 19 ottobre/Orario Professori Orizzontale Sostegno.txt"),
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/08 completo 19 ottobre/Orario Professori Orizzontale Progetto.txt"),
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/08 completo 19 ottobre/output2"),
                l.filtroAuleSpostamenti(), true, true);*/

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
        //fix aule differenti non consecutive
        /*{
            BitOrarioOraLezione vecchia = orarioTotale.getLezioneInClasse(EOra.SESTA, EGiorno.VENERDI, _1D);
            BitOrarioOraLezione nuova = vecchia.clonaLezioneInAltraAula(_B13);
            orarioTotale.removeLezione(vecchia);
            orarioTotale.addLezione(nuova);
        }

        {
            BitOrarioOraLezione vecchia = orarioTotale.getLezioneInClasse(EOra.SESTA, EGiorno.GIOVEDI, _4D);
            BitOrarioOraLezione nuova = vecchia.clonaLezioneInAltraAula(_D26);
            orarioTotale.removeLezione(vecchia);
            orarioTotale.addLezione(nuova);
        }*/
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

    public VincoliSostituzioni_1920_n28_09_03_20 invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {


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


        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        for (ClassData classi : ClassData.values()) {
            for (EGiorno giorno : EGiorno.valuesGiorniDiLezione()) {
                orarioTotale.classeInVisitaDidattica("Circolare n.133/134: Didattica online per sospensione attività didattiche", classi, giorno, EOra.values());
            }
        }


        return this;
    }
}
