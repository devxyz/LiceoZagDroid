package utility.scrutini.engine.util;

import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;
import utility.scrutini.engine.data.ScrutiniCarenze_Termine;
import utility.scrutini.engine.util.ScrutiniCarenzeUtil;

import java.util.*;

public class ScrutiniCarenze_TextExtractionByRow extends SimpleTextExtractionStrategy {

    final static double EPSILON = 0.0000001;
    private final ArrayList<ScrutiniCarenze_Termine> parole;
    boolean PRINT1 = false;

    public ScrutiniCarenze_TextExtractionByRow() {
        this.parole = new ArrayList<>();
    }

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

    @Override
    public Set<EventType> getSupportedEvents() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(EventType.RENDER_TEXT)));
    }

    public ArrayList<ScrutiniCarenze_Termine> getParole() {
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


            TextRenderInfo renderInfo = (TextRenderInfo) data;
            double curY = ScrutiniCarenzeUtil.getAvgY(renderInfo);
            String testo = renderInfo.getText();


            if (PRINT1) {

                System.out.print("<" + testo + ">");
                System.out.print("\tX MEDIA: " + ScrutiniCarenzeUtil.getAvgX(renderInfo));
                System.out.println("\tY MEDIA: " + curY);
                ScrutiniCarenzeUtil.printDebugLine(renderInfo);
            }

            parole.add(new ScrutiniCarenze_Termine(
                    ScrutiniCarenzeUtil.getAvgX(renderInfo),
                    ScrutiniCarenzeUtil.getAvgY(renderInfo),
                    testo.trim()

            ));


        }


    }
}
