package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.ddi.AssegnaPostazioniDDIOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import utility.aule2020_21.AssegnazioneClasseAulaGiornaliera202021;
import utility.aule2020_21.EngineAssegnazioneAule_V3;
import utility.aule2020_21.UtilizzoClassi;
import utility.copertura_classi.ControlloCoperturaClassi_COVID;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_2021_n14B_IPOTESI_settimana_18gen_22gen_50_PERCENTO_LU_VE_DAD_2_settimane extends AbstractVincoliSostituzioni {

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
            "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario/");


    public static void main(String[] args) throws IOException {


        final VincoliSostituzioni_2021_n14B_IPOTESI_settimana_18gen_22gen_50_PERCENTO_LU_VE_DAD_2_settimane l = new VincoliSostituzioni_2021_n14B_IPOTESI_settimana_18gen_22gen_50_PERCENTO_LU_VE_DAD_2_settimane();

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        String prefix = "14B-18.01-LU-VE-50percento_2settimane";
        MotoreSostituzioneAule3.doTaskFromTXT_CSV(
                l,
                new File(root, prefix + "/Orario Classi Completo.txt"),
                new File(root, prefix + "/Orario Tabella Globale.csv"),
                new File(root, prefix + "/Orario Sostegno Orizzontale Semplice.txt"),
                null, new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true);
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        Map<String, String> map = new TreeMap<>();
        map.put("CERULLO", "MARGUCCIO");
        //map.put("MILLOZZI", "MILLOZZI S.");
        //map.put("A011_PART TIME", "*SUPPL A011");
        //map.put("A011_RESIDUE/CEDUTE*", "BIONDI (provvisoria)");//elisa biondi - completa con tivoli - annuale
        //map.put("A019_PART_TIME_3E*", "*SUPPL1 A019");
        //map.put("A019_PART_TIME_4G*", "*SUPPL2 A019");
        //map.put("A027_PART TIME", "CARA");
        //map.put("A048_RESIDUE", "*SUPPL A048");
/*        map.put("BIONDI", "BIONDI E.");
        map.put("FERRIGNO", "MASTROCESARE [FERRIGNO*]");
        map.put("CERULLO", "MARGUCCIO [CERULLO*]");
        //map.put("QUARESIMA", "GATTO [QUARESIMA*]");
        map.put("A050_PART TIME", "LEGGERI");
        map.put("EX_BIONDI", "BIONDI M.");
        //map.put("EX_LATINI", "MILLOZZI C.");
        //map.put("EX_LIBERATI", "TURRIZIANI");//valeria.turriziani@scuolesuperioridizagarolo.edu.it - annuale
        map.put("EX_MARTINI", "SABUCCI");
        //map.put("FERRIGNO", "MASTROCESARE [FERRIGNO*]");
        //map.put("MARCHESE>SUPPL", "MARCHESE**");
        //map.put("TASSAN>SUPPL", "TASSAN**");

 */
        renameDocente(orarioTotale, map);
        ArrayList<UtilizzoClassi> assegnaule = assegnazioni();

        //aggiunge aule non assegnate
/*        EGiorno[] giorni = new EGiorno[]{
                EGiorno.LUNEDI,
                EGiorno.MARTEDI,
                EGiorno.MERCOLEDI,
        };
        for (EGiorno g : giorni) {
            for (ClassData c : ClassData.values()) {
                UtilizzoClassi e = new UtilizzoClassi(RoomData.USCITA_DIDATTICA, c, g);
                assegnaule.add(e);
            }
        }
*/
        assegnaAuleAdOrario(orarioTotale, assegnaule);


        Map<EGiorno, List<RoomData>> postazioniDDiUtilizzabili = new HashMap<>();
        for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
            postazioniDDiUtilizzabili.put(g, aulePerDDI(g, orarioTotale, 20));
        }
        //
        //System.out.println(auleLibere(EGiorno.LUNEDI, orarioTotale));
        //System.out.println(aulePerDDI(EGiorno.LUNEDI, orarioTotale));
        //if (true) throw new IllegalArgumentException("STOP");
