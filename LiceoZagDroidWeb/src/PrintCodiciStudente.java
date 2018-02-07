import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stefano on 05/02/2018.
 */
public class PrintCodiciStudente {
    static final File folder = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidWeb/web-questionario");

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(new File(folder, "alunni.csv")));
        String linea;
        List<Student> students = new ArrayList<>();
        while ((linea = f.readLine()) != null) {
            linea = linea.trim();
            if (linea.length() == 0) continue;

            //$codice;$nome;$cognome;$classe
            final String[] split = linea.split(";");
            Student s = new Student();
            s.codice = split[0];
            s.nome = split[1];
            s.cognome = split[2];
            s.classe = split[3];
            students.add(s);
        }
        f.close();


        Collections.sort(students);

        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(folder, "print.html"))));
        out.print("<html><body>");
        String prevClass = "";
        for (Student s : students) {
            if (!prevClass.equals(s.classe)) {
                if (prevClass.length() > 0) {
                    out.println("</table>");
                    out.print("<p style=\"page-break-after: always;\">&nbsp;</p>");
                }
                out.println("<h1>Classe " + s.classe + "</h1>");
                out.println("<table style=' width:100%; border:2px solid black; border-collapse: collapse;'>");
                prevClass = s.classe;
            }
            out.print("<tr >" +
                    "<td style='padding:10px;border-top:2px solid black;font-size:24px'>Nominativo: " + s.cognome + " " + s.nome + "<br>classe " + s.classe + " </td>" +
                    "<td style='padding:10px;border:2px solid black;font-size:20px;font-family: \"Courier New\", Courier, \"Lucida Sans Typewriter\", \"Lucida Typewriter\", monospace;'><b>Codice: " + s.codice + "</b></td>" +
                    "</tr>");
        }

        out.println("</table>");
        out.print("</body>");
        out.close();


    }

    private static class Student implements Comparable<Student> {
        String codice;
        String classe;
        String nome;
        String cognome;

        @Override
        public int compareTo(Student o) {
            int i = classe.compareTo(o.classe);
            if (i != 0) return i;

            i = cognome.compareTo(o.cognome);
            if (i != 0) return i;

            i = nome.compareTo(o.nome);
            if (i != 0) return i;

            i = codice.compareTo(o.codice);
            if (i != 0) return i;


            return 0;
        }
    }
}
