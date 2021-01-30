package utility.cdc_maggio2020_gsuite;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

public class GeneraGruppi {
    public static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/cdc_maggio2020");
    public static final File file_credenziali_docenti = new File(root, "gsuite.docenti.csv");
    public static final File file_credenziali_studenti_liceo = new File(root, "gsuite.studenti.liceo.csv");
    public static final File file_credenziali_studenti_ipia = new File(root, "gsuite.studenti.ipia.csv");
    public static final File file_componenti = new File(root, "componenti_varie.csv");

    public static void main(String[] args) throws IOException, CsvException {
        CSVReader r = new CSVReaderBuilder(new FileReader(file_componenti)).withSkipLines(1).build();
        TreeSet<String> gruppi = new TreeSet<>();
        for (String[] row : r.readAll()) {
            System.out.println(Arrays.toString(row));
            System.out.flush();
            String nome = row[0].trim();
            String scuola = row[1].trim();
            String classe = row[2].trim();
            String tipologia = row[3].replace(".", "_").trim();
            String note = row[4].trim();
            String gruppo = tipologia.toLowerCase() + "." + classe.toUpperCase() + "." + scuola.toLowerCase() + ".201920";
            gruppi.add(gruppo);
        }

        for (String s : gruppi) {
            System.out.println(s);
        }

        System.out.println("=================================");
        generaScript(System.out, gruppi);
    }

    public static void generaScript(PrintStream out, Collection<String> gruppi) {
        for (String g : gruppi) {
            String x = "     AdminDirectory.Groups.insert(\n" +
                    "       {\n" +
                    "         email:\"#email#\",\n" +
                    "         description:\"#description#\",\n" +
                    "         name:\"#name#\"\n" +
                    "       }\n" +
                    "                                 );\n";
            x = x.replace("#email#", g + "@scuolesuperioridizagarolo.edu.it")
                    .replace("#name#", g)
                    .replace("#description#", g);
            out.println(x);
        }
    }
}
