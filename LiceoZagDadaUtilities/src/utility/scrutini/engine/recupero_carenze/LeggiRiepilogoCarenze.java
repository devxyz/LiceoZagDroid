package utility.scrutini.engine.recupero_carenze;

import utility.scrutini.engine.data.RecuperoCarenze_Intestazione;
import utility.scrutini.engine.data.ScrutiniCarenze_TerminiPerPagina;
import utility.scrutini.engine.util.ScrutiniCarenzeUtil;
import utility.scrutini.engine.data.ScrutiniCarenze_Termine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LeggiRiepilogoCarenze {


    public static final boolean PRINT1 = false;


    public static ArrayList<RecuperoCarenze_Intestazione> ricava_intestazione(ArrayList<ArrayList<ScrutiniCarenze_Termine>> termini) {

        int riga = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(termini, "alunni");
        if (riga < 0) throw new IllegalArgumentException("Riga alunni non trovata");

        ArrayList<ScrutiniCarenze_Termine> materie = termini.get(riga);

        ArrayList<RecuperoCarenze_Intestazione> ris = new ArrayList<>();
        for (ScrutiniCarenze_Termine x : materie) {
            RecuperoCarenze_Intestazione e = new RecuperoCarenze_Intestazione(x.text, x.avgX, x.avgY);
            ris.add(e);
        }

        return ris;
    }

    public static RecuperoCarenze_Intestazione _cercaIntestazioneColonnaPiuVicina(ArrayList<RecuperoCarenze_Intestazione> riga, ScrutiniCarenze_Termine termine) {
        if (riga.size() == 0) return null;
        RecuperoCarenze_Intestazione minimo = riga.get(0);
        for (RecuperoCarenze_Intestazione x : riga) {
            if (Math.abs(x.avgX - termine.avgX) < Math.abs(minimo.avgX - termine.avgX)) {
                minimo = x;
            }
        }
        return minimo;
    }

    public static ArrayList<LinkedHashMap<RecuperoCarenze_Intestazione, String>> analizzaFileCarenze(File f) throws IOException {
        List<ScrutiniCarenze_TerminiPerPagina> terminiPerPagina = ScrutiniCarenzeUtil.extractParoleByRow(f, 9.5, 10);
        ArrayList<LinkedHashMap<RecuperoCarenze_Intestazione, String>> ris = new ArrayList<>();
        for (ScrutiniCarenze_TerminiPerPagina tt : terminiPerPagina) {
            ArrayList<ArrayList<ScrutiniCarenze_Termine>> xx = tt.terminiPerRiga;
            int numRiga = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(xx, "alunni");
            if (numRiga >= 0) {
                if (PRINT1)
                    System.out.println("FUSIONE RIGA " + numRiga);
                //ScrutiniCarenzeUtil.mergeRiga(xx, numRiga, 10);
            }

            if (PRINT1)
                ScrutiniCarenzeUtil.stampaTabellaTermini(xx);

            ArrayList<RecuperoCarenze_Intestazione> intestazione = ricava_intestazione(xx);
            if (PRINT1) {
                System.out.println("INTESTAZIONE");
                for (RecuperoCarenze_Intestazione ix : intestazione) {
                    System.out.println("  > " + ix);
                }
                System.out.println();
                System.out.println();
                System.out.println();
            }

            //ricava valori

            int da = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(xx, "ALUNNI");
            int a = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(xx, "TOTALI");
            //se ci sono piu' pagine la scritta TOTALI compare solo all'ultima pagina
            if (a < 0) {
                a = xx.size();
            }
            if (da < 0) throw new IllegalArgumentException("Alunni o Totali non trovati");
            for (int i = da + 1; i < a; i++) {
                LinkedHashMap<RecuperoCarenze_Intestazione, String> studente = new LinkedHashMap<>();
                for (RecuperoCarenze_Intestazione x : intestazione) {
                    studente.put(x, "");
                }
                for (ScrutiniCarenze_Termine t : xx.get(i)) {
                    RecuperoCarenze_Intestazione ii = _cercaIntestazioneColonnaPiuVicina(intestazione, t);
                    studente.put(ii, t.text);
                }
                ris.add(studente);
            }

        }


        return ris;
    }


    public static void main(String[] args) throws IOException {

        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/scrutini/dati/1B-TRIMESTRE/RIEPILOGO CARENZE_TRIMESTRE_1B.PDF");
        {
            System.out.println("============ " + f);
            ArrayList<LinkedHashMap<RecuperoCarenze_Intestazione, String>> studenti = analizzaFileCarenze(f);
            int i = 1;
            for (LinkedHashMap<RecuperoCarenze_Intestazione, String> a : studenti) {
                System.out.println(i + ") =================================");
                i++;
                for (Map.Entry<RecuperoCarenze_Intestazione, String> e : a.entrySet()) {
                    System.out.println("  " + e.getKey().materia + " = " + e.getValue());
                }
            }

            if (true) return;
        }


    }
}
