package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2019_20;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
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
public class VincoliSostituzioni_1920_n14_02_12_19 extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_1920_n14_02_12_19 l = new VincoliSostituzioni_1920_n14_02_12_19();

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
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/09 completo con suppl 25 novembre/output3/timetable_20191129130411_25.11.2019_29.11.2019.json.zip"),
                new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/orario20192020/09 completo con suppl 25 novembre/output3/"),
                l.filtroAuleSpostamenti(), true, true);

    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {

        //aiuta sostituzioni
        {
            BitOrarioOraLezione l = orarioTotale.getLezioneInClasse(EOra.TERZA, EGiorno.GIOVEDI, ClassData.CLASS_2H);
            BitOrarioOraLezione l2 = l.clonaLezioneInAltraAula(RoomData.NON_ASSEGNATO);
            orarioTotale.removeLezione(l);
            orarioTotale.addLezione(l2);
        }

        {
            BitOrarioOraLezione l = orarioTotale.getLezioneInClasse(EOra.TERZA, EGiorno.GIOVEDI, ClassData.CLASS_4B);
            BitOrarioOraLezione l2 = l.clonaLezioneInAltraAula(RoomData.NON_ASSEGNATO);
            orarioTotale.removeLezione(l);
            orarioTotale.addLezione(l2);
        }


    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {
        //fix aule differenti non consecutive
        {
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
        }
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                //FilterAule.LABORATORI_SOLO_COMPATIBILI,
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

    public VincoliSostituzioni_1920_n14_02_12_19 invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {


        dal = "02/12/2019";
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


        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());

        //classi che possono derogare (poco numerose e utili a coprire le aule piccole)
        ClassData[] classiMeno18 = classiDaScludere();


        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A5_DIS, EGiorno.GIOVEDI, EOra.TERZA));

        ClassData[] classi = new ClassData[]{
                ClassData.CLASS_5D,
                ClassData.CLASS_5E,
        };
        for (ClassData c : classi) {
            orarioTotale.classeInVisitaDidattica("Circolare n.60 - Incontro Orientamento 5D / 5E", c, EGiorno.GIOVEDI, EOra.TERZA);

        }



/*
        //======================================================================
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "filosofia",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.D22, RoomData.D23, RoomData.D24, RoomData.D25}
        ));

        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "storia",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.D22, RoomData.D23, RoomData.D24, RoomData.D25}
        ));


        //======================================================================
        //======================================================================
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "informatica",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.A4_INF, RoomData.A1, RoomData.A5sharp}
        ));

        //======================================================================
        //======================================================================
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "inglese",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.E27, RoomData.E28, RoomData.E29, RoomData.E29sharp, RoomData.E30,}
        ));

        //======================================================================
        //======================================================================
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "matematica",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.C14, RoomData.C16, RoomData.C17, RoomData.C19, RoomData.C20, RoomData.C21}
        ));


        //======================================================================
        //======================================================================
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "italiano",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.A1, RoomData.A2, RoomData.A6, RoomData.B8, RoomData.B9, RoomData.B10, RoomData.B11, RoomData.B12, RoomData.B13,}
        ));

        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "STORIA E GEOGRAFIA",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.A1, RoomData.A2, RoomData.A6, RoomData.B8, RoomData.B9, RoomData.B10, RoomData.B11, RoomData.B12, RoomData.B13,}
        ));

        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "LATINO",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.A1, RoomData.A2, RoomData.A6, RoomData.B8, RoomData.B9, RoomData.B10, RoomData.B11, RoomData.B12, RoomData.B13,}
        ));

        //======================================================================
        //======================================================================
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "religione",
                classiMeno18,
                new String[]{},
                new RoomData[]{RoomData.B13sharp, RoomData.B13}
        ));

        /*
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "religione (#)",
                new ClassData[]{},
                new String[]{},
                new RoomData[]{RoomData.B13sharp, RoomData.B13}
        ));
*/

        //filosofia:

        //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F31_PALESTRA, EGiorno.LUNEDI, EOra.values()));
        //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F31_PALESTRA, EGiorno.MARTEDI, EOra.values()));
        //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F31_PALESTRA, EGiorno.MERCOLEDI, EOra.values()));

        //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A5_DIS, EGiorno.MARTEDI, EOra.PRIMA, EOra.SECONDA, EOra.TERZA));
        /*orarioTotale.classeInVisitaDidattica("Viaggio Istruzione Tarquinia e Cerveteri 30 aprile", ClassData.CLASS_1A, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Viaggio Istruzione Tarquinia e Cerveteri 30 aprile", ClassData.CLASS_1B, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Viaggio Istruzione Tarquinia e Cerveteri 30 aprile", ClassData.CLASS_1C, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Viaggio Istruzione Tarquinia e Cerveteri 30 aprile", ClassData.CLASS_1D, EGiorno.MARTEDI, EOra.values());

        orarioTotale.classeInVisitaDidattica("Viaggio Istruzione Tarquinia e Cerveteri 3 maggio", ClassData.CLASS_1F, EGiorno.VENERDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Viaggio Istruzione Tarquinia e Cerveteri 3 maggio", ClassData.CLASS_1E, EGiorno.VENERDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Viaggio Istruzione Tarquinia e Cerveteri 3 maggio", ClassData.CLASS_1H, EGiorno.VENERDI, EOra.values());
*/

/*
        //simuklazione prima prova
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.D22, EGiorno.MARTEDI, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.D23, EGiorno.MARTEDI, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.D24, EGiorno.MARTEDI, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.D25, EGiorno.MARTEDI, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.D26, EGiorno.MARTEDI, EOra.values()));


        orarioTotale.classeInVisitaDidattica("Simulazione prima prova - aule D22-D26", ClassData.CLASS_5A, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione prima prova - aule D22-D26", ClassData.CLASS_5B, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione prima prova - aule D22-D26", ClassData.CLASS_5C, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione prima prova - aule D22-D26", ClassData.CLASS_5D, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione prima prova - aule D22-D26", ClassData.CLASS_5E, EGiorno.MARTEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione prima prova - aule D22-D26", ClassData.CLASS_5F, EGiorno.MARTEDI, EOra.values());
*/

        //==============================================================================================================
        // blocco aule....
        //==============================================================================================================
        /*l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.LUNEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.MARTEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.GIOVEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.VENERDI));
*/

        // l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C19, EGiorno.VENERDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));
        // l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_5B, _E29, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA));

        //sabato in ala C solo classe quinta
        //l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_5A, _C16, EGiorno.SABATO));

        //l.add(new LessonConstraint_AnnoClasseFermaInAulaDidattica_ignoreLabs(5, _C16, EGiorno.SABATO));


        // scienze
        /*
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "scienze",
                new ClassData[]{ClassData.CLASS_1B},
                new String[]{},
                new RoomData[]{RoomData.D22, RoomData.D23, RoomData.D24, RoomData.D25, RoomData.D26, RoomData.E27, RoomData.E28,}
        ));
*/


        // INGLESE
        /*
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "inglese",
                new ClassData[]{ClassData.CLASS_1B},
                new String[]{"schiarea"},
                new RoomData[]{RoomData.B8, RoomData.B9, RoomData.B10, RoomData.B11, RoomData.B13,}
        ));

*/


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

        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _B11, EGiorno.values()));
        //l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4D, _B8, EGiorno.GIOVEDI,EOra.QUINTA,eo));

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
