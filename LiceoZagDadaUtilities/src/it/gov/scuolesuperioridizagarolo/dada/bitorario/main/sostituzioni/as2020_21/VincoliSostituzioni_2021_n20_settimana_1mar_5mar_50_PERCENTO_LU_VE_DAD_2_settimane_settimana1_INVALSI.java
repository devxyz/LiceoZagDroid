package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
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
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;
import utility.aule2020_21.*;
import utility.copertura_classi.ControlloCoperturaClassi_COVID;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_2021_n20_settimana_1mar_5mar_50_PERCENTO_LU_VE_DAD_2_settimane_settimana1_INVALSI extends AbstractVincoliSostituzioni {

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
            "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario/");


    public static void main(String[] args) throws IOException {


        final VincoliSostituzioni_2021_n20_settimana_1mar_5mar_50_PERCENTO_LU_VE_DAD_2_settimane_settimana1_INVALSI l = new VincoliSostituzioni_2021_n20_settimana_1mar_5mar_50_PERCENTO_LU_VE_DAD_2_settimane_settimana1_INVALSI();

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        String prefix = "19-18.01-LU-VE-50percento_2settimane";
        MotoreSostituzioneAule3.doTaskFromTXT_CSV_EXCEL(
                l,
                new File(root, prefix + "/Orario Classi Completo.txt"),
                new File(root, prefix + "/Orario Tabella Globale.csv"),
                new File(root, prefix + "/docenti-extra.xlsx"),
                new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true,
                new MainParserGeneraStampeOrario.MainParserGeneraStampeOrarioListener() {
                    @Override
                    public void _2_after_ParserOrarioAllocazioneAuleTXT(BitOrarioGrigliaOrario orarioTotale) {

                        //RINOMINA
                        Map<String, String> map = new TreeMap<>();
                        map.put("CERULLO", "MARGUCCIO");
                        map.put("STRACQUALURSI", "GATTO [STRACQUALURSI*]");
                        map.put("FERRIGNO", "MASTROCESARE [FERRIGNO*]");
                        map.put("CONCA [TASSAN*]", "BORGIA C.");
                        map.put("SUPINO", "SUPINO S.");
                        renameDocente(orarioTotale, map);

                        spostaDisposizioni(orarioTotale, "CARABELLA", EGiorno.VENERDI, EOra.SECONDA, EGiorno.VENERDI, EOra.QUINTA);
                        spostaDisposizioni(orarioTotale, "SACCENTI", EGiorno.LUNEDI, EOra.QUARTA, EGiorno.LUNEDI, EOra.SETTIMA);

                        //rimuovi dispo
                        rimuoviDisposizioni(orarioTotale, "QUARESIMA", EGiorno.LUNEDI, EOra.PRIMA);
                        rimuoviDisposizioni(orarioTotale, "QUARESIMA", EGiorno.LUNEDI, EOra.SECONDA);
                        rimuoviDisposizioni(orarioTotale, "QUARESIMA", EGiorno.LUNEDI, EOra.QUINTA);
                        rimuoviDisposizioni(orarioTotale, "QUARESIMA", EGiorno.MARTEDI, EOra.PRIMA);
                        rimuoviDisposizioni(orarioTotale, "QUARESIMA", EGiorno.GIOVEDI, EOra.PRIMA);
                        rimuoviDisposizioni(orarioTotale, "QUARESIMA", EGiorno.GIOVEDI, EOra.SECONDA);
                        spostaDisposizioni(orarioTotale, "QUARESIMA", EGiorno.MERCOLEDI, EOra.QUINTA, EGiorno.MERCOLEDI, EOra.TERZA);

                        //fisse
                        //rimuoviDisposizioni(orarioTotale, "MATTEUCCI", EGiorno.MARTEDI, EOra.QUARTA);
                        spostaDisposizioni(orarioTotale, "MATTEUCCI", EGiorno.MARTEDI, EOra.QUARTA, EGiorno.LUNEDI, EOra.SETTIMA);
                        spostaDisposizioni(orarioTotale, "MATTEUCCI", EGiorno.VENERDI, EOra.QUARTA, EGiorno.VENERDI, EOra.TERZA);
                        spostaDisposizioni(orarioTotale, "DE ANGELIS", EGiorno.VENERDI, EOra.TERZA, EGiorno.GIOVEDI, EOra.QUINTA);


                        //rimuove le disposizioni di rosini che le ha cambiate
                        {
                            ArrayList<BitOrarioOraLezione> lezz = new ArrayList<>(orarioTotale.getLezioneConDocente("ROSINI"));
                            for (BitOrarioOraLezione l : lezz) {
                                if (l.isDisposizionePura()) {
                                    orarioTotale.removeLezione(l);
                                }
                            }
                        }


                        //rimuove le disposizioni di rosini che le ha cambiate
                        {
                            ArrayList<BitOrarioOraLezione> lezz = new ArrayList<>(orarioTotale.getLezioneConDocente("PAGNONI"));
                            for (BitOrarioOraLezione l : lezz) {
                                if (l.isDisposizionePura()) {
                                    orarioTotale.removeLezione(l);
                                }
                            }
                        }


                    }
                }
        );
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        ArrayList<UtilizzoClassi> assegnaule = assegnazioni();


        //QUARANTENA
        //
        //quarantena(assegnaule, ClassData.CLASS_4F, EGiorno.LUNEDI, EGiorno.MARTEDI);

        assegnaAuleAdOrario(orarioTotale, assegnaule);


        Map<EGiorno, List<RoomData>> postazioniDDiUtilizzabili = new HashMap<>();
        for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
            postazioniDDiUtilizzabili.put(g, aulePerDDI(g, orarioTotale, 20));
        }

        Random r = new Random(13);
        AssegnaPostazioniDDIOrario.assegnaVers2(orarioTotale, postazioniDDiUtilizzabili, r, 10000);


    }

    private List<RoomData> aulePerDDI(EGiorno g, final BitOrarioGrigliaOrario orarioTotale, int num) {
        //Set<RoomData> skipDDI = new TreeSet<>(Arrays.asList(RoomData.DDI_aula_A2, RoomData.DDI_aula_C7));

        List<RoomData> ris = new ArrayList<>();
        while (ris.size() < num) {
            for (RoomData r : auleLibere_UtilizzabiliDDIdocenti(g, orarioTotale)) {
                if (ris.size() >= num) return ris;
                RoomData e = RoomData.searchDDILinkedRoom(r);
                //      if (skipDDI.contains(e)) continue;
                ris.add(e);
            }
            /*RoomData[] rr = new RoomData[]{
                    RoomData.DDI_corridoio_AREA_AC};

            for (RoomData r : rr) {
                if (ris.size() >= num) return ris;
            //    if (skipDDI.contains(r)) continue;
                ris.add(r);
            }*/
        }

        return ris;
    }

    private List<RoomData> auleLibere_UtilizzabiliDDIdocenti(EGiorno g, final BitOrarioGrigliaOrario orarioTotale) {

        List<RoomData> elencoAuleUtilizzabili = RoomData.filter(new RoomData.RoomDataFilter() {
            @Override
            public boolean accept(RoomData c) {
                if (c.isDDI()) return false;
                if (c.isAulaFittizia()) return false;
                if (c == RoomData.F1) return false;
                if (c == RoomData.F2) return false;
                if (c == RoomData.A4) return false;
                if (c == RoomData.A7) return false;
                if (c == RoomData.A6) return false;//INVALSI
                if (c == RoomData.PAL1) return false;
                if (c == RoomData.PAL2) return false;

                if (c == RoomData.DDI_corridoio_AREA_C) return false;
                if (c == RoomData.DDI_corridoio_AREA_B) return false;
                if (c == RoomData.DDI_corridoio_AREA_D) return false;
                if (c == RoomData.DDI_aula_A2) return false;//vicino DSGA
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
            //elenco le aule utilizzabili per le classi
            ArrayList<RoomData> auleUtilizzabili = auleUtilizzabiliPerClassi();

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

        //tutte le giornate utilizza le stesse classi
        RipartizioneAuleClassiEngine.FilterRipartizioneAuleClassiEngine filter = new RipartizioneAuleClassiEngine.FilterRipartizioneAuleClassiEngine() {
            @Override
            public boolean accept(Classe202021 c, Aula202021 a, EGiorno giorno) {
                if (c.toClassData() == ClassData.CLASS_4H && a.toRoomData() != RoomData.E2) return false;
                if (c.toClassData() == ClassData.CLASS_5G && a.toRoomData() != RoomData.E3) return false;
                if (c.toClassData()._section.equals("B") && a.toRoomData().location == ERoomArea.AREA_D) return false;
                if (c.toClassData()._section.equals("D") && a.toRoomData().location == ERoomArea.AREA_D) return false;
                return true;
            }
        };


        ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioneMigliore =
                EngineAssegnazioneAule_V3.calcolaAssegnazioneSettimanale(num_tentativi, classiInPresenzaMap, auleNonUtilizzabiliMap, new Random(13), filter);
        if (assegnazioneMigliore == null) {
            throw new IllegalArgumentException("Assegnazione non trovata.");
        }
        //estende per tutti i giorni della settimana


        ArrayList<UtilizzoClassi> u = new ArrayList<>();
        for (AssegnazioneClasseAulaGiornaliera202021 a : assegnazioneMigliore) {
            u.addAll(a.toUtilizzoClassi());
        }
        return u;
    }

    private ArrayList<RoomData> auleUtilizzabiliPerClassi() {
        ArrayList<RoomData> auleUtilizzabili = new ArrayList<>();
        for (RoomData r : RoomData.values()) {
            if (r.isAulaFittizia()) continue;
            if (r == RoomData.F1) continue;//palestra
            if (r == RoomData.F2) continue;//scienze
            if (r == RoomData.PAL1) continue;//aule palestre
            if (r == RoomData.PAL2) continue;//aule palestre
            if (r == RoomData.A4) continue;//informatica
            if (r == RoomData.A3) continue;//fisica
            if (r == RoomData.A2) continue;//vicino DSGA
            if (r == RoomData.B6) continue;//finestra rotta
            if (r == RoomData.A6) continue;//INVALSI
            auleUtilizzabili.add(r);
        }
        return auleUtilizzabili;
    }


    @Override
    public Map<ClassData, ControlloCoperturaClassi_COVID.CoperturaClasse_COVID> reportQuarantene(BitOrarioGrigliaOrario orarioTotale) {
        List<ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID> assenti = new ArrayList<>();
        return ControlloCoperturaClassi_COVID.report(orarioTotale, assenti);
    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {


        //if (true) throw new IllegalArgumentException("STOP");
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                FilterAule.LABORATORI_SEMPRE,
        };
    }

    public VincoliSostituzioni_2021_n20_settimana_1mar_5mar_50_PERCENTO_LU_VE_DAD_2_settimane_settimana1_INVALSI invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                                                                        final LessonConstraintContainer l) {
        dal = "01/03/2021";
        al = "05/03/2021";
        progressivo = "20_INVALSI";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        return this;
    }
}
