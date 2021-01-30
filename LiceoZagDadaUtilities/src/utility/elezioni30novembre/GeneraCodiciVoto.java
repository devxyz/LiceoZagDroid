package utility.elezioni30novembre;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class GeneraCodiciVoto {
    public static Set<String> listaParole() throws IOException {
        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/elezioni30novembre/parole.txt");
        BufferedReader in = new BufferedReader(new FileReader(f));
        String l;
        Set<String> ris = new TreeSet<>();
        while ((l = in.readLine()) != null) {
            if (l.contains("à")) continue;
            if (l.contains("è")) continue;
            if (l.contains("é")) continue;
            if (l.contains("ì")) continue;
            if (l.contains("ò")) continue;
            if (l.contains("ù")) continue;
            if (l.length() <= 3) continue;
            ris.add(l.toUpperCase());
        }
        return ris;
    }

    public static void main(String[] args) throws IOException {

        Random r = new Random(13);
        Set<String> codiciGenerati = new TreeSet<>();

        //if (true) return;
        genera(r, "ATA", 100, codiciGenerati);
        genera(r, "DOCENTE", 200, codiciGenerati);
        genera(r, "GENITORE-MADRE", 1500, codiciGenerati);
        genera(r, "GENITORE-PADRE", 1500, codiciGenerati);
    }

    public static String genera(Random r, int lunghezza, ArrayList<String> parole) {
        StringBuilder s = new StringBuilder();
        do {
            s.setLength(0);
            do {
                int i = r.nextInt(parole.size());
                if (s.length() > 0)
                    s.append("");
                String pp = parole.get(i);
                pp = ("" + pp.charAt(0)).toUpperCase() + pp.substring(1).toLowerCase();
                s.append(pp);
            }
            while (s.length() < lunghezza);
        }
        while (s.length() > lunghezza + 2);

        return s.toString();
    }


    public static void genera(Random r, String tipo, int numero, Set<String> codiciGenerati) throws IOException {
        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/elezioni30novembre", tipo + ".txt");
        PrintStream out = new PrintStream(new FileOutputStream(f));
        Set<String> parole = listaParole();
        for (int i = 0; i < numero; i++) {
            String genera = genera(r, 15, new ArrayList<>(parole));
            if (codiciGenerati.contains(genera)) {
                i--;
                continue;
            }
            codiciGenerati.add(genera);
            out.println(genera);
        }
        out.close();
    }
}
