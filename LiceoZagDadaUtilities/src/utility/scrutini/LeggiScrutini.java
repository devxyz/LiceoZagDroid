package utility.scrutini;

import com.itextpdf.kernel.geom.LineSegment;
import com.itextpdf.kernel.geom.Vector;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created by stefano on 09/07/2018.
 */
public class LeggiScrutini {

    static final boolean PRINT1 = false;

    static class Studente {
        String nome;


        @Override
        public String toString() {
            return "Studente{" +
                    "nome='" + nome + '\'' +
                    ", giudizio='" + giudizio + '\'' +
                    ", classe='" + classe + '\'' +
                    '}';
        }

        String giudizio;

        public String numClasse() {
            return classe.split("( )+")[0].split("\\^")[0];
        }

        public String sezioneClasse() {
            return classe.split("( \t)+")[0].split("\\^")[1];
        }

        public String classeSezione() {
            return classe.split("( )+")[0];
        }

        public String ordinamento() {
            return classe.substring(classe.indexOf(" "));
        }

        public Studente(String nome, String giudizio, String classe) {
            this.nome = nome;
            this.giudizio = giudizio;
            this.classe = classe;
        }

        String classe;

    }

    private static double getX(Vector v) {
        return v.get(Vector.I2);
    }

    private static double getY(Vector v) {
        return v.get(Vector.I1);
    }

    private static double getZ(Vector v) {
        return v.get(Vector.I3);
    }

    public static void printDebugLine(TextRenderInfo l) {
        LineSegment a = l.getAscentLine();
        LineSegment d = l.getDescentLine();
        Vector p1 = a.getStartPoint();
        Vector p2 = a.getEndPoint();
        Vector p3 = d.getStartPoint();
        Vector p4 = d.getEndPoint();
        System.out.println("TEXT:" + l.getText() +
                "\n  P1_xy= " + Math.round(getX(p1)) + "\t" + Math.round(getY(p1)) +
                "\n  P2_xy= " + Math.round(getX(p2)) + "\t" + Math.round(getY(p2)) +
                "\n  P3_xy= " + Math.round(getX(p3)) + "\t" + Math.round(getY(p3)) +
                "\n  P4_xy= " + Math.round(getX(p4)) + "\t" + Math.round(getY(p4))
        );

    }

    public static double getAvgX(TextRenderInfo l) {
        LineSegment a = l.getAscentLine();
        LineSegment d = l.getDescentLine();
        Vector p1 = a.getStartPoint();
        Vector p2 = a.getEndPoint();
        Vector p3 = d.getStartPoint();
        Vector p4 = d.getEndPoint();
        return (getX(p1) + getX(p2) + getX(p3) + getX(p4)) / 4.;
    }

    public static double getAvgY(TextRenderInfo l) {
        LineSegment a = l.getAscentLine();
        LineSegment d = l.getDescentLine();
        Vector p1 = a.getStartPoint();
        Vector p2 = a.getEndPoint();
        Vector p3 = d.getStartPoint();
        Vector p4 = d.getEndPoint();
        return (getY(p1) + getY(p2) + getY(p3) + getY(p4)) / 4.;
    }

    private static ArrayList<ArrayList<LeggiScrutini_Termine>> extractParoleByRow(File f) throws IOException {
        PdfReader reader = new PdfReader(f);
        PdfDocument p = new PdfDocument(reader);
        final int numberOfPages = p.getNumberOfPages();


        LeggiScrutini_TextExtractionByRow strategy = new LeggiScrutini_TextExtractionByRow();
        int conta = 0;
        for (int i = 1; i <= numberOfPages; i = i + 1) {
            final PdfPage p1 = p.getPage(i);
            PdfTextExtractor.getTextFromPage(p1, strategy);
        }
        return strategy.getParole();
    }

