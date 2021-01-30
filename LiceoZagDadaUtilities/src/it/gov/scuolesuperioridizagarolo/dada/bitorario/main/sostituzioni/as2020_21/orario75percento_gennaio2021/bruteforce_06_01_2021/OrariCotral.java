package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021.bruteforce_06_01_2021;

import com.sun.source.util.Trees;
import org.apache.commons.beanutils.A;

import java.io.*;
import java.util.*;

public class OrariCotral {
    static File orari = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/orario75percento_gennaio2021/bruteforce_06_01_2021/orari_cotral.txt");

    static String INIZIO_PAGINA = "Zagarolo-Orario in vigore";
    static String LEGENDA = "Legenda:";

    static String COMUNE_PARTENZA = "#COMUNE PARTENZA";
    static String COMUNE_ARRIVO = "#COMUNE ARRIVO";
    static String TIPO_CORSA = "#TIPO CORSA";

    static String PARTENZA = "Partenza da:";
    static String ARRIVO = "Arrivo a:";
    static String LUN_VEN = "Lunedi – Venerdi:";
    static String SABATO = "Sabato:";
    static String FESTIVO = "Festivo:";
    static String STAMPATO_IL = "Stampato il";
    static String ALTRO = "Altro";

    //mappa dei valori per pagina validi
    private static Map<String, StringBuilder> mapValori() {
        Map<String, StringBuilder> m = new TreeMap<>();
        m.put(COMUNE_PARTENZA, new StringBuilder());
        m.put(COMUNE_ARRIVO, new StringBuilder());
        m.put(INIZIO_PAGINA, new StringBuilder());
        m.put(LEGENDA, new StringBuilder());
        m.put(PARTENZA, new StringBuilder());
        m.put(ARRIVO, new StringBuilder());
        m.put(LUN_VEN, new StringBuilder());
        m.put(SABATO, new StringBuilder());
        m.put(FESTIVO, new StringBuilder());
        m.put(STAMPATO_IL, new StringBuilder());
        m.put(TIPO_CORSA, new StringBuilder());
        m.put(ALTRO, new StringBuilder());
        for (int i = 1; i <= 20; i++) {
            m.put("P" + i + ".", new StringBuilder());
        }
        return m;
    }

    /**
     * cerca l'etichetta piu' appropriata (in base a come inizia la linea) oppure null se non trovata
     *
     * @param line
     * @param m
     * @return
     */
    private static String searchLabel(String line, Map<String, StringBuilder> m) {
        line = line.toUpperCase();
        for (String s : m.keySet()) {
            if (line.startsWith(s.toUpperCase(Locale.ROOT))) {
                return s;
            }
        }
        return null;
    }

    static Set<String> comuni;

    static {
        comuni = new TreeSet<>();
        comuni.add("Zagarolo");
        comuni.add("Cave");
        comuni.add("Valmontone");
        comuni.add("Palestrina");
        comuni.add("San Cesareo");
        comuni.add("Acuto");
        comuni.add("Bellegra");
        comuni.add("Colleferro");
        comuni.add("Colonna");
        comuni.add("Labico");
        comuni.add("Fiuggi");
        comuni.add("Frascati");
        comuni.add("Roma");
        comuni.add("Gallicano nel Lazio");
        comuni.add("Genazzano");
        comuni.add("Monte Compatri");
        comuni.add("Piglio");
        comuni.add("Rocca S.Stefano");
        comuni.add("Roiate");
        comuni.add("Filettino");
        comuni.add("Trevi nel Lazio");
        comuni.add("Olevano Romano");
        comuni.add("San Vito Romano");

    }


