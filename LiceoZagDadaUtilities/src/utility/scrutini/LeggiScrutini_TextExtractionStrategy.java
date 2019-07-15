package utility.scrutini;

import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import java.util.*;

public class LeggiScrutini_TextExtractionStrategy extends SimpleTextExtractionStrategy {

    private final ArrayList<String> parole;
    private int indiceParola = 0;
    private int indiceRiga = 0;
    private String classe;
    private double precY = 0;
    final static double EPSILON = 0.0000001;

    public ArrayList<LeggiScrutini_Materia> getIntestazione() {
        return intestazione;
    }

    public final ArrayList<LeggiScrutini_Materia> intestazione = new ArrayList<>();
    private boolean fineParse = false;

    /**
     * Compare two doubles within a given epsilon
     */
    public static boolean equals(double a, double b, double eps) {
        if (a == b) return true;
        // If the difference is less than epsilon, treat as equal.
        return Math.abs(a - b) < eps;
    }

    /**
     * Compare two doubles, using default epsilon
     */
    public static boolean equals(double a, double b) {
        if (a == b) return true;
        // If the difference is less than epsilon, treat as equal.
        return Math.abs(a - b) < EPSILON * Math.max(Math.abs(a), Math.abs(b));
    }

    public LeggiScrutini_TextExtractionStrategy(ArrayList<String> parole) {
        this.parole = parole;
    }

    public String getClasse() {
        return classe.substring(0, classe.indexOf(" ")).trim();
    }

    public String getIndirizzo() {
        return classe.substring(classe.indexOf(" ")).trim();
    }


    @Override
    public Set<EventType> getSupportedEvents() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(EventType.RENDER_TEXT)));
    }

    @Override
    public void eventOccurred(IEventData data, EventType type) {
        super.eventOccurred(data, type);
        if (fineParse) return;


        if (type == EventType.BEGIN_TEXT) {
            TextRenderInfo renderInfo = (TextRenderInfo) data;
//                        final String text = renderInfo.getText().trim();
            //System.out.println("B#" + text);

        }
        if (type == EventType.END_TEXT) {
            TextRenderInfo renderInfo = (TextRenderInfo) data;
            //                      final String text = renderInfo.getText().trim();
            // System.out.println("E#" + text);

        }
        if (type == EventType.RENDER_TEXT) {
            indiceParola++;


            TextRenderInfo renderInfo = (TextRenderInfo) data;
            double curY = LeggiScrutini.getAvgY(renderInfo);
            String testo = renderInfo.getText();
            boolean aCapo = Math.abs(precY - curY) > 5;
            if (aCapo) {
                indiceRiga++;
            }
            precY = curY;


            if (testo.equalsIgnoreCase("firme")) {
                fineParse = true;
                return;
            }

            if (aCapo) {
                System.out.println("<<" + indiceRiga + ">> ======================================================");
            }
            System.out.print("<" + testo + ">");
            System.out.print("\tX MEDIA: " + LeggiScrutini.getAvgX(renderInfo));
            System.out.println("\tY MEDIA: " + curY);
            LeggiScrutini.printDebugLine(renderInfo);


            parole.add(testo.trim().toUpperCase());


            //SE CLASSE
            if (indiceRiga == 6) {
                classe = testo;
                return;
            }

            //SE INTESTAZIONE
            if (indiceRiga == 10) {
                LeggiScrutini_Testo nuovo = new LeggiScrutini_Testo(renderInfo);
                LeggiScrutini_Materia e = new LeggiScrutini_Materia(nuovo);

                if (intestazione.size() > 0) {
                    LeggiScrutini_Materia precLeggiScrutiniIntestazione = this.intestazione.get(this.intestazione.size() - 1);
                    if (Math.abs(precLeggiScrutiniIntestazione.mediaX() - LeggiScrutini.getAvgX(renderInfo)) <= 10) {
                        precLeggiScrutiniIntestazione.parole.add(nuovo);
                        e = null;
                    } else {
                        intestazione.add(e);
                    }

                } else {
                    intestazione.add(e);
                }

                if (e != null) {
                    if (
                            testo.trim().equalsIgnoreCase("alunni") ||
                                    testo.trim().equalsIgnoreCase("comportamento") ||
                                    testo.trim().equalsIgnoreCase("esito finale")
                    ) {
                        e.colonnaAssenza = new LeggiScrutini_ColonnaVotoAss("vuoto", -10000, -1000000);
                    }

                }

            }

            //SE INTESTAZIONE VOTO-ASSENZA
            if (indiceRiga == 11) {
                for (LeggiScrutini_Materia scrutini_materia : intestazione) {
                    if (scrutini_materia.colonnaVoto == null) {
                        scrutini_materia.colonnaVoto = new LeggiScrutini_ColonnaVotoAss(renderInfo);
                        break;
                    }
                    if (scrutini_materia.colonnaAssenza == null) {
                        scrutini_materia.colonnaAssenza = new LeggiScrutini_ColonnaVotoAss(renderInfo);
                        break;
                    }
                }
            }


        }


    }
}
