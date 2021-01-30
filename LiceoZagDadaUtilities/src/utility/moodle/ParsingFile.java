package utility.moodle;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Random;

public class ParsingFile {
    static File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/moodle/");

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(root, "studenti.txt")));
        PrintStream out = new PrintStream(new FileOutputStream(new File(root, "studenti_moodle.csv")));
        PrintStream avviso = new PrintStream(new FileOutputStream(new File(root, "registro_elettronico.csv")));
        String classe = "";
        out.println("username,password,firstname,lastname,maildisplay,course1,email");

        String line;
        int i = 1;
        Random r = new Random(13);
        while ((line = in.readLine()) != null) {
            line = line.trim();
            if (!line.startsWith("[")) {
                classe = line.toUpperCase();
                continue;
            }

            String nominativo = line.split("]")[1].trim();
            nominativo = nominativo.replace("'", "").replaceAll("[^\\p{ASCII}]", "");
            nominativo = StringUtils.stripAccents(nominativo);
            nominativo = nominativo.replaceAll("[ ]+", ".").toUpperCase();
            String password = "X" + (1000000 + r.nextInt(9999999 - 1000000 + 1));
            //N,username,password,firstname,lastname,maildisplay,course1,email
            String username = (classe + "." + nominativo).toLowerCase();
            out.printf("%s,%s,%s,%s,0,%s,%s\n",
                    username, password, classe + "_" + nominativo.toLowerCase(), nominativo, classe, "x" + i + "@127.0.0.1"
            );

            i++;
            avviso.printf("N;%s;;CREDENZIALI MOODLE;" +
                            "Si comunica che le credenziali per accedere alla piattaforma Moodle di Informatica per lo studente %s sono\\nUSERNAME:%s\\nPASSWORD:%s\n",
                    classe.toUpperCase() + " " + nominativo.replace(".", " "), nominativo.replace(".", " "), username, password);

        }

        avviso.close();
        out.close();
        in.close();
    }
}
