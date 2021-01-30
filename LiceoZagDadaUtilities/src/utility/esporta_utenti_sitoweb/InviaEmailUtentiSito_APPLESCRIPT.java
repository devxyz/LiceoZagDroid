package utility.esporta_utenti_sitoweb;

import java.io.*;
import java.nio.file.Files;
import java.util.Random;

public class InviaEmailUtentiSito_APPLESCRIPT {
    public static String password(Random r, int size) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < size) {
            sb.append("" + r.nextInt(10));
        }
        return sb.toString();
    }

    static String testo_email = "" +
            "" +
            "";

    static String generateEmailMessage(String nome, String cognome, String email) throws IOException {
        final String oggetto = new String(Files.readAllBytes(new File(root, "EmailOggetto.txt").toPath()))
                .replace("{email}", email).replace("{nome}", nome).replace("{cognome}", cognome);
        final String messaggio = new String(Files.readAllBytes(new File(root, "EmailMessaggio.txt").toPath()))
                .replace("{email}", email).replace("{nome}", nome).replace("{cognome}", cognome);


        final File template = new File(root, "template-email.script");
        final String templateString = new String(Files.readAllBytes(template.toPath()));
        String t = templateString;
        t = t.replace("#to-name", nome + " " + cognome);
        t = t.replace("#to-address", email);
        t = t.replace("#subject", oggetto);
        t = t.replace("#content", messaggio);
        return t;
    }

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/esporta_utenti_sitoweb");

    public static void main(String[] args) throws IOException {

        final BufferedReader in = new BufferedReader(new FileReader(new File(root, "utentiATA_j2xml1590020201015100508.xml")));
        String nome = null;
        String cognome = null;
        String email = null;
        final StringBuilder script = new StringBuilder();
        String s;
        while ((s = in.readLine()) != null) {
            s = s.trim();
            if (s.startsWith("<name>")) {
                String nomecognome = normalizza1(s).trim().replace(" De ", " De_").replace(" Di ", " Di_").replace(" Del ", " Del_");

                int beginIndex = nomecognome.lastIndexOf(" ");
                cognome = nomecognome.substring(beginIndex).replace("_", " ").trim();
                nome = nomecognome.substring(0, beginIndex).replace("_", " ").trim();
            }
            if (s.startsWith("<email>")) {
                email = normalizza2(s);
            }

            if (nome != null && cognome != null && email != null) {
                script.append(generateEmailMessage(nome, cognome, email)).append("\n");
                nome = null;
                cognome = null;
                email = null;
            }
        }
        in.close();
        if (nome != null && cognome != null && email != null) {
            script.append(generateEmailMessage(nome, cognome, email)).append("\n");
            nome = null;
            cognome = null;
            email = null;
        }

        BufferedWriter out = new BufferedWriter(new FileWriter(new File(root, "genera-email.script")));
        out.write(script.toString());
        out.close();

    }

    private static String normalizza1(String s) {
        return s.replace("<name>", "").replace("</name>", "").replace("]]>", "").replace("<![CDATA[", "");
    }

    private static String normalizza2(String s) {
        return s.replace("<email>", "").replace("</email>", "").replace("]]>", "").replace("<![CDATA[", "");
    }
}
