package utility.cdc_maggio2020_gsuite;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import utility.scrutini.engine.util.LevenghsteinDistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CredenzialiGSuiteStudenti {
    public static class CredenzialiGSuiteDocenti_Row {
        String cognome;
        String nome;

        String cognome_nome;
        String email;
        String classe_sezione;
        String scuola;

        public CredenzialiGSuiteDocenti_Row(String nome, String cognome, String email, String classe_sezione, String scuola) {
            this.cognome_nome = normalizzaCognomeNome(cognome + " " + nome);
            this.email = email;
            this.classe_sezione = classe_sezione;
            this.scuola = scuola;
            this.nome = nome;
            this.cognome = cognome;
        }
    }

    int lastSimilarity = 1000000;

    public int getLastSimilarity() {
        return lastSimilarity;
    }


    private List<CredenzialiGSuiteDocenti_Row> credenziali_email_nominativo = new ArrayList<>();

    public CredenzialiGSuiteStudenti(File csv) throws Throwable {
        BufferedReader rin = new BufferedReader(new FileReader(csv));
        CSVReader r = new CSVReaderBuilder(rin).withSkipLines(1).build();
        for (String[] row : r.readAll()) {
            String nome = row[0];
            String cognome = row[1];
            String email = row[2];
            String altro = row[22];//calsse_scuola_AS
            String classe = normalizzaClasse(altro.split("_")[0]);
            String scuola = normalizzaScuola(altro.split("_")[1]);
            credenziali_email_nominativo.add(new CredenzialiGSuiteDocenti_Row(nome, cognome, email, classe, scuola));
        }
        r.close();
    }


    private static String normalizzaClasse(String s) {
        return s.trim().toUpperCase();
    }

    private static String normalizzaScuola(String s) {
        return s.trim().toUpperCase();
    }

    private static String normalizzaCognomeNome(String s) {
        return
                s.replace(" ", "").replace("'", "").replace("è", "e").
                        replace("à", "a").replace("ò", "o").replace("Ò", "o").
                        replace("ì", "i").replace("Ì", "i").replace("Á", "a").
                        replace("È", "e").replace("é", "e").
                        toUpperCase();
    }

    //cerca cognome_nome piu' probabile
    public CredenzialiGSuiteDocenti_Row cercaEmailNominativo(String cognome_nome, String classe, String scuola) {
        cognome_nome = normalizzaCognomeNome(cognome_nome);
        classe = normalizzaClasse(classe);
        scuola = normalizzaScuola(scuola);
        CredenzialiGSuiteDocenti_Row best = null;
        int best_valore = 100000000;

        for (CredenzialiGSuiteDocenti_Row e : credenziali_email_nominativo) {
            if (!e.scuola.equalsIgnoreCase(scuola))
                continue;
            if (!e.classe_sezione.equalsIgnoreCase(classe))
                continue;


            String nominativo1 = e.cognome_nome;
            int calculate = LevenghsteinDistance.calculate(nominativo1, cognome_nome);
            if (calculate < best_valore) {
                best_valore = calculate;
                best = e;
            }
        }

        lastSimilarity = best_valore;
        //email associata
        return best;
    }


}
