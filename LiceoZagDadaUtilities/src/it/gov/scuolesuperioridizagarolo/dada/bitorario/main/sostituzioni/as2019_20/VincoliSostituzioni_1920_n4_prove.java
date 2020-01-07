package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2019_20;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_1920_n4_prove extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_1920_n4_prove l = new VincoliSostituzioni_1920_n4_prove();
        MotoreSostituzioneAule3.doTaskFromTXT(
                l,
                new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/orario20192020/04 provvisorio 30 settembre/Orario Allocazione Aule.txt"),
                null,
                new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/orario20192020/04 provvisorio 30 settembre/Orario Professori Orizzontale Semplice.txt"),
                null, null, new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/orario20192020/04 provvisorio 30 settembre/output"),
                l.filtroAuleSpostamenti(), false, true);
    }

    private static boolean docentiInComune(TreeMap<ClassData, TreeSet<String>> mappaDocentiPerClasse, Collection<ClassData> cc) {
        ArrayList<ClassData> cd = new ArrayList<>(cc);
        for (int i = 0; i < cd.size(); i++) {
            for (int j = i + 1; j < cd.size(); j++) {
                if (docentiInComune(mappaDocentiPerClasse, cd.get(i), cd.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean docentiInComune(TreeMap<ClassData, TreeSet<String>> mappaDocentiPerClasse, ClassData c1, ClassData c2) {
        TreeSet<String> d1 = mappaDocentiPerClasse.get(c1);
        TreeSet<String> d2 = mappaDocentiPerClasse.get(c2);
        for (String docente : d1) {
            if (d2.contains(docente))
                return true;
        }
        return false;
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {


        TreeSet<ClassData> classiFinite = new TreeSet<>();
        TreeMap<ClassData, TreeSet<String>> mappaDocentiPerClasse = new TreeMap<>();


        for (ClassData classData : ClassData.values()) {
            mappaDocentiPerClasse.put(classData, orarioTotale.getDocenti(classData));
        }

        ClassData[] values = ClassData.values();
        for (int i = 0; i < values.length; i++) {
            ClassData c1 = values[i];
            if (classiFinite.contains(c1)) continue;
            ClassData[] classData = ClassData.values();

            TreeSet<ClassData> gruppo = new TreeSet<>();
            gruppo.add(c1);
            classiFinite.add(c1);


            boolean coppia = false;
            for (int i1 = i + 1; i1 < classData.length; i1++) {

                ClassData c2 = classData[i1];
                if (classiFinite.contains(c2)) continue;

                ArrayList<ClassData> gruppoIpotetico = new ArrayList<>(gruppo);
                gruppoIpotetico.add(c2);
                boolean docentiInComune = docentiInComune(mappaDocentiPerClasse, gruppoIpotetico);


                if (!docentiInComune) {
                    gruppo.add(c2);
                    classiFinite.add(c2);
                }
            }

            if (true) {
                System.out.println("=========================================");
                System.out.println(gruppo);
                for (ClassData data : gruppo) {
                    System.out.println("  > " + data + " " + mappaDocentiPerClasse.get(data));
                }
            }
        }


        if (true) throw new IllegalArgumentException("ERRORE");
    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                FilterAule.LABORATORI_SEMPRE,
        };
    }

    public VincoliSostituzioni_1920_n4_prove invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {


        dal = "30/09/2019";
        al = "04/10/2019";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        //==============================================================================================================
        //blocco sesta ora su aule richieste
        //==============================================================================================================
        for (EGiorno giorno : EGiorno.values()) {
            for (RoomData r : RoomData.values()) {
                if (r.location == ERoomArea.AREA_C || r.location == ERoomArea.AREA_E || r.location == ERoomArea.AREA_D) {
                    //l.add(new LessonConstraint_AulaNonDisponibile(r, giorno, EOra.SESTA));
                }
            }

        }


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

        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "MILLOZZI S.", new RoomData[]{RoomData.A1, RoomData.A2, RoomData.A4_INF, RoomData.A5sharp}, EGiorno.values(), EOra.values()));

        //Studenti con sostegno
        l.add(new LessonConstraint_ClasseBloccataInArea(true, ClassData.CLASS_2E, new ERoomArea[]{ERoomArea.AREA_C, ERoomArea.AREA_F}, EGiorno.values()));
        l.add(new LessonConstraint_ClasseBloccataInArea(true, ClassData.CLASS_2A, new ERoomArea[]{ERoomArea.AREA_C, ERoomArea.AREA_F}, EGiorno.values()));


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
