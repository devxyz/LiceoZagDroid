package utility.cdc_maggio2020_gsuite;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import utility.scrutini.engine.util.LevenghsteinDistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CredenzialiGSuiteDocenti {
    public static class CredenzialiGSuiteDocenti_Row {
        String cognome;
        String nome;

        String cognome_nome, email;

        public CredenzialiGSuiteDocenti_Row(String nome, String cognome, String email) {
            this.cognome_nome = normalizzaCognomeNome(cognome + " " + nome);
            this.email = email;
            this.cognome = cognome;
            this.nome = nome;
        }
    }

    private List<CredenzialiGSuiteDocenti_Row> credenziali_email_nominativo = new ArrayList<>();

    public CredenzialiGSuiteDocenti(File csv) throws Throwable {
        BufferedReader rin = new BufferedReader(new FileReader(csv));
        CSVReader r = new CSVReaderBuilder(rin).withSkipLines(1).build();
        for (String[] row : r.readAll()) {
            String nome = row[0];
            String cognome = row[1];
            String email = row[2];
            credenziali_email_nominativo.add(new CredenzialiGSuiteDocenti_Row(nome, cognome, email));
        }
        r.close();
    }

    private static String normalizzaCognomeNome(String s) {
        return
                s.replace(" ", "").replace("'", "").replace("è", "e").
                        replace("à", "a").replace("ò", "o").replace("Ò", "o").
                        replace("ì", "i").replace("Ì", "i").replace("Á", "a").
                        replace("È", "e").replace("é", "e").
                        toUpperCase();
    }

    int lastSimilarity = 1000000;

    public int getLastSimilarity() {
        return lastSimilarity;
    }

    //cerca cognome_nome piu' probabile
    public CredenzialiGSuiteDocenti_Row cercaEmailNominativo(String cognome_nome) {
        cognome_nome = normalizzaCognomeNome(cognome_nome);
        CredenzialiGSuiteDocenti_Row best = null;
        int best_valore = 100000000;

        for (CredenzialiGSuiteDocenti_Row e : credenziali_email_nominativo) {
            String nominativo1 = e.cognome_nome;
            if (nominativo1.length() > cognome_nome.length())
                nominativo1 = nominativo1.substring(0, cognome_nome.length());
            int calculate = LevenghsteinDistance.calculate(nominativo1, cognome_nome);
            if (calculate < best_valore) {
                best_valore = calculate;
                best = e;
            }
        }
        lastSimilarity = best_valore;
        double v = best_valore / (double) cognome_nome.length();
        if (v > 0.5)
            return null;
        else
            //email associata
            return best;
    }


}
