package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by stefano on 27/04/2018.
 */
public class MainRiepilogoQuestionarioASL_TUTOR {

    public static void main(String[] args) throws IOException {


        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/src/utility/201718-Scheda di valutazione del percorso-DOCENTE-TUTOR-3maggio.csv");
        File fout = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/src/utility/html2017-18/");
        fout.mkdirs();

        BufferedReader br = new BufferedReader(new FileReader(f));


        final List<String> header = CSVUtils.parseLine(br.readLine(), ',', '\"');

        final PrintStream out = new PrintStream(new File(fout, "tot.html"));
        out.println("<html><body>");


        int id = 1;
        String s;

        Map<String, Questionario> questionarioOrdinati = new TreeMap<>();
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (s.length() == 0) continue;


            final List<String> row = CSVUtils.parseLine(s, ',', '\"');


            Questionario q = new Questionario();
            System.out.println("X");
            for (int i = 0; i < header.size(); i++) {
                if (i >= row.size()) {
                    continue;
                }
                if (header.get(i).equalsIgnoreCase("nome")) {
                    if (row.get(i).length() > 0) q.Nome.add(row.get(i));
                } else {
                    if (header.get(i).equalsIgnoreCase("cognome")) {
                        if (row.get(i).length() > 0) q.Cognome.add(row.get(i));
                    } else {
                        if (header.get(i).equalsIgnoreCase("Scuola")) {
                            if (row.get(i).length() > 0) q.Scuola.add(row.get(i));
                        } else {
                            if (header.get(i).equalsIgnoreCase("Classe")) {
                                if (row.get(i).length() > 0) q.Classe.add(row.get(i));
                            } else {
                                if (header.get(i).equalsIgnoreCase("Sezione")) {
                                    if (row.get(i).length() > 0) q.Sezione.add(row.get(i));
                                } else {

                                        q.headers.add(header.get(i));
                                        q.data.add(row.get(i));

                                }
                            }
                        }
                    }
                }
            }
            q.normalize();

            for (int i = 0; i < q.Nome.size(); i++) {
                final String cognome = q.Cognome.get(i);
                final String nome = q.Nome.get(i);
                final String classe = q.Classe.get(i);
                final String sezione = q.Sezione.get(i);
                final String scuola = q.Scuola.get(i);


                //PrintStream out = new PrintStream(new File(fout, scuola + "-" + s3 + s4 + "-" + s1 + " " + s2 + "" + id + ".html"));
                //out.println("<html><body>");

                if (!classe.equalsIgnoreCase("V")) continue;

                String label = scuola + " " + classe + " " + sezione + " - " + cognome.toUpperCase().trim() + " " + nome.toUpperCase().trim() + " (#" + id + ")";
                questionarioOrdinati.put(label, q);


                id++;
            }


            //break;
        }

        for (Map.Entry<String, Questionario> qx : questionarioOrdinati.entrySet()) {
            //out.println("<h3> AS 2016/17 - " + cognome + " " + nome + " " + classe + sezione + " " + scuola + "</h3>");
            out.println("<h3> AS 2017/18 - " + qx.getKey().split("\\(")[0] + "</h3>");

            final Questionario q = qx.getValue();
            out.println("<table >");
            for (int j = 0; j < q.data.size(); j++) {
                if (q.data.get(j).length() >= 0) {
                    out.println("<tr>");
                    //out.println("<td style='border:'>" + q.headers.get(j) + "</td> <td>" + q.data.get(j) + "</td>");
                    out.println("<td style='border-top:2px solid black;' >" + q.headers.get(j) + ": <b style='font-size:18px'>" + q.data.get(j) + "</b></td>");


                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println("<div style='display: block; page-break-before: always;'>");

        }
        out.println("</body></html>");
        out.close();

        br.close();


    }

    static class Questionario {
        List<String> Nome = new ArrayList<>();
        List<String> Cognome = new ArrayList<>();
        List<String> Scuola = new ArrayList<>();
        List<String> Classe = new ArrayList<>();
        List<String> Sezione = new ArrayList<>();

        List<String> headers = new ArrayList<>();
        List<String> data = new ArrayList<>();

        public void normalize() {
            int max = Math.max(Nome.size(),
                    Math.max(Cognome.size(),
                            Math.max(Scuola.size(),
                                    Math.max(Classe.size(),
                                            Sezione.size()
                                    ))));

            while (Nome.size() < max) {
                Nome.add("-");
            }
            while (Cognome.size() < max) {
                Cognome.add("-");
            }
            while (Scuola.size() < max) {
                Scuola.add("-");
            }
            while (Classe.size() < max) {
                Classe.add("-");
            }
            while (Sezione.size() < max) {
                Sezione.add("-");
            }

        }
    }
}
