package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.Classroom;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_n19_settimana_04feb_09feb extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_n19_settimana_04feb_09feb l = new VincoliSostituzioni_n19_settimana_04feb_09feb();
        MotoreSostituzioneAule3.doTaskFromJSon(
                l,
                new File("/Users/stefano/Dropbox/DROPBOX LICEO/AS 2018.19/Orario Scolastico/orario-definitivo/DEFINITIVO_timetable_20181114222113_12.11.2018_09.06.2019.json.zip"),
                new File("/Users/stefano/Dropbox/DROPBOX LICEO/AS 2018.19/Orario Scolastico/orario-modifiche"),
                l.filtroAuleSpostamenti(), true);
    }

    private String checkAula(final BitOrarioGrigliaOrario orarioTotale, List<ClassData> classi, EGiorno giorno, EOra ora) {
        StringBuilder sb = new StringBuilder("Anomalie " + giorno + " " + ora + ": ");
        for (ClassData x : classi) {
            BitOrarioOraLezione lezioneInClasse = orarioTotale.getLezioneInClasse(ora, giorno, x);
            if (lezioneInClasse.getAula().flagAulaLaboratorioPalestra())
                sb.append("-- Classe ").append(x).append(" aula non idonea ").append(lezioneInClasse.getAula());
        }
        return sb.toString();
    }

    private static void sortByCapacityASC(List<RoomData> r) {
        Collections.sort(r, new Comparator<RoomData>() {
            @Override
            public int compare(RoomData o1, RoomData o2) {
                return Integer.compare(o1.maxStudents, o2.maxStudents);
            }
        });
    }

    private static String codeOf(EGiorno[] cc) {
        ArrayList<String> ss = new ArrayList<>();
        for (EGiorno s : cc) {
            ss.add("EGiorno." + s.name());
        }
        return "new EGiorno[]{" + ss.toString().replace("[", "").replace("]", "") + "}";
    }

    private static String codeOf(EOra[] cc) {
        ArrayList<String> ss = new ArrayList<>();
        for (EOra s : cc) {
            ss.add("EOra." + s.name());
        }
        return "new EOra[]{" + ss.toString().replace("[", "").replace("]", "") + "}";
    }

    private static String codeOf(List<LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo> cc) {
        StringBuilder sb = new StringBuilder();
        for (LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo x : cc) {
            StringBuilder giorni = new StringBuilder();


            sb.append("l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(");
            sb.append("ClassData." + x.classe.name() + ",RoomData." + x.aula.name() +
                    "," + codeOf(x.giorno.toArray(new EGiorno[x.giorno.size()])) +
                    "," + codeOf(x.ore.toArray(new EOra[x.ore.size()]))
            );
            sb.append("));\n");
        }

        return sb.toString();
    }

    public static List<LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo> vincoliClassiParallele(
            BitOrarioGrigliaOrario orarioTotale, List<ClassData> classi, EGiorno giorno, EOra ora, boolean dueOre) {
        Set<RoomData> usedRoom = new TreeSet<>();
        for (ClassData x : classi) {
            BitOrarioOraLezione lezioneInClasse = orarioTotale.getLezioneInClasse(ora, giorno, x);
            RoomData aula = lezioneInClasse.getAula();
            usedRoom.add(aula);
        }

        List<LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo> l2 = new ArrayList<LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo>();
        for (final ClassData x : classi) {
            BitOrarioOraLezione lezioneInClasse = orarioTotale.getLezioneInClasse(ora, giorno, x);
            RoomData aula = lezioneInClasse.getAula();
            if (aula == RoomData.A5_DIS || !aula.flagAulaLaboratorioPalestra()) {
                if (dueOre)
                    l2.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(x, aula, giorno, ora, ora.next()));
                else
                    l2.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(x, aula, giorno, ora));
            } else {
                List<RoomData> aule = RoomData.filter(new RoomData.RoomDataFilter() {
                    @Override
                    public boolean accept(RoomData c) {
                        return x.numberOfStudents <= c.maxStudents && !usedRoom.contains(c) && (c == RoomData.A5sharp || !c.flagAulaLaboratorioPalestra());
                    }
                });
                sortByCapacityASC(aule);
                RoomData aula1 = aule.get(0);
                if (dueOre)
                    l2.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(x, aula1, giorno, ora, ora.next()));
                else
                    l2.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(x, aula1, giorno, ora));
                usedRoom.add(aula1);
            }
        }
        return l2;
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {

        List<ClassData> terzeQuarte = classiTerzeQuarte();
        List<ClassData> primeSecondeTerzeQuarte = classiPrimeSecondeTerzeQuarte();
        List<ClassData> terzeQuarte_LATINO = classiTerzeQuarte_LATINO();
        List<ClassData> primeSeconde = classiPrimeSeconde();
        List<ClassData> primeSeconde_LATINO = classiPrimeSeconde_LATINO();

        System.out.println("/*TERZE E QUARTE - lunedi' 1° e 2° ora ITALIANO*/");
        System.out.println(codeOf(vincoliClassiParallele(orarioTotale, terzeQuarte, EGiorno.LUNEDI, EOra.PRIMA, true)));

        System.out.println("/*PRIME E SECONDE - lunedi' 4° e 5° ora ITALIANO*/");
        System.out.println(codeOf(vincoliClassiParallele(orarioTotale, primeSeconde, EGiorno.LUNEDI, EOra.QUARTA, true)));


        System.out.println("/*PRIME E SECONDE - martedi' 1° e 2° ora LATINO*/");
        System.out.println(codeOf(vincoliClassiParallele(orarioTotale, primeSeconde_LATINO, EGiorno.MARTEDI, EOra.PRIMA, true)));

        System.out.println("/*TERZE E QUARTE - martedi' 4° ora LATINO*/");
        System.out.println(codeOf(vincoliClassiParallele(orarioTotale, terzeQuarte_LATINO, EGiorno.MARTEDI, EOra.QUARTA, false)));

        System.out.println("/*PRIME, SECONDE, TERZE, QUARTE - mercoledi' 1° ora INGLESE*/");
        System.out.println(codeOf(vincoliClassiParallele(orarioTotale, primeSecondeTerzeQuarte, EGiorno.MERCOLEDI, EOra.PRIMA, false)));


        System.out.println("/*TERZE E QUARTE - sabato 1° e 2° ora FILOSOFIA*/");
        System.out.println(codeOf(vincoliClassiParallele(orarioTotale, terzeQuarte, EGiorno.SABATO, EOra.PRIMA, true)));


        System.out.flush();
        //System.out.println(checkAula(orarioTotale, primeSeconde, EGiorno.LUNEDI, EOra.QUARTA));


        //if (true) throw new IllegalArgumentException();
    }

    private List<ClassData> classiPrimeSeconde() {
        List<ClassData> primeSeconde = filterByClassNumber(1);
        primeSeconde.addAll(filterByClassNumber(2));
        return primeSeconde;
    }

    private List<ClassData> classiPrimeSeconde_LATINO() {
        List<ClassData> primeSeconde = filterByClassNumber_ORDINARIO(1);
        primeSeconde.addAll(filterByClassNumber_ORDINARIO(2));
        return primeSeconde;
    }

    private List<ClassData> classiTerzeQuarte() {
        List<ClassData> terzeQuarte = filterByClassNumber(3);
        terzeQuarte.addAll(filterByClassNumber(4));
        return terzeQuarte;
    }

    private List<ClassData> classiPrimeSecondeTerzeQuarte() {
        List<ClassData> terzeQuarte = filterByClassNumber(1);
        terzeQuarte.addAll(filterByClassNumber(2));
        terzeQuarte.addAll(filterByClassNumber(3));
        terzeQuarte.addAll(filterByClassNumber(4));
        return terzeQuarte;
    }

    private List<ClassData> classiTerzeQuarte_LATINO() {
        List<ClassData> terzeQuarte = filterByClassNumber_ORDINARIO(3);
        terzeQuarte.addAll(filterByClassNumber_ORDINARIO(4));
        return terzeQuarte;
    }

    private List<ClassData> filterByClassNumber(int classNumber) {
        return ClassData.filter(new ClassData.ClassDataFilter() {
            @Override
            public boolean accept(ClassData c) {
                return c._class == classNumber;
            }
        });
    }

    private List<ClassData> filterByClassNumber_ORDINARIO(int classNumber) {
        return ClassData.filter(new ClassData.ClassDataFilter() {
            @Override
            public boolean accept(ClassData c) {
                return c._class == classNumber && c.isOrdinario();
            }
        });
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

    public VincoliSostituzioni_n19_settimana_04feb_09feb invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {
        dal = "04/02/2019";
        al = "09/02/2019";

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

        //==============================================================================================================
        //BLOCCO SABATO
        //==============================================================================================================
        for (RoomData r : RoomData.values()) {
            if (r.location == ERoomArea.AREA_D) {
                l.add(new LessonConstraint_AulaNonDisponibile(r, EGiorno.SABATO, EOra.values()));
            }
            if (r.location == ERoomArea.AREA_E) {
                l.add(new LessonConstraint_AulaNonDisponibile(r, EGiorno.SABATO, EOra.values()));
            }

        }
        addVincoliProveParallele(l);


        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F32_SCI, EGiorno.SABATO, EOra.values()));

        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "SCHIAREA", new RoomData[]{RoomData.C20}, new EGiorno[]{EGiorno.GIOVEDI}, EOra.values()));

        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "SANTELLI", new RoomData[]{RoomData.C17}, new EGiorno[]{EGiorno.GIOVEDI}, new EOra[]{EOra.SESTA}));


        //=================== VINCOLI VARI

        //lunedi' giornata violenza donne circolare n.64

        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A5_DIS, EGiorno.SABATO, EOra.QUINTA, EOra.QUARTA, EOra.TERZA));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A7, EGiorno.SABATO, EOra.QUINTA, EOra.QUARTA, EOra.TERZA));

        orarioTotale.classeInVisitaDidattica("incontro con il Prof. Gaetano Piccolo sul tema Il Discernimento, imparare a decidere",
                ClassData.CLASS_3B, EGiorno.SABATO, EOra.QUINTA, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("incontro con il Prof. Gaetano Piccolo sul tema Il Discernimento, imparare a decidere",
                ClassData.CLASS_3C, EGiorno.SABATO, EOra.QUINTA, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("incontro con il Prof. Gaetano Piccolo sul tema Il Discernimento, imparare a decidere",
                ClassData.CLASS_3D, EGiorno.SABATO, EOra.QUINTA, EOra.QUARTA);


        return this;
    }

    private void addVincoliProveParallele(LessonConstraintContainer l) {
        /*TERZE E QUARTE - lunedi' 1° e 2° ora ITALIANO*/
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3A, RoomData.B11, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3B, RoomData.C20, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3C, RoomData.B13sharp, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3D, RoomData.D25, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3E, RoomData.A2, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3F, RoomData.C21, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3G, RoomData.B13, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4A, RoomData.D22, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4B, RoomData.A7, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4C, RoomData.D24, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4D, RoomData.E29, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4E, RoomData.C14, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));

        /*PRIME E SECONDE - lunedi' 4° e 5° ora ITALIANO*/
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1A, RoomData.A5_DIS, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1B, RoomData.A1, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1C, RoomData.D24, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1D, RoomData.D22, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1E, RoomData.C17, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1F, RoomData.B13, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1H, RoomData.B13sharp, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2A, RoomData.A5sharp, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2B, RoomData.C21, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2C, RoomData.B12, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2D, RoomData.A6, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2E, RoomData.B10, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2F, RoomData.B9, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2G, RoomData.D23, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2H, RoomData.C14, new EGiorno[]{EGiorno.LUNEDI}, new EOra[]{EOra.QUARTA, EOra.QUINTA}));

        /*PRIME E SECONDE - martedi' 1° e 2° ora LATINO*/
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1A, RoomData.A5sharp, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1C, RoomData.D24, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1E, RoomData.B11, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2A, RoomData.D25, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2C, RoomData.B12, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2E, RoomData.B9, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2G, RoomData.D22, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));

        /*TERZE E QUARTE - martedi' 4° ora LATINO*/
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3A, RoomData.B8, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.QUARTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3C, RoomData.C18, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.QUARTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3E, RoomData.E27, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.QUARTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3G, RoomData.E29, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.QUARTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4A, RoomData.B10, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.QUARTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4C, RoomData.D25, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.QUARTA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4E, RoomData.A7, new EGiorno[]{EGiorno.MARTEDI}, new EOra[]{EOra.QUARTA}));

        /*PRIME, SECONDE, TERZE, QUARTE - mercoledi' 1° ora INGLESE*/
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1A, RoomData.C17, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1B, RoomData.C16, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1C, RoomData.B9, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1D, RoomData.B13, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1E, RoomData.C19, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1F, RoomData.C20, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_1H, RoomData.D24, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2A, RoomData.C18, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2B, RoomData.B8, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2C, RoomData.B12, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2D, RoomData.A5_DIS, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2E, RoomData.B13sharp, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2F, RoomData.A1, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2G, RoomData.D22, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_2H, RoomData.A2, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3A, RoomData.E29, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3B, RoomData.A5sharp, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3C, RoomData.D25, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3D, RoomData.C14, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3E, RoomData.E28, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3F, RoomData.C21, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3G, RoomData.B10, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4A, RoomData.E27, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4B, RoomData.D26, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4C, RoomData.B11, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4D, RoomData.A6, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4E, RoomData.E30, new EGiorno[]{EGiorno.MERCOLEDI}, new EOra[]{EOra.PRIMA}));

        /*TERZE E QUARTE - sabato 1° e 2° ora FILOSOFIA*/
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3A, RoomData.A2, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3B, RoomData.B11, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3C, RoomData.B13, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3D, RoomData.B12, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3E, RoomData.A6, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3F, RoomData.A1, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_3G, RoomData.A5sharp, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4A, RoomData.C21, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4B, RoomData.B8, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4C, RoomData.B10, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4D, RoomData.A5_DIS, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData.CLASS_4E, RoomData.A7, new EGiorno[]{EGiorno.SABATO}, new EOra[]{EOra.PRIMA, EOra.SECONDA}));
    }
}