    private static String cercaComune(String destinazione) {
        destinazione = destinazione.toUpperCase(Locale.ROOT);
        for (String o : comuni) {
            o = o.toUpperCase(Locale.ROOT);
            if (destinazione.equalsIgnoreCase(o)) return o;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Tratta> tratte = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(orari));
        Map<String, StringBuilder> valori = mapValori();
        String line;
        String latestLabel = ALTRO;
        while ((line = in.readLine()) != null) {

            line = line.replace("ì", "i");
            String label = searchLabel(line, valori);

            //valori.get(ALTRO).append(line).append("\n");

            if (label == null) {
                label = latestLabel;
            } else {

                if (label.equals(PARTENZA) || label.equals(ARRIVO)) {
                    //inizia nuova scansione, salva la precedente
                    tratte.add(new Tratta(valori));

                    for (Map.Entry<String, StringBuilder> e : valori.entrySet()) {
                        if (e.getValue().length() > 0) {
                            System.out.println("<<< " + e.getKey().toUpperCase() + " >>> " + e.getValue());
                        }
                    }
                    System.out.println("==========================================================================");

                    valori = mapValori();//crea nuova mappa vuota
                }


                //rimuove la label dalla linea
                line = line.substring(label.length()).trim();

                //se partenza o arrivo individua i comuni
                if (label.equals(PARTENZA)) {
                    String da;
                    String a;
                    //parte da zagarolo
                    String[] split = line.split(ARRIVO);
                    da = " ZAGAROLO - " + split[0];
                    a = split[1];

                    append(valori, a, ARRIVO);
                    append(valori, "PARTENZA DA ZAGAROLO", TIPO_CORSA);
                    append(valori, da, PARTENZA);

                    assegnaComuni(valori, line);


                } else if (label.equals(ARRIVO)) {
                    String da;
                    String a;
                    //arrivo a zagarolo
                    String[] split = line.split(PARTENZA);
                    a = " ZAGAROLO - " + split[0];
                    da = split[1];
                    append(valori, da, PARTENZA);
                    append(valori, "ARRIVO A ZAGAROLO", TIPO_CORSA);
                    append(valori, a, ARRIVO);

                    try {
                        assegnaComuni(valori, line);
                    } catch (Throwable e) {
                        throw new IllegalArgumentException(e);
                    }
                } else {
                    append(valori, line, label);
                }

                System.out.println("## " + line);

            }
            latestLabel = label;
        }
        tratte.add(new Tratta(valori));
        for (Map.Entry<String, StringBuilder> e : valori.entrySet()) {
            if (e.getValue().length() > 0) {
                System.out.println(e.getKey().toUpperCase() + ":" + e.getValue());
            }
        }

        in.close();


        PrintStream out = new PrintStream(new File(orari.getParent(), "orari.csv"));
        out.println("da;a;tipo_corsa;orario;giornata");
        for (Tratta t : tratte) {
            for (Orario o : t.orario_FESTIVI) {
                out.printf("%s;%s;%s;%02d:%02d;%s\n",
                        t.comunePartenza, t.comuneArrivo,
                        t.tipoCorsa(), o.ora, o.minuto, "FESTIVI"
                );
            }
            for (Orario o : t.orario_SAB) {
                out.printf("%s;%s;%s;%02d:%02d;%s\n",
                        t.comunePartenza, t.comuneArrivo,
                        t.tipoCorsa(), o.ora, o.minuto, "SABATO"
                );
            }
            for (Orario o : t.orario_LUN_VEN) {
                out.printf("%s;%s;%s;%02d:%02d;%s\n",
                        t.comunePartenza, t.comuneArrivo,
                        t.tipoCorsa(), o.ora, o.minuto, "LUN_VEN"
                );
            }
        }
        out.close();

        System.out.println("ANALISI ORARI");
        {
            System.out.println();
            System.out.println("ENTRATA ORE 8.00");
            for (String c : comuni) {
                stampaDestinazioneZagarolo(tratte, c, new Orario(7, 20), new Orario(8, 10));
            }
        }
        {
            System.out.println();
            System.out.println("ENTRATA ORE 10.00");
            for (String c : comuni) {
                stampaDestinazioneZagarolo(tratte, c, new Orario(9, 20), new Orario(10, 10));
            }
        }

        {
            System.out.println();
            System.out.println("USCITA ORE 13.20");
            for (String c : comuni) {
                stampaPartenzeDaZagarolo(tratte, c, new Orario(13, 0), new Orario(13, 40));
            }
        }

        {
            System.out.println();
            System.out.println("USCITA ORE 15.00");
            for (String c : comuni) {
                stampaPartenzeDaZagarolo(tratte, c, new Orario(15, 0), new Orario(15, 40));
            }
        }
    }

    private static void assegnaComuni(Map<String, StringBuilder> valori, String line) {
        String comunePartenza = cercaComune(valori.get(PARTENZA).toString().split("-")[0].trim());
        valori.get(COMUNE_PARTENZA).append(comunePartenza);
        if (comunePartenza == null) {
            // System.out.println(valori);
            throw new IllegalArgumentException("Manca comune in " + valori.get(PARTENZA) + ">>> " + line);
        }
        String comuneArrivo = cercaComune(valori.get(ARRIVO).toString().split("-")[0].trim());
        valori.get(COMUNE_ARRIVO).append(comuneArrivo);
        if (comuneArrivo == null) {
            // System.out.println(valori);
            throw new IllegalArgumentException("Manca comune in " + valori.get(ARRIVO) + ">>> " + line);
        }
    }

