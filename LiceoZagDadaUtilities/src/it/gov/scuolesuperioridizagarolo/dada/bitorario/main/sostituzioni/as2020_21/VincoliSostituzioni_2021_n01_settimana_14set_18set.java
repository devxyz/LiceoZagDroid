package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
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
public class VincoliSostituzioni_2021_n01_settimana_14set_18set extends AbstractVincoliSostituzioni {
    private static Map<String, Integer> oreDisposizioneDaTogliere() {
        String s = "COCCI\t2\n" +
                "\t\n" +
                "\t\n" +
                "CERRI\t1\n" +
                "\t\n" +
                "\t\n" +
                "GENTILE\t3\n" +
                "\t\n" +
                "\t\n" +
                "CORSI\t2\n" +
                "\t\n" +
                "\t\n" +
                "MATTOZZI\t2\n" +
                "\t\n" +
                "\t\n" +
                "MATTEUCCI\t2\n" +
                "\t\n" +
                "\t\n" +
                "PANEPUCCIA\t2\n" +
                "\t\n" +
                "\t\n" +
                "FUSANI\t\n" +
                "\t\n" +
                "\t\n" +
                "BALZAROTTI\t1\n" +
                "\t\n" +
                "\t\n" +
                "MONEGO\t1\n" +
                "\t\n" +
                "\t\n" +
                "ALESSANDRONI\t2\n" +
                "\t\n" +
                "\t\n" +
                "MATTETTI\t3\n" +
                "\t\n" +
                "\t\n" +
                "CENTRACCHIO\t1\n" +
                "\t\n" +
                "\t\n" +
                "MARCHESE\t1\n" +
                "\t\n" +
                "\t\n" +
                "CENTURIONI\t2\n" +
                "\t\n" +
                "\t\n" +
                "BORRELLO\t1\n" +
                "\t\n" +
                "\t\n" +
                "FABRONI\t2\n" +
                "\t\n" +
                "\t\n" +
                "DI_ROSA\t1\n" +
                "\t\n" +
                "\t\n" +
                "CARLI\t1\n" +
                "\t\n" +
                "\t\n" +
                "PAGLIARI\t1\n" +
                "\t\n" +
                "\t\n" +
                "GUGLIELMOTTI\t\n" +
                "\t\n" +
                "\t\n" +
                "VOLPI\t\n" +
                "\t";
        String[] righe = s.split("\n");
        Map<String, Integer> ris = new TreeMap<>();
        for (String r : righe) {

            String[] split = r.split("[ \t]+");
            if (split.length <= 1) continue;
            String docente = split[0].toUpperCase().replace("_", " ");
            int ore = Integer.parseInt(split[1].toUpperCase().replace("H", ""));
            ris.put(docente, ore);
        }
        return ris;
    }


