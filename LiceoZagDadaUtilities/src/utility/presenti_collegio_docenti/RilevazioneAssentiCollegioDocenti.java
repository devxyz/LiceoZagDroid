package utility.presenti_collegio_docenti;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class RilevazioneAssentiCollegioDocenti {
    static final File cartella = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/presenti_collegio_docenti");
    static final File docentiTotali = new File(cartella, "docenti_totali.csv");
    static final File docentiVotanti = new File(cartella, "docenti_risposte.csv");

    private static Set<String> read(File f) throws IOException {
        String line;
        Set<String> ris = new TreeSet<>();
        BufferedReader in = new BufferedReader(new FileReader(f));
        while ((line = in.readLine()) != null) {
            line = line.trim();
            if (line.length() > 0) {
                ris.add(line);
            }
        }

        in.close();
        return ris;
    }

    public static void main(String[] args) throws IOException {
        Set<String> totali = read(docentiTotali);
        Set<String> votanti = read(docentiVotanti);
        totali.removeAll(votanti);


        System.out.println("ASSENTI");
        for (String s : totali) {
            System.out.println(s);
        }
    }

}
