package utility.concorsoDirigenti.moodle;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by stefano on 27/06/2018.
 */
public class ImportaDati {
    private static boolean isPageNumber(String s) {
        return s.endsWith("/1453") && s.length() <= 9;
    }

    private static ArrayList<String> extractText(PdfPage p1) {
        ITextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
        String currentText = PdfTextExtractor.getTextFromPage(p1, strategy);

        final String[] split = currentText.split("[\n]");
        ArrayList<String> ss = new ArrayList<>();
        for (String s : split) {
            if (s.trim().length() > 0) {
                // System.out.println(s.trim());
                ss.add(s.trim());
            }
        }
        return ss;
    }


    public static boolean contieneElencoPuntato(String s) {
        final String trim = s.trim();
        return
                trim.startsWith("[a]") ||
                        trim.startsWith("[b]") ||
                        trim.startsWith("[c]") ||
                        trim.startsWith("[d]");
    }

    public static void main(String[] args) throws IOException {

        File f = new File("/Users/stefano/Downloads/Domande della Prova Preselettiva.pdf");
        File f2 = new File("/Users/stefano/Downloads/Domande della Prova Preselettiva.txt");

        PdfReader reader = new PdfReader(f);
        PdfDocument p = new PdfDocument(reader);
        final int numberOfPages = p.getNumberOfPages();

        //System.out.println(numberOfPages);
        ArrayList<String> ris = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i = i + 1) {
            final PdfPage p1 = p.getPage(i);


            ArrayList<String> testoP1 = extractText(p1);
            for (String s : testoP1) {
                if (!isPageNumber(s)) {
                    ris.add(s);
                }
            }


            if (i > 10000)
                break;
        }

        {
            BufferedWriter out = new BufferedWriter(new FileWriter(f2));
            for (String s : ris) {
                out.append(s).append("\n");

            }
            out.close();
        }

        int precArea = 0;
        int precProgressivo = 0;


        Map<Integer, Questionario> m = new TreeMap<>();
        for (int i = 1; i <= 9; i++) {
            m.put(i, new Questionario());
        }

        ArrayList<Domanda> totale = new ArrayList<>();
        int num = 0;
        Questionario tot = new Questionario();
        for (int i = 0; i < ris.size(); ) {
            num++;
            while (i < ris.size() && !ris.get(i).trim().startsWith("[RIF.")) {
                System.out.println("SKIP RIF " + ris.get(i));
                i++;
            }
            String rif = ris.get(i);
            i++;

            while (i < ris.size() && !ris.get(i).trim().startsWith("DOMANDA")) {
                System.out.println("SKIP DOMANDA " + ris.get(i));
                i++;
            }

            String titoloDomanda = ris.get(i);
            i++;
            StringBuffer testoDomanda = new StringBuffer();
            StringBuffer rispostaA = new StringBuffer();
            StringBuffer rispostaB = new StringBuffer();
            StringBuffer rispostaC = new StringBuffer();
            StringBuffer rispostaD = new StringBuffer();

            while (i < ris.size() && !ris.get(i).trim().startsWith("[a]")) {
                testoDomanda.append(ris.get(i)).append(" ");
                i++;
            }
            while (i < ris.size() && !ris.get(i).trim().startsWith("[b]")) {
                final String v = ris.get(i);
                appendRisposta(rispostaA, v);
                i++;
            }
            while (i < ris.size() && !ris.get(i).trim().startsWith("[c]")) {
                final String v = ris.get(i);
                appendRisposta(rispostaB, v);
                i++;
            }
            while (i < ris.size() && !ris.get(i).trim().startsWith("[d]")) {
                final String v = ris.get(i);
                appendRisposta(rispostaC, v);
                i++;
            }

            while (i < ris.size() && !ris.get(i).trim().startsWith("[RIF.")) {
                final String v = ris.get(i);
                appendRisposta(rispostaD, v);
                i++;
            }
            int area = Integer.parseInt(rif.split("[.]")[1].trim());
            int progressivo = Integer.parseInt(rif.split("[.]")[2].replaceAll("]", "").trim());


            System.out.println("=================");
            System.out.println("AREA " + area + " - " + rif + " - " + titoloDomanda + " n." + num);
            System.out.println(testoDomanda);
            System.out.println(" --> " + rispostaA);
            System.out.println(" --> " + rispostaB);
            System.out.println(" --> " + rispostaC);
            System.out.println(" --> " + rispostaD);

            Domanda d = new Domanda(rif, "Default per CD2018/DOMANDE_PER_AREA/AREA " + area, titoloDomanda + " " + rif, "<b>" + titoloDomanda + " - AREA " + area + ": </b>" + testoDomanda.toString(),
                    new Risposta(rispostaA.toString(), 100),
                    new Risposta(rispostaB.toString(), 0),
                    new Risposta(rispostaC.toString(), 0),
                    new Risposta(rispostaD.toString(), 0)
            );

            totale.add(d);
            tot.domande.add(d);
            m.get(area).domande.add(d);


            if (area == precArea && progressivo != precProgressivo + 1) {
                System.out.println(" >>>  ERRORE!!!!");

            }
            precArea = area;
            precProgressivo = progressivo;
        }

        Random r = new Random();

        //generazione quiz fissi casuali
        Map<Integer, Questionario> quizMultipli = new TreeMap<>();
        int xx = 1;
        Questionario q = new Questionario();
        quizMultipli.put(xx, q);
        while (totale.size() > 0) {
            if (q.domande.size()>500){
                q = new Questionario();
                quizMultipli.put(xx, q);
            }

            for (int i = 0; i < 100 && totale.size() > 0; i++) {
                int x = r.nextInt(totale.size());
                final Domanda remove = totale.remove(x);
                q.domande.add(remove.nuovaDomandaPerCategoria("Default per CD2018/DOMANDE_PER_QUIZ/QUIZ-" + xx));
            }
            xx++;
        }


        System.out.println();
        System.out.println("===========================");
        for (Map.Entry<Integer, Questionario> e : m.entrySet()) {
            System.out.println("AREA " + e.getKey() + " -> " + e.getValue().domande.size());
            BufferedWriter w = new BufferedWriter(new FileWriter(new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/data/concorsodirigenti/moodle-area_" + e.getKey() + ".xml")));
            w.append(e.getValue().toXml());
            w.close();
        }

        System.out.println();
        System.out.println("===========================");
        for (Map.Entry<Integer, Questionario> e : quizMultipli.entrySet()) {
            System.out.println("QUIZ " + e.getKey() + " -> " + e.getValue().domande.size());
            BufferedWriter w = new BufferedWriter(new FileWriter(new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/data/concorsodirigenti/moodle-quiz_" + e.getKey() + ".xml")));
            w.append(e.getValue().toXml());
            w.close();
        }

        BufferedWriter w = new BufferedWriter(new FileWriter(new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/data/concorsodirigenti/moodle-per-aree.xml")));
        w.append(tot.toXml());
        w.close();


    }

    private static void appendRisposta(StringBuffer risposta, String linea) {
        if (risposta.length() == 0 && contieneElencoPuntato(linea)) {
            risposta.append(linea.substring(3).trim()).append(" ");
        } else {
            risposta.append(linea.trim()).append(" ");
        }
    }
}
