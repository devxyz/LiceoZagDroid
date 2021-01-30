package utility.mail_batch.mail_applescript_studenti;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.*;

public class GeneraGruppiBatchStudenti {
    private static PrintStream getFile(Map<String, PrintStream> map, String label) throws FileNotFoundException {
        PrintStream out = map.get(label);
        if (out == null) {
            out = new PrintStream(new File(root, "csv_gruppi/" + label + ".csv"));
            //out = new PrintStream(new File(root, "csv_gruppi/gruppi.csv"));
            out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");
            map.put(label, out);
        }
        return out;
    }

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/mail_batch/mail_applescript_studenti");

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

    public static void main(String[] args) throws Exception {


        CSVReader reader = new CSVReaderBuilder(new FileReader(new File(root, "users_studenti.csv"))).withSkipLines(1).build();

        Set<String> gruppi = new TreeSet<>();
        Map<String, PrintStream> files = new TreeMap<>();
        TreeSet<String> labelSet = new TreeSet<>();


        int i = 0;
        for (String[] riga : reader.readAll()) {
            //Anna Rita,Paliotta,annarita.paliotta@scuolesuperioridizagarolo.edu.it,Pass08975311,annaritapaliotta@gmail.com
            int col = 0;
            String nome = riga[0];
            String cognome = riga[1];
            String classe_full = riga[20];
            String[] classe_componenti = classe_full.split("_");
            String classesez = classe_componenti[0].toLowerCase();
            String scuola = classe_componenti[1].toLowerCase();

            String password = riga[3];
            String emailgsuite = riga[2];
            String emailpersonale = riga[9];

            {
                String label = "studenti";
                labelSet.add(label);
                PrintStream out = getFile(files, label);
                //out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");
                out.printf("%s@scuolesuperioridizagarolo.edu.it,%s,%s %s,MEMBER,USER\n", label, emailgsuite, nome, cognome);
            }

            //studenti scuola
            {
                String label = "studenti." + scuola + ".202021";
                labelSet.add(label);
                PrintStream out = getFile(files, "studenti.scuola");
                //out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");
                out.printf("%s@scuolesuperioridizagarolo.edu.it,%s,%s %s,MEMBER,USER\n", label, emailgsuite, nome, cognome);
            }

            //studenti scuola/classe
            {
                String label = "studenti." + classesez + "." + scuola + ".202021";
                labelSet.add(label);
                PrintStream out = getFile(files, "studenti.classe");
                //out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");
                out.printf("%s@scuolesuperioridizagarolo.edu.it,%s,%s %s,MEMBER,USER\n", label, emailgsuite, nome, cognome);
            }
/*
            //studenti scuola/classe
            if (classesez.contains("1d") || classesez.contains("2d") || classesez.contains("3d") || classesez.contains("4d")) {
                String label = "studenti." + classesez + "." + scuola + ".201920";
                labelSet.add(label);
                PrintStream out = getFile(files, "studenti.classeD_err");
                //out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");
                out.printf("%s@scuolesuperioridizagarolo.edu.it,%s,%s %s,MEMBER,USER\n", label, emailgsuite, nome, cognome);
            }*/

        }

        //account admin di controllo
        for (String s : labelSet) {
            String label = s;
            PrintStream out = getFile(files, "admin");
            //out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");
            out.printf(label + "@scuolesuperioridizagarolo.edu.it,admin@scuolesuperioridizagarolo.edu.it,admin admin,MEMBER,USER\n", label);

        }

        //account admin di controllo
        for (String s : labelSet) {
            String label = s;
            PrintStream out = getFile(files, "manuela.cenciarini");
            //out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");
            out.printf(label + "@scuolesuperioridizagarolo.edu.it,manuela.cenciarini@scuolesuperioridizagarolo.edu.it,admin admin,MEMBER,USER\n", label);

        }


        for (PrintStream out : files.values()) {
            out.close();
        }
        reader.close();
        System.out.println("CLASSI LICEO:");
        for (String s : labelSet) {
            if (s.contains(".liceo.")) {
                String[] s1 = s.split("\\.");
                System.out.println(s1[1] + "\t" + s1[2] + "\t" + s + "@scuolesuperioridizagarolo.edu.it");
            }
        }
        System.out.println("CLASSI IPIA:");
        for (String s : labelSet) {
            if (s.contains(".ipia.")) {
                String[] s1 = s.split("\\.");
                System.out.println(s1[1] + "\t" + s1[2] + "\t" + s + "@scuolesuperioridizagarolo.edu.it");
            }
        }

        generaScript(System.out,labelSet);

    }
}