    private static LeggiScrutini_TextExtractionStrategy extractParole(final ArrayList<String> parole, File f) throws IOException {
        PdfReader reader = new PdfReader(f);
        PdfDocument p = new PdfDocument(reader);
        final int numberOfPages = p.getNumberOfPages();


        //System.out.println(numberOfPages);
        String classe = "";
        ArrayList<Studente> studenti = new ArrayList<>();
        LeggiScrutini_TextExtractionStrategy strategy = new LeggiScrutini_TextExtractionStrategy(parole);
        int conta = 0;
        for (int i = 1; i <= numberOfPages; i = i + 1) {
            final PdfPage p1 = p.getPage(i);
            String currentText = PdfTextExtractor.getTextFromPage(p1, strategy);
            System.out.println();
            System.out.println();
            System.out.println("Classe:" + strategy.getClasse() + " indirizzo:" + strategy.getIndirizzo());
            System.out.println("Materie:");
            for (LeggiScrutini_Materia x : strategy.getIntestazione()) {
                System.out.println("  - " + x);
            }


            if (PRINT1)
                System.out.println(currentText);
            String[] split = currentText.split("\n");
            classe = split[4];


            boolean inizia = false;

            for (int i1 = 0; i1 < split.length; i1++) {

                String s = split[i1].replace("U Ass", "").trim();

                if (s.equalsIgnoreCase("ESITO FINALE")) {
                    inizia = true;
                    continue;
                }
                if (s.equalsIgnoreCase("ESITO AMMISSIONE")) {
                    inizia = true;
                    continue;
                }
                if (s.toLowerCase().startsWith("firme")) {
                    break;
                }

                if (inizia) {
                    for (int j = 0; j <= 9; j++) {
                        s = s.replace(j + "", "#");
                    }
                    s = s.replace(" BUON ", "#");
                    s = s.replace(" BUO ", "#");
                    s = s.replace(" B ", "#");
                    s = s.replace(" S ", "#");
                    s = s.replace(" SUFF ", "#");
                    s = s.replace(" DIST ", "#");
                    s = s.replace(" NA ", "#");
                    s = s.replace(" NC ", "#");
                    s = s.replace(" OTTI ", "#");
                    s = s.replace("--", "#");
                    String old;
                    do {
                        old = s;
                        s = s.replace("# ", "#");
                        s = s.replace(" #", "#");
                        s = s.replace("##", "#");

                    } while (!s.equals(old));

                    if (PRINT1)
                        System.out.println(s);
                    String[] split1 = s.split("#");

                    Studente ss;
                    if (split1.length > 1)
                        ss = new Studente(split1[0], split1[1], classe);
                    else
                        ss = new Studente(split1[0], "SCONOSCIUTO", classe);
                    conta++;
                    studenti.add(ss);


                }

            }


            //System.out.println(currentText);

        }
        System.out.println("CLASSE " + classe + ":\n   " + conta);
        return strategy;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> parole = new ArrayList<>();

        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/scrutini");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toUpperCase().contains("TABELLONE");
            }
        });

        ArrayList<Studente> studenti = new ArrayList<>();
        for (File f : files) {
            System.out.println("============ " + f);
            ArrayList<ArrayList<LeggiScrutini_Termine>> xx = extractParoleByRow(f);

            if (true) return;
        }

/*        File report = new File(root, "scrutini.csv");
        PrintStream out = new PrintStream(report);


        out.print("classe-tot");
        out.print(";");
        out.print("classe");
        out.print(";");
        out.print("sez");
        out.print(";");
        out.print("ordinamento");
        out.print(";");
        out.print("nome");
        out.print(";");
        out.print("giudizio");
        out.println();


        for (Studente s : studenti) {
            System.out.println(s);
            out.print(s.classe);
            out.print(";");
            out.print(s.numClasse());
            out.print(";");
            out.print(s.sezioneClasse());
            out.print(";");
            out.print(s.ordinamento());
            out.print(";");
            out.print(s.nome);
            out.print(";");
            out.print(s.giudizio);
            out.println();
        }
        out.close();
*/
    }

}
