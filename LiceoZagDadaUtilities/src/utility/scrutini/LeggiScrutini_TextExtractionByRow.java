package utility.scrutini;

import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import java.util.*;

public class LeggiScrutini_TextExtractionByRow extends SimpleTextExtractionStrategy {

    private final ArrayList<ArrayList<LeggiScrutini_Termine>> parole;
    private int indiceParola = 0;
    private int indiceRiga = 0;
    private double precY = 0;
    final static double EPSILON = 0.0000001;

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

    public LeggiScrutini_TextExtractionByRow() {
        this.parole = new ArrayList<>();
    }


    @Override
    public Set<EventType> getSupportedEvents() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(EventType.RENDER_TEXT)));
    }

    public ArrayList<ArrayList<LeggiScrutini_Termine>> getParole() {
        return parole;
    }

    @Override
    public void eventOccurred(IEventData data, EventType type) {
        super.eventOccurred(data, type);


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
                parole.add(new ArrayList<>());
            }
            precY = curY;


            if (aCapo) {
                System.out.println("<<" + indiceRiga + ">> ======================================================");
            }

            parole.add(new ArrayList<>());
            System.out.print("<" + testo + ">");
            System.out.print("\tX MEDIA: " + LeggiScrutini.getAvgX(renderInfo));
            System.out.println("\tY MEDIA: " + curY);
            LeggiScrutini.printDebugLine(renderInfo);


            ArrayList<LeggiScrutini_Termine> riga = parole.get(parole.size() - 1);
            riga.add(new LeggiScrutini_Termine(indiceRiga,
                    LeggiScrutini.getAvgX(renderInfo),
                    LeggiScrutini.getAvgY(renderInfo),
                    testo.trim()

            ));


        }


    }
}