/*
        TreeSet<String> consiglioDiClasse_docente = orarioTotale.getConsiglioDiClasse_Docente(ClassData.CLASS_4G);
        for (String d : consiglioDiClasse_docente) {
            BitOrarioOraLezione lezioneConDocente = orarioTotale.getLezioneConDocente(d, EGiorno.MARTEDI, EOra.SETTIMA);
            if (lezioneConDocente != null)
                System.out.println(d + ": " + lezioneConDocente);
            lezioneConDocente = orarioTotale.getLezioneConDocente(d, EGiorno.MARTEDI, EOra.OTTAVA);
            if (lezioneConDocente != null)
                System.out.println(d + ": " + lezioneConDocente);
        }
        //if (true) throw new IllegalArgumentException("????");
*/
        AssegnaPostazioniDDIOrario.assegna(orarioTotale, postazioniDDiUtilizzabili);

    }

    private List<RoomData> aulePerDDI(EGiorno g, final BitOrarioGrigliaOrario orarioTotale, int num) {

        List<RoomData> ris = new ArrayList<>();
        while (ris.size() < num) {
            for (RoomData r : auleLibere(g, orarioTotale)) {
                if (ris.size() >= num) return ris;
                ris.add(RoomData.searchDDILinkedRoom(r));
            }
            RoomData[] rr = new RoomData[]{
                    RoomData.DDI_corridoio_AREA_AC,
                    RoomData.DDI_corridoio_AREA_AC,
                    RoomData.DDI_corridoio_AREA_AC,
                    RoomData.DDI_corridoio_AREA_B,
                    RoomData.DDI_corridoio_AREA_C,
                    RoomData.DDI_corridoio_AREA_D};

            for (RoomData r : rr) {
                if (ris.size() >= num) return ris;
                ris.add(r);
            }
        }

        return ris;
    }

    private List<RoomData> auleLibere(EGiorno g, final BitOrarioGrigliaOrario orarioTotale) {

        List<RoomData> elencoAuleUtilizzabili = RoomData.filter(new RoomData.RoomDataFilter() {
            @Override
            public boolean accept(RoomData c) {
                if (c.isDDI()) return false;
                if (c.isAulaFittizia()) return false;
                if (c == RoomData.F1) return false;
                if (c == RoomData.F2) return false;
                if (c == RoomData.A4) return false;
                if (c == RoomData.A7) return false;
                if (c == RoomData.PAL1) return false;
                if (c == RoomData.PAL2) return false;
                return true;
            }
        });
        List<RoomData> ris = new ArrayList<>(elencoAuleUtilizzabili);

        //ci tolgo quelle utilizzate
        for (EOra c : EOra.valuesOreDiLezione()) {
            ris.removeAll(orarioTotale.getAuleOccupate(c, g));
        }

        return ris;
    }

    //indice settimana 1 oppure 2
    private Set<ClassData> classiInPresenza(String tabellone, EGiorno giorno, int indexSettimana) {
        String[] righe = tabellone.split("\n");
        Set<ClassData> ris = new TreeSet<>();
        for (String s : righe) {
            String[] valori = s.split("\t");
            String classe = valori[0].trim();
            if (classe.equalsIgnoreCase("-")) continue;//salta intestazione
            ClassData c = ClassData.search(classe);
            //cerca giorno
            int indexColonna;
            if (indexSettimana == 1) {
                indexColonna = 2;
            } else {
                indexColonna = 8;
            }
            EGiorno g = EGiorno.LUNEDI;
            while (g != giorno) {
                indexColonna++;
                g = g.nextGiornoLezione();
            }
            String v = valori[indexColonna];
            if (!v.equalsIgnoreCase("DAD")) {
                ris.add(c);
            }
            if (v.equalsIgnoreCase("|")) {
                throw new IllegalArgumentException("Errore allineamento per " + giorno + "(" + g + ")");
            }
        }
        return ris;
    }

    private ArrayList<UtilizzoClassi> assegnazioni() {
        String tabellone =
                "-\tLU\tMA\tME\tGIO\tVE\t\tLU\tMA\tME\tGIO\tVE\n" +
                        " 1A\t|\tP08\tP10\tP08\tP08\tP10\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 1B\t|\tP08\tP10\tP10\tP08\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 1C\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP10\tP08\tP10\tP08\tP10\n" +
                        " 1D\t|\tP10\tP08\tP08\tP10\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 1E\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP10\tP10\tP10\tP08\n" +
                        " 1F\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP10\tP08\tP08\tP10\tP08\n" +
                        " 2A\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP08\tP08\tP08\tP08\n" +
                        " 2B\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP10\tP10\tP10\tP08\n" +
                        " 2C\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP08\tP08\tP08\tP10\n" +
                        " 2D\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP10\tP08\tP08\tP10\n" +
                        " 2E\t|\tP08\tP08\tP08\tP08\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 2F\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP10\tP08\tP08\tP10\tP10\n" +
                        " 2G\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP10\tP08\tP10\tP08\n" +
                        " 2H\t|\tP10\tP08\tP08\tP10\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 3A\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP10\tP10\tP08\tP08\n" +
                        " 3B\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP10\tP10\tP08\tP08\tP08\n" +
                        " 3C\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP08\tP10\tP10\tP10\n" +
                        " 3D\t|\tP08\tP10\tP10\tP08\tP10\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 3E\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP10\tP08\tP08\tP10\tP10\n" +
                        " 3F\t|\tP08\tP08\tP10\tP10\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 3H\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP08\tP10\tP08\tP08\n" +
                        " 4A\t|\tP10\tP08\tP08\tP08\tP10\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 4B\t|\tP08\tP08\tP08\tP08\tP10\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 4C\t|\tP08\tP08\tP08\tP10\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 4D\t|\tP10\tP08\tP10\tP10\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 4E\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP10\tP08\tP08\tP08\tP10\n" +
                        " 4F\t|\tP10\tP10\tP08\tP10\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 4G\t|\tP10\tP08\tP10\tP08\tP10\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 4H\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP10\tP10\tP08\tP08\n" +
                        " 5A\t|\tP08\tP10\tP08\tP10\tP10\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 5B\t|\tP10\tP08\tP08\tP08\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 5C\t|\tP08\tP10\tP10\tP08\tP10\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 5D\t|\tP08\tP10\tP08\tP08\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 5E\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP08\tP08\tP08\tP08\tP08\n" +
                        " 5F\t|\tP08\tP10\tP10\tP08\tP08\t|\tDAD\tDAD\tDAD\tDAD\tDAD\n" +
                        " 5G\t|\tDAD\tDAD\tDAD\tDAD\tDAD\t|\tP10\tP08\tP08\tP08\tP08";

        int indexSettimana = 1;


        int num_tentativi = 1000;

        //SELEZIONA LE AULE DA UTILIZZARE: seleziona quelle piu' piccole fino a lasciare attive 36/2 aule (quelle corrispondenti alle classi)
        Set<RoomData> auleNonUtilizzabili = new TreeSet<>();
        {
            //elenco le aule utilizzabili
            ArrayList<RoomData> auleUtilizzabili = new ArrayList<>();
            for (RoomData r : RoomData.values()) {
                if (r.isAulaFittizia()) continue;
                if (r == RoomData.F1) continue;//palestra
                if (r == RoomData.F2) continue;//scienze
                if (r == RoomData.PAL1) continue;//aule palestre
                if (r == RoomData.PAL2) continue;//aule palestre
                if (r == RoomData.A4) continue;//informatica
                if (r == RoomData.A3) continue;//fisica
                auleUtilizzabili.add(r);
            }

            if (auleUtilizzabili.size() < 18) throw new IllegalArgumentException("Aule insuff (18)");

            //ordino dalla piu' grande alla piu' piccola
            Collections.sort(auleUtilizzabili, new Comparator<RoomData>() {
                @Override
                public int compare(RoomData o1, RoomData o2) {
                    return -Integer.compare(o1.maxStudents, o2.maxStudents);
                }
            });

            //rimuovo le ultime aule, lasciando le piu' grandi
            while (auleUtilizzabili.size() > 18) {
                //rimuove l'ultima
                auleUtilizzabili.remove(auleUtilizzabili.size() - 1);
            }

            auleNonUtilizzabili.addAll(Arrays.asList(RoomData.values()));
            auleNonUtilizzabili.removeAll(auleUtilizzabili);
        }

        //=====================
        //=====================
        //=====================
        Random r = new Random(13);

        Map<EGiorno, ArrayList<ClassData>> classiInPresenzaMap = new TreeMap<>();
        EGiorno[] giorni = {EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI, EGiorno.GIOVEDI, EGiorno.VENERDI};
        Map<EGiorno, Set<RoomData>> auleNonUtilizzabiliMap = new TreeMap<>();
        for (EGiorno g : giorni) {
            classiInPresenzaMap.put(g, new ArrayList<>(classiInPresenza(tabellone, g, indexSettimana)));
            auleNonUtilizzabiliMap.put(g, auleNonUtilizzabili);
        }


        ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioneMigliore =
                EngineAssegnazioneAule_V3.
                        calcolaAssegnazioneSettimanale(num_tentativi, classiInPresenzaMap, auleNonUtilizzabiliMap, r);
        if (assegnazioneMigliore == null) {
            throw new IllegalArgumentException("Assegnazione non trovata.");
        }


        ArrayList<UtilizzoClassi> u = new ArrayList<>();
        for (AssegnazioneClasseAulaGiornaliera202021 a : assegnazioneMigliore) {
            u.addAll(a.toUtilizzoClassi());
        }
        return u;
    }


    @Override
    public Map<ClassData, ControlloCoperturaClassi_COVID.CoperturaClasse_COVID> reportQuarantene(BitOrarioGrigliaOrario orarioTotale) {
        List<ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID> assenti = new ArrayList<>();
        return ControlloCoperturaClassi_COVID.report(orarioTotale, assenti);
    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {

/*
        if (true)
            throw new IllegalArgumentException("STOP");
*/
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                FilterAule.LABORATORI_SEMPRE,
        };
    }

    public VincoliSostituzioni_2021_n14B_IPOTESI_settimana_18gen_22gen_50_PERCENTO_LU_VE_DAD_2_settimane invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                                                                final LessonConstraintContainer l) {
        dal = "18/01/2021";
        al = "22/01/2021";
        progressivo = "14B";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        return this;
    }
}
