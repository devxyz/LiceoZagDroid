package utility.emailbatch;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class InviaEmailBatch_APPLESCRIPT {
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


    static String generateEmailMessage(Map<String, String> fields) throws IOException {
        String oggetto = new String(Files.readAllBytes(new File(root, "oggetto.txt").toPath()));
        String messaggio = new String(Files.readAllBytes(new File(root, "messaggio.txt").toPath()));

        for (Map.Entry<String, String> f : fields.entrySet()) {
            oggetto = oggetto.replace(f.getKey(), f.getValue());
            messaggio = messaggio.replace(f.getKey(), f.getValue());
        }


        final File template = new File(root, "template-email.script");
        final String templateString = new String(Files.readAllBytes(template.toPath()));
        String t = templateString;
        t = t.replace("#to-name",  fields.get("{email}"));
        t = t.replace("#to-address", fields.get("{email}"));
        t = t.replace("#subject", oggetto);
        t = t.replace("#content", messaggio);
        return t;
    }

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/emailbatch/");

    public static void main(String[] args) throws IOException {

        final BufferedReader in = new BufferedReader(new FileReader(new File(root, "fields.txt")));
        Map<String, String> fields = new TreeMap<>();
        final StringBuilder script = new StringBuilder();
        String s;
        String lastField = null;
        while ((s = in.readLine()) != null) {
            System.out.println(s);
            s = s.trim();
            if (s.startsWith("=")) {
                if (fields.size() > 0) {
                    String s1 = generateEmailMessage(fields);
                    script.append(s1).append("\n");
                }
                fields.clear();
                continue;
            }
            if (s.startsWith("{")) {
                lastField = s;
                fields.put(lastField, "");
                continue;
            }

            fields.put(lastField, fields.get(lastField) + s);

        }
        if (fields.size() > 0) {
            String s1 = generateEmailMessage(fields);
            script.append(s1).append("\n");
            fields.clear();
        }

        in.close();

        BufferedWriter out = new BufferedWriter(new FileWriter(new File(root, "genera-email.script")));
        out.write(script.toString());
        out.close();

    }

}
