package utility.registroclasse;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by stefano on 06/07/2018.
 */
public class LeggiRegistroClasse {
    static final boolean PRINT1 = false;
    static final boolean PRINT2 = false;

    public static void main(String[] args) throws IOException {
        File fout = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/src/utility/registroclasse/RegistroDiClasseTot.csv");

        final ArrayList<String> parole = new ArrayList<>();


        File folder1 = new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/registri di classe/liceo");
        File folder2 = new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/registri di classe/ipia");
        final FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".pdf");
            }
        };
        final File[] files1 = folder1.listFiles(filter);
        for (File f : files1) {
            System.out.println("Processing " + f);
            extractParole(parole, f);
        }

        final File[] files2 = folder2.listFiles(filter);
        for (File f : files2) {
            System.out.println("Processing " + f);
            extractParole(parole, f);
        }


        ArrayList<Assenze> aa = new ArrayList<>();


        /*for (Iterator<String> iterator = parole.iterator(); iterator.hasNext(); ) {
            String pp = iterator.next();
            if (
                    pp.startsWith("ASSENTI:*")
                            || pp.startsWith("CLASSE*")
                            || pp.startsWith("GIORNO*")
                //||pp.startsWith("PERIODO DAL:")
                    ) {


            } else {
                iterator.remove();
            }
        }*/


        String classe = "";
        String giornoSettimana = "";
        String giornoData = "";
        String meseData = "";
        String scuola = "";
        for (String s : parole) {
            if (s.startsWith("CLASSE*")) {
                classe = s.replace("CLASSE*", "").replaceAll("[ ]+", " ");
                if (s.contains("SCIENTIFICO")) {
                    scuola = "LICEO";
                } else {
                    scuola = "IPIA";
                }
                continue;
            }
            if (s.startsWith("GIORNO*")) {
                final String[] split = s.split("[*]");
                giornoSettimana = split[1];
                final String[] split1 = split[2].trim().split("[ ]+");
                giornoData = split1[0];
                meseData = split1[1];
                continue;
            }
            if (s.startsWith("ASSENTI:*")) {
                final String[] studenti = s.replace("ASSENTI:*", "").split("[,]");
                for (String stud : studenti) {
                    TipologiaAssenze tipo = TipologiaAssenze.ASSENZA;
                    if (stud.contains("[R")) {
                        tipo = TipologiaAssenze.ENTRATA;
                    } else if (stud.contains("[U")) {
                        tipo = TipologiaAssenze.USCITA;
                    }
                    final String[] xx = stud.split("\\[");
                    final String ss = xx[0].trim();
                    String nota = "";
                    if (xx.length > 1) {
                        nota = "[" + xx[1];
                    }
                    final Assenze e = new Assenze(classe, scuola, ss, nota, giornoData, meseData, giornoSettimana, tipo);
                    aa.add(e);
                    if (PRINT2) {
                        System.out.println(e);
                    }


                }
            }
        }

        PrintStream pout = new PrintStream(new BufferedOutputStream(new FileOutputStream(fout)));
        pout.println("classe;scuola;studente;note;data;mese;giorno-settimana;tipologia-assenza");
        for (Assenze e : aa) {
            pout.println(e.classe + ";" + e.scuola + ";" + e.studente + ";" + e.annotazioni + ";" + e.giornoData + ";" + e.meseData + ";" + e.giornosettimana + ";" + e.tipologia);
        }
        pout.close();

    }

    private static void extractParole(final ArrayList<String> parole, File f) throws IOException {
        PdfReader reader = new PdfReader(f);
        PdfDocument p = new PdfDocument(reader);
        final int numberOfPages = p.getNumberOfPages();


        //System.out.println(numberOfPages);

        for (int i = 1; i <= numberOfPages; i = i + 1) {

            final PdfPage p1 = p.getPage(i);
            ITextExtractionStrategy strategy = new SimpleTextExtractionStrategy() {
                @Override
                public void eventOccurred(IEventData data, EventType type) {
                    super.eventOccurred(data, type);

                    if (type == EventType.RENDER_TEXT) {
                        TextRenderInfo renderInfo = (TextRenderInfo) data;

                        //      System.out.println(data);
                        //    System.out.println(renderInfo.getText());
                        // System.out.println(renderInfo.getPdfString());
                        //  System.out.println("=================================");
                        final String text = renderInfo.getText().trim();
                        if (text.contains("SCIENTIFICO")
                                || text.contains("OPERATORE")
                                || text.contains("Manutenzione ed")
                                ) {
                            parole.add("CLASSE*" + text);

                        } else {
                            final String lunedì = "Lunedì";
                            final String martedì = "Martedì";
                            final String mercoledì = "Mercoledì";
                            final String giovedì = "Giovedì";
                            final String venerdì = "Venerdì";
                            final String sabato = "Sabato";
                            final String domenica = "Domenica";
                            if (text.trim().equals(lunedì)
                                    || text.trim().equals(martedì)
                                    || text.trim().equals(mercoledì)
                                    || text.trim().equals(giovedì)
                                    || text.trim().equals(venerdì)
                                    || text.trim().equals(sabato)
                                    || text.trim().equals(domenica)
                                    ) {
                                parole.add("GIORNO*" +
                                        text.replace(lunedì, "1-" + lunedì).
                                                replace(martedì, "2-" + martedì).
                                                replace(mercoledì, "3-" + mercoledì).
                                                replace(giovedì, "4-" + giovedì).
                                                replace(venerdì, "5-" + venerdì).
                                                replace(sabato, "6-" + sabato).
                                                replace(domenica, "7-" + domenica).
                                                replace("ì", "i'") + "*");
                            } else if (
                                    text.endsWith(":")
                                            || text.contains("A.S.")
                                            || parole.size() == 0
                                            || text.startsWith("Pagina")
                                            || text.startsWith("Periodo")

                                    ) {
                                parole.add(text.toUpperCase() + "*");

                            } else {
                                parole.set(parole.size() - 1, parole.get(parole.size() - 1) + " " + text);
                                //parole.add("  " + text);
                            }
                        }
                    }


                }
            };
            String currentText = PdfTextExtractor.getTextFromPage(p1, strategy);

            if (PRINT1) {
                System.out.println("=======================");
                for (String s : parole) {
                    System.out.println(s);
                }
            }
            //System.out.println(currentText);

        }
    }

    static enum TipologiaAssenze {
        ENTRATA, USCITA, ASSENZA
    }

    static class Assenze {
        public final String classe;
        public final String scuola;
        public final String studente;
        public final String annotazioni;
        public final String giornoData;
        public final String meseData;
        public final String giornosettimana;
        public final TipologiaAssenze tipologia;

        Assenze(String classe, String scuola, String studente, String annotazioni, String giorno, String mese, String giornosettimana, TipologiaAssenze tipologia) {
            this.classe = classe;
            this.scuola = scuola;
            this.studente = studente;
            this.annotazioni = annotazioni;
            this.giornoData = giorno;
            this.meseData = mese;
            this.giornosettimana = giornosettimana;
            this.tipologia = tipologia;
        }

        @Override
        public String toString() {
            return "Assenze{" +
                    "annotazioni='" + annotazioni + '\'' +
                    ", classe='" + classe + '\'' +
                    ", scuola='" + scuola + '\'' +
                    ", studente='" + studente + '\'' +
                    ", giornoData='" + giornoData + '\'' +
                    ", meseData='" + meseData + '\'' +
                    ", giornosettimana='" + giornosettimana + '\'' +
                    ", tipologia=" + tipologia +
                    '}';
        }
    }
}
