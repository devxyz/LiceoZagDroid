package utility.pagelle;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created by stefano on 16/06/2018.
 */
public class SplitPagelle {
    public static void main(String[] args) throws IOException {

        final String root = "/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/src/utility/pagelle";


        final File[] sources = new File(root).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".pdf");
            }
        });
        TreeMap<String, ArrayList<File>> studenti = new TreeMap<>();

        for (File source : sources) {
            PdfReader reader = new PdfReader(source);
            PdfDocument p = new PdfDocument(reader);
            final int numberOfPages = p.getNumberOfPages();

            //System.out.println(numberOfPages);
            for (int i = 1; i <= numberOfPages; i = i + 2) {
                final PdfPage p1 = p.getPage(i);
                final PdfPage p2 = p.getPage(i + 1);

                ArrayList<String> testoP1 = extractText(p1);
                ArrayList<String> testoP2 = extractText(p2);
                //System.out.println(testoP2);
                String dataNascita = testoP2.get(4);
                //System.out.println(dataNascita);
                final int liceo = dataNascita.indexOf("Liceo");

                if (liceo >= 0)
                    dataNascita = dataNascita.substring(0, liceo);


                dataNascita = dataNascita.trim().replace("/", ".").replace(" ", "_");


                String nomeStudente = testoP1.get(testoP1.size() - 4).toUpperCase();
                String idStudente = nomeStudente + " (" + dataNascita + ")";


                String nomeClasse = testoP1.get(testoP1.size() - 2);
                //  System.out.println(nomeStudente + " " + nomeClasse);
                final String[] split1 = nomeClasse.split(" ");
                String sezione = split1[split1.length - 1];


                if (!studenti.containsKey(idStudente)) {
                    studenti.put(idStudente, new ArrayList<File>());
                }
                final ArrayList<File> file = studenti.get(idStudente);
                System.out.println(nomeStudente + " " + dataNascita + " " + nomeClasse);

                //System.out.println(currentText);
                File dir = new File(root, "out/" + "SEZ " + sezione + "/" + idStudente);
                dir.mkdirs();
                final File file1 = new File(dir, idStudente + "-" + nomeClasse + ".pdf");
                file.add(file1);
                PdfWriter out = new PdfWriter(file1);
                PdfDocument dout = new PdfDocument(out);
                dout.addPage(p1.copyTo(dout));
                dout.addPage(p2.copyTo(dout));
                dout.close();
            }
            p.close();


        }


        for (Map.Entry<String, ArrayList<File>> e : studenti.entrySet()) {
            final ArrayList<File> files = e.getValue();
            Set<String> cartelle = new TreeSet<>();
            for (File x : files) {
                cartelle.add(x.getParentFile().getPath().toLowerCase());
            }

            if (files.size() < 3) {
                File xroot = files.get(0).getParentFile();
                //controllo se ha cambiato sezione
                for (File x : files) {
              //      x.delete();
                }
              //  xroot.delete();
            }
            if (cartelle.size() > 1) {
                System.out.println("Lo studente " + e.getKey() + " si trova in piu' sezioni: " + cartelle);
            }
        }
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
}
