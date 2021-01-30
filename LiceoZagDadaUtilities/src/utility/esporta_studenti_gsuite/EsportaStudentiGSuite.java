package utility.esporta_studenti_gsuite;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EsportaStudentiGSuite {
    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/esporta_studenti_gsuite");

    public static void main(String[] args) throws IOException, CsvException {

        Set<String> skipUser = new TreeSet<>();
        {
            //email studenti già presenti
            File stud_attivi = new File(root, "utenti_attivi.csv");
            CSVReader r = new CSVReaderBuilder(new FileReader(stud_attivi)).withSkipLines(1).build();
            List<String[]> liceox = r.readAll();
            for (String[] strings : liceox) {
                skipUser.add(strings[0]);
            }
            r.close();

        }


        ArrayList<String[]> allStudents = new ArrayList<>();
        {
            File liceo = new File(root, "GSUITE_RMPS07701G.csv");
            File ipia = new File(root, "GSUITE_RMRI07701R.csv");


            CSVReader r = new CSVReaderBuilder(new FileReader(liceo)).withSkipLines(1).build();
            List<String[]> liceox = r.readAll();
            r.close();

            CSVReader r2 = new CSVReaderBuilder(new FileReader(ipia)).withSkipLines(1).build();
            List<String[]> ipiax = r2.readAll();
            r2.close();

            allStudents.addAll(liceox);
            allStudents.addAll(ipiax);
        }
        Random r = new Random(100);

        for (String[] s : allStudents) {
            String nome = s[0];
            String cognome = s[1];
            String[] split = s[5].replace("L.S.", "LS").split("-");
            String scuola = split[3];
            String classe = split[1];
            String sez = split[2];
            String indirizzo = split[3];
            String emailPersonale = s[7];
            String matricola = s[16];

            if (classe.startsWith("1") && !scuola.toUpperCase().contains("IPSIA")) {
                matricola = "0000" + (Integer.parseInt(matricola) - 6100);
            }

            String scuolaAbbrevia = scuola.contains("IPSIA") ? "IPIA" : "LICEO";
            String username = normalizza(nome) + "." + normalizza(cognome) + "." + matricola +
                    "@scuolesuperioridizagarolo.edu.it";

            //if (skipUser.contains(username))
              //  continue;

            int da = 10000000;
            int a_ = 99999999;
            String password = "P" + (da + r.nextInt(a_ - da + 1)) + "X";
            //System.out.println(nome + " " + cognome + " " + classe + sez + "(" + scuolaAbbrevia + ") " + emailPersonale);
            String unitaorg = "/studenti/" + scuolaAbbrevia.toLowerCase();

            String[] row = new String[28];
            row[0] = nome;
            row[1] = cognome;
            row[2] = username;
            row[3] = password;
            row[5] = unitaorg;
            row[8] = emailPersonale;
            row[9] = emailPersonale;
            row[16] = matricola;
            row[17] = "A";
            row[20] = classe + "" + sez + "_" + scuolaAbbrevia + "_2020/21";

            for (int i = 0; i < row.length; i++) {
                String s1 = row[i];
                if (i > 0) {
                    System.out.print("\t");
                }
                if (s1 == null) {
                    System.out.print("");
                } else {
                    System.out.print(s1);
                }
            }
            System.out.println();
        }

    }

    static String normalizza(String s) {
        return s.replace(" ", "")
                .replace("'", "")
                .replace("à", "a")
                .replace("è", "e")
                .replace("é", "e")
                .replace("ì", "i")
                .replace("ò", "o")
                .replace("ù", "u")
                .toLowerCase();
    }
}