    static void stampaPartenzeDaZagarolo(ArrayList<Tratta> tratte, String comuneDestinazione, Orario da, Orario a) {

        TreeSet<Orario> orariLUN_VEN = new TreeSet<>();
        TreeSet<Orario> orariSAB = new TreeSet<>();
        for (Tratta t : tratte) {
            if (
                    t.comunePartenza.equalsIgnoreCase("zagarolo")
                            &&
                            t.comuneArrivo.equalsIgnoreCase(comuneDestinazione)
            ) {

                for (Orario orario : t.orario_LUN_VEN) {
                    if (orario.between(da, a)) {
                        orariLUN_VEN.add(orario);
                    }
                }
                for (Orario orario : t.orario_LUN_VEN) {
                    if (orario.between(da, a)) {
                        orariSAB.add(orario);
                    }
                }
            }
        }

        if (orariLUN_VEN.size() == 0 || orariSAB.size() == 0) {
            System.out.println("DA ZAGAROLO VERSO " + comuneDestinazione + " (" + da + "-" + a + ")");
            System.out.println(" > LUN-VEN:" + orariLUN_VEN);
            System.out.println(" > SAB:" + orariSAB);
            System.out.println(" > CORSE MANCANTI ! -------------------------------------------");
        }

    }

    static void stampaDestinazioneZagarolo(ArrayList<Tratta> tratte, String comunePartenza, Orario da, Orario a) {

        TreeSet<Orario> orariLUN_VEN = new TreeSet<>();
        TreeSet<Orario> orariSAB = new TreeSet<>();
        for (Tratta t : tratte) {

            if (
                    t.comuneArrivo.equalsIgnoreCase("zagarolo")
                            &&
                            t.comunePartenza.equalsIgnoreCase(comunePartenza)
            ) {

                for (Orario orario : t.orario_LUN_VEN) {
                    if (orario.between(da, a)) {
                        orariLUN_VEN.add(orario);
                    }
                }
                for (Orario orario : t.orario_LUN_VEN) {
                    if (orario.between(da, a)) {
                        orariSAB.add(orario);
                    }
                }
            }
        }
        if (orariLUN_VEN.size() == 0 || orariSAB.size() == 0) {
            System.out.println("DA " + comunePartenza + " VERSO ZAGAROLO (" + da + "-" + a + ")");
            System.out.println(" > LUN-VEN:" + orariLUN_VEN);
            System.out.println(" > SAB:" + orariSAB);
            System.out.println(" > CORSE MANCANTI ! -------------------------------------------");
        }


    }

    static class Orario implements Comparable<Orario> {
        final int ora;
        final int minuto;


        Orario(int ora, int minuto) {
            this.ora = ora;
            this.minuto = minuto;
        }

        @Override
        public String toString() {
            return ora + ":" + minuto;
        }

        @Override
        public int compareTo(Orario o) {
            int i = Integer.compare(ora, o.ora);
            if (i != 0) return i;
            return Integer.compare(minuto, o.minuto);
        }

        public boolean between(Orario o1, Orario o2) {
            return o1.compareTo(this) <= 0 && this.compareTo(o2) <= 0;
        }
    }

    static class Tratta {
        final String comunePartenza, comuneArrivo;
        final Map<String, StringBuilder> map;
        final ArrayList<Orario> orario_LUN_VEN = new ArrayList<>();
        final ArrayList<Orario> orario_SAB = new ArrayList<>();
        final ArrayList<Orario> orario_FESTIVI = new ArrayList<>();

        public String tipoCorsa() {
            return map.get(TIPO_CORSA).toString();
        }

        @Override
        public String toString() {
            return "Tratta{" +
                    "comunePartenza='" + comunePartenza + '\'' +
                    ", comuneArrivo='" + comuneArrivo + '\'' +
                    ", orario_LUN_VEN=" + orario_LUN_VEN +
                    ", orario_SAB=" + orario_SAB +
                    ", orario_FESTIVI=" + orario_FESTIVI +
                    '}';
        }

        Tratta(Map<String, StringBuilder> map) {
            this.comunePartenza = map.get(COMUNE_PARTENZA).toString();
            this.comuneArrivo = map.get(COMUNE_ARRIVO).toString();
            this.map = map;
            orario_LUN_VEN.addAll(parseOrario(map.get(LUN_VEN).toString()));
            orario_SAB.addAll(parseOrario(map.get(SABATO).toString()));
            orario_FESTIVI.addAll(parseOrario(map.get(FESTIVO).toString()));

        }

        ArrayList<Orario> parseOrario(String s) {
            String[] split = s.split("[ ]+");
            ArrayList<Orario> ris = new ArrayList<>();
            for (String sx : split) {
                sx = sx.trim();
                if (sx.length() == 0) continue;
                String[] split1 = sx.split("\\(");
                String[] split2 = split1[0].split(":");
                if (split1[0].length() == 0) continue;
                Orario o = new Orario(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]));
                ris.add(o);
            }
            return ris;
        }
    }

    private static void append(Map<String, StringBuilder> valori, String line, String label) {
        StringBuilder bb = valori.get(label);
        bb.append(" ").append(line);
    }
}
