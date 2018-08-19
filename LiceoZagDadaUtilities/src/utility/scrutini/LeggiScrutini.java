package utility.scrutini;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by stefano on 09/07/2018.
 */
public class LeggiScrutini {

    static final boolean PRINT1 = false;

    private static void extractParole(final ArrayList<String> parole, File f) throws IOException {
        PdfReader reader = new PdfReader(f);
        PdfDocument p = new PdfDocument(reader);
        final int numberOfPages = p.getNumberOfPages();


        //System.out.println(numberOfPages);

        for (int i = 1; i <= numberOfPages; i = i + 1) {

            final PdfPage p1 = p.getPage(i);
            ITextExtractionStrategy strategy = new SimpleTextExtractionStrategy() {

                @Override
                public Set<EventType> getSupportedEvents() {
                    return Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(EventType.RENDER_TEXT)));
                }

                @Override
                public void eventOccurred(IEventData data, EventType type) {
                    super.eventOccurred(data, type);

                    if (type == EventType.BEGIN_TEXT) {
                        TextRenderInfo renderInfo = (TextRenderInfo) data;
                        final String text = renderInfo.getText().trim();
                        System.out.println("B#" + text);

                    }
                    if (type == EventType.END_TEXT) {
                        TextRenderInfo renderInfo = (TextRenderInfo) data;
                        final String text = renderInfo.getText().trim();
                        System.out.println("E#" + text);

                    }
                    if (type == EventType.RENDER_TEXT) {
                        TextRenderInfo renderInfo = (TextRenderInfo) data;

                        //      System.out.println(data);
                        //    System.out.println(renderInfo.getText());
                        // System.out.println(renderInfo.getPdfString());
                        //  System.out.println("=================================");
                        final String text = renderInfo.getText().trim();
                        System.out.println("R#" + text);

                        parole.add(text.toUpperCase());

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

    public static void main(String[] args) throws IOException {
        ArrayList<String> parole = new ArrayList<>();
        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/src/utility/scrutini/TABELLONE SCRUTINI.PDF");
        extractParole(parole, f);
    }
}
