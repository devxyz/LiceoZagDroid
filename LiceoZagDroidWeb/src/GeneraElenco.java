import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by stefano on 07/02/2018.
 */
public class GeneraElenco {
    private static String[] normalize(String[] ss) {
        ArrayList<String> aa = new ArrayList<>();
        for (String s : ss) {
            if (s.trim().length() > 0)
                aa.add(s.trim().toUpperCase());
        }
        return aa.toArray(new String[aa.size()]);
    }

    public static void main(String[] args) throws IOException {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidWeb/web-questionario/elenchi/ELENCHI 17.18");
        final File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".csv");
            }
        });
        ArrayList<String> linee = new ArrayList<>();

        for (File f : files) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                linee.add(line);
            }

            br.close();
        }
        ArrayList<Studente> studenti = new ArrayList<>();

        String classeCorrente = "";
        for (String linea : linee) {
            final String[] split = normalize(linea.split("[\t]"));
            if (split.length <= 2) continue;

            if (split[0].equalsIgnoreCase("N.RO")) {
                classeCorrente = split[1].trim().toUpperCase();
                continue;
            }


            if (split.length == 7) {
                Studente s = new Studente();
                studenti.add(s);
                s.matricola = split[3];
                s.cognome = split[1];
                s.nome = split[2];
                s.cf = split[4];
                s.data_luogo_nascita = split[5];
                s.classe = classeCorrente;
            } else {
                if (split.length == 6) {
                    Studente s = new Studente();
                    studenti.add(s);
                    s.matricola = "???";
                    s.cognome = split[1];
                    s.nome = split[2];
                    s.cf = split[3];
                    s.data_luogo_nascita = split[4];
                    s.classe = classeCorrente;
                } else {
                    throw new IllegalArgumentException("Invalid data " + Arrays.toString(split));
                }

            }

            Collections.sort(studenti, new Comparator<Studente>() {
                @Override
                public int compare(Studente o1, Studente o2) {
                    int i = o1.classe.compareTo(o2.classe);
                    if (i != 0) return i;
                    i = o1.cognome.compareTo(o2.cognome);
                    if (i != 0) return i;

                    i = o1.nome.compareTo(o2.nome);
                    if (i != 0) return i;


                    return 0;
                }
            });

            PrintStream out = new PrintStream(new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidWeb/web-questionario/alunni.csv"));
            out.println("$codice;$nome;$cognome;$classe");
            for (Studente studente : studenti) {
                out.println(studente.cf + ";" + studente.nome + ";" + studente.cognome + ";" + studente.classe);
            }

            //System.out.println(Arrays.toString(split));


            /*Studente s = new Studente();
            studenti.add(s);
            s.matricola = split[3];
            s.cognome = split[1];
            s.nome = split[2];
            s.cf = split[4];
            s.data_luogo_nascita = split[5];
            s.classe = classeCorrente;*/
        }


    }

    private static class Studente {
        String cf, nome, cognome, data_luogo_nascita, classe, matricola;

    }
}