    public static void main(String[] args) throws IOException {


        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
                "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario");
        final VincoliSostituzioni_2021_n01_settimana_14set_18set l = new VincoliSostituzioni_2021_n01_settimana_14set_18set();
        Map<ClassData, RoomData> assegnaule = l.assegnaule();
        for (ClassData classData : assegnaule.keySet()) {
            System.out.println(classData.toStringDimensioni() + "\t" + assegnaule.get(classData).toStringDimensioni());
        }

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        MotoreSostituzioneAule3.doTaskFromTXT(
                l,
                new File(root, "Orario Allocazione Aule.txt"),
                null,
                new File(root, "Orario Professori Orizzontale Semplice.txt"),
                null, null, new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true);
    }

    public Map<ClassData, RoomData> assegnaule() {
        List<ClassData> biennio = ClassData.filter(new ClassData.ClassDataFilter() {
            @Override
            public boolean accept(ClassData c) {
                return c._class <= 2;
            }
        });
        List<ClassData> triennio = ClassData.filter(new ClassData.ClassDataFilter() {
            @Override
            public boolean accept(ClassData c) {
                return c._class >= 3;
            }
        });

        Map<ClassData, RoomData> ris = new TreeMap<>();
        Set<RoomData> skip = new TreeSet<>(Arrays.asList(
                RoomData.A8,
                RoomData.F1,
                RoomData.F2
        ));

        assegnaAuleImpl(biennio, ris, skip);
        assegnaAuleImpl(triennio, ris, skip);
        return ris;
    }

    public void assegnaAuleImpl(List<ClassData> classi, Map<ClassData, RoomData> ris, Set<RoomData> skip) {
        //ordina dal piu' grande al piu' piccolo
        classi.sort(new Comparator<ClassData>() {
            @Override
            public int compare(ClassData o1, ClassData o2) {
                return -Integer.compare(o1.numberOfStudents, o2.numberOfStudents);
            }
        });

        //aule ordinate per capienza
        List<RoomData> roomData = new ArrayList<>(Arrays.asList(RoomData.values()));
        roomData.sort(new Comparator<RoomData>() {
            @Override
            public int compare(RoomData o1, RoomData o2) {
                return -Integer.compare(o1.maxStudents, o2.maxStudents);
            }
        });

        System.out.println("CLASSI");
        for (ClassData classData : classi) {
            System.out.print(" " + classData.toStringDimensioni());
        }

        System.out.println("AULE");
        for (RoomData room : roomData) {
            System.out.print(" " + room.toStringDimensioni());
        }

        for (ClassData c : classi) {
            for (int i = 0, roomDataSize = roomData.size(); i < roomDataSize; i++) {
                RoomData r = roomData.get(i);
                if (r != null && skip.contains(r)) {
                    roomData.set(i, null);
                    r = null;
                }
                if (r != null) {
                    if (r.maxStudents >= c.numberOfStudents) {
                        ris.put(c, r);
                        roomData.set(i, null);
                        break;
                    } else {

                        StringBuilder sb = new StringBuilder();
                        for (RoomData rx : roomData) {
                            if (rx != null) {
                                sb.append(rx.toStringDimensioni() + " ");
                            }
                        }
                        throw new IllegalArgumentException("ERRORE CLASSE " + c.toStringDimensioni() + "\n" + sb);
                    }
                }
            }
        }
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        Map<ClassData, RoomData> assegnaule = assegnaule();
        ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(orarioTotale.getLezioni());
        for (BitOrarioOraLezione l : lezioni) {
            if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_CON_COMPRESENZA
                    || l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_SINGOLA) {
                ClassData classe = l.getClasse();
                EGiorno giorno = l.getGiorno();

                //biennio
                if (classe._class <= 2) {
                    if (giorno == EGiorno.MARTEDI || giorno == EGiorno.GIOVEDI) {
                        sostituisciConDisposizione(orarioTotale, l);
                        continue;
                    }
                }
                //triennio
                if (classe._class >= 3) {
                    if (giorno == EGiorno.LUNEDI || giorno == EGiorno.MERCOLEDI || giorno == EGiorno.VENERDI) {
                        sostituisciConDisposizione(orarioTotale, l);
                        continue;
                    }
                }
                if (l.getOra().getProgressivOra() > 4) {
                    orarioTotale.removeLezione(l);
                    continue;
                }

                orarioTotale.removeLezione(l);
                orarioTotale.addLezione(l.clonaLezioneInAltraAula(assegnaule.get(l.getClasse())));


            }
        }

        //ore disposizione da rimuovere
        Map<String, Integer> disposizioneDaTogliere = oreDisposizioneDaTogliere();
        TreeSet<String> docenti = orarioTotale.getDocenti();
        for (String s : disposizioneDaTogliere.keySet()) {
            if (!docenti.contains(s)) {
                throw new IllegalArgumentException("Docente " + s + " non trovato in " + docenti);
            }
        }

        //rimuove eventuali disposizioni
        for (Map.Entry<String, Integer> e : disposizioneDaTogliere.entrySet()) {
            String docente = e.getKey();
            Integer ore = e.getValue();
            for (int i = 1; i <= ore; i++) {
                if (!rimuoviDisposizione(orarioTotale, docente)) {
                    throw new IllegalArgumentException("Impossibile rimuovere una disposizione per " + docente);
                }
            }
        }


    }

    private static boolean rimuoviDisposizione(final BitOrarioGrigliaOrario orarioTotale, String docente) {
        List<EGiorno> eGiornos = new ArrayList<>(Arrays.asList(EGiorno.valuesGiorniDiLezione()));
        for (EGiorno giorno : eGiornos) {
            int oreLezione = 0;
            for (EOra ora : EOra.valuesOreDiLezione()) {
                BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                if (l != null)
                    oreLezione++;
            }
            if (oreLezione > 2) {
                EOra[] ore = EOra.valuesOreDiLezione();
                for (EOra ora : ore) {
                    BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                    if (l != null) {
                        if (l.isDisposizionePura()) {
                            orarioTotale.removeLezione(l);
                            // orarioTotale.addLezione(BitOrarioOraLezione.creaOraProgetto(l.getDocentePrincipale(), "-", l.getOra(), l.getGiorno()));
                            return true;
                        }
                        //se non disposizione salta al giorno seguente
                        break;
                    }
                }
                List<EOra> oreInvertite = new ArrayList<>(Arrays.asList(ore));
                Collections.reverse(oreInvertite);
                for (EOra ora : oreInvertite) {
                    BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                    if (l != null) {
                        if (l.isDisposizionePura()) {
                            orarioTotale.removeLezione(l);
                            // orarioTotale.addLezione(BitOrarioOraLezione.creaOraProgetto(l.getDocentePrincipale(), "-", l.getOra(), l.getGiorno()));
                            return true;
                        }
                        //se non disposizione salta al giorno seguente
                        break;
                    }
                }
            }
        }


        return false;

    }

    private void sostituisciConDisposizione(BitOrarioGrigliaOrario orarioTotale, BitOrarioOraLezione l) {
        orarioTotale.removeLezione(l);
        String docente;
        docente = l.getDocentePrincipale();
        if (docente != null)
            orarioTotale.addLezione(BitOrarioOraLezione.creaOraDisposizione(docente, l.getOra(), l.getGiorno()));
        docente = l.getDocenteCompresenza();
        if (docente != null)
            orarioTotale.addLezione(BitOrarioOraLezione.creaOraDisposizione(docente, l.getOra(), l.getGiorno()));
        docente = l.getDocenteSostegno();
        if (docente != null)
            orarioTotale.addLezione(BitOrarioOraLezione.creaOraDisposizione(docente, l.getOra(), l.getGiorno()));
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

    public VincoliSostituzioni_2021_n01_settimana_14set_18set invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                     final LessonConstraintContainer l) {
        dal = "14/09/2020";
        al = "18/09/2020";

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


        List<ClassData> biennio = ClassData.filter(new ClassData.ClassDataFilter() {
            @Override
            public boolean accept(ClassData c) {
                return c._class <= 2;
            }
        });
        List<ClassData> triennio = ClassData.filter(new ClassData.ClassDataFilter() {
            @Override
            public boolean accept(ClassData c) {
                return c._class > 2;
            }
        });

        //rimuove tutte le lezioni


        return this;
    }
}
