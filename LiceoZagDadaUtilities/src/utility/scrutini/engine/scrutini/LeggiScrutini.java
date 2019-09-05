package utility.scrutini.engine.scrutini;

import utility.scrutini.engine.data.ScrutiniCarenze_TerminiPerPagina;
import utility.scrutini.engine.data.Scrutini_Intestazione;
import utility.scrutini.engine.data.ScrutiniCarenze_Termine;
import utility.scrutini.engine.util.ScrutiniCarenzeUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created by stefano on 09/07/2018.
 */
public class LeggiScrutini {

    public static final boolean PRINT1 = false;


    public static Scrutini_Intestazione _cercaIntestazioneColonnaPiuVicina(ArrayList<Scrutini_Intestazione> riga, ScrutiniCarenze_Termine termine) {
        if (riga.size() == 0) return null;
        Scrutini_Intestazione minimo = riga.get(0);
        for (Scrutini_Intestazione x : riga) {
            if (Math.abs(x.avgX - termine.avgX) < Math.abs(minimo.avgX - termine.avgX)) {
                minimo = x;
            }
        }
        return minimo;
    }

    public static ArrayList<Scrutini_Intestazione> ricava_intestazione(ArrayList<ArrayList<ScrutiniCarenze_Termine>> termini) {

        int riga = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(termini, "alunni");
        if (riga < 0) throw new IllegalArgumentException("Riga alunni non trovata");

        ArrayList<ScrutiniCarenze_Termine> materie = termini.get(riga);
        ArrayList<ScrutiniCarenze_Termine> spec_materie = termini.get(riga + 1);

        ArrayList<Scrutini_Intestazione> ris = new ArrayList<>();
        for (ScrutiniCarenze_Termine x : spec_materie) {
            ScrutiniCarenze_Termine materia = ScrutiniCarenzeUtil._cercaTermineColonnaPiuVicina(materie, x);
            Scrutini_Intestazione e = new Scrutini_Intestazione(materia.text, x.text, x.avgX, x.avgY);
            ris.add(e);
        }

        return ris;
    }

    public static void main(String[] args) throws IOException {

        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/scrutini");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toUpperCase().contains("TABELLONE") && pathname.getName().toUpperCase().endsWith(".PDF");
            }
        });

        for (File f : files) {
            System.out.println("============ " + f);
            ArrayList<LinkedHashMap<Scrutini_Intestazione, String>> studenti = analizzaFileScrutini(f);
            for (LinkedHashMap<Scrutini_Intestazione, String> a : studenti) {
                System.out.println("=================================");
                for (Map.Entry<Scrutini_Intestazione, String> e : a.entrySet()) {
                    System.out.println("  " + e.getKey().materia + "(" + e.getKey().tipologiaEnum + ") = " + e.getValue());
                }
            }

            if (true) return;
        }

/*        File report = new File(root, "scrutini.csv");
        PrintStream out = new PrintStream(report);


        out.print("classe-tot");
        out.print(";");
        out.print("classe");
        out.print(";");
        out.print("sez");
        out.print(";");
        out.print("ordinamento");
        out.print(";");
        out.print("nome");
        out.print(";");
        out.print("giudizio");
        out.println();


        for (Studente s : studenti) {
            System.out.println(s);
            out.print(s.classe);
            out.print(";");
            out.print(s.numClasse());
            out.print(";");
            out.print(s.sezioneClasse());
            out.print(";");
            out.print(s.ordinamento());
            out.print(";");
            out.print(s.nome);
            out.print(";");
            out.print(s.giudizio);
            out.println();
        }
        out.close();
*/
    }

    public static ArrayList<LinkedHashMap<Scrutini_Intestazione, String>> analizzaFileScrutini(File f) throws IOException {
        List<ScrutiniCarenze_TerminiPerPagina> terminiPagine = ScrutiniCarenzeUtil.extractParoleByRow(f);
        ArrayList<LinkedHashMap<Scrutini_Intestazione, String>> ris = new ArrayList<>();
        for (ScrutiniCarenze_TerminiPerPagina tt : terminiPagine) {
            ArrayList<ArrayList<ScrutiniCarenze_Termine>> xx = tt.terminiPerRiga;
            int numRiga = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(xx, "alunni");
            if (numRiga >= 0) {
                if (PRINT1)
                    System.out.println("FUSIONE RIGA " + numRiga);
                //ScrutiniCarenzeUtil.mergeRiga(xx, numRiga, 10);
            }

            if (PRINT1)
                ScrutiniCarenzeUtil.stampaTabellaTermini(xx);

            ArrayList<Scrutini_Intestazione> intestazione = ricava_intestazione(xx);
            if (PRINT1) {
                System.out.println("INTESTAZIONE");
                for (Scrutini_Intestazione ix : intestazione) {
                    System.out.println("  > " + ix);
                }
                System.out.println();
                System.out.println();
                System.out.println();
            }

            //ricava i valori

            int da = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(xx, "ALUNNI");
            int a = ScrutiniCarenzeUtil.cercaRigaCheIniziaPer(xx, "FIRME");
            if (a < 0) a = xx.size();//se non e' l'ultima pagina
            if (da < 0) throw new IllegalArgumentException("Alunni o forme non trovati");
            for (int i = da + 2; i < a; i++) {
                LinkedHashMap<Scrutini_Intestazione, String> studente = new LinkedHashMap<>();
                for (Scrutini_Intestazione x : intestazione) {
                    studente.put(x, "");
                }
                for (ScrutiniCarenze_Termine t : xx.get(i)) {
                    Scrutini_Intestazione ii = _cercaIntestazioneColonnaPiuVicina(intestazione, t);
                    studente.put(ii, t.text);
                }
                ris.add(studente);
            }
        }
        return ris;
    }

}
