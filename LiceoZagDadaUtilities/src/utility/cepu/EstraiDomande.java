package utility.cepu;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.ImageRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;
import utility.scrutini.engine.data.ScrutiniCarenze_Termine;
import utility.scrutini.engine.data.ScrutiniCarenze_TerminiPerPagina;
import utility.scrutini.engine.util.ScrutiniCarenze_MergeTermini;
import utility.scrutini.engine.util.ScrutiniCarenze_TextExtractionByRow;

import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EstraiDomande {
    static File root = new File("/Users/stefano/Desktop/chiara/cepu");

    public static void main(String[] args) throws IOException {
        File f = new File(root, "1.pdf");

        PdfReader reader = new PdfReader(f);
        PdfDocument p = new PdfDocument(reader);
        final int numberOfPages = p.getNumberOfPages();

        List<ScrutiniCarenze_TerminiPerPagina> ris = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i = i + 1) {
            final PdfPage p1 = p.getPage(i);


            MySimpleTextExtractionStrategy strategy = new MySimpleTextExtractionStrategy() {
                @Override
                public Set<EventType> getSupportedEvents() {
                    return Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(
                            EventType.RENDER_TEXT,
                            EventType.RENDER_IMAGE,
                            EventType.RENDER_PATH
                    )));
                }

                @Override
                public void eventOccurred(IEventData data, EventType type) {
                    if (type.equals(EventType.RENDER_TEXT)) {
                        super.eventOccurred(data, type);
                    } else {
                        //result.append("IMAGE: "+data+"\n");
                        //System.out.println(data);
                        if (type.equals(EventType.RENDER_IMAGE)) {
                            ImageRenderInfo i = (ImageRenderInfo) data;
                            rows.add("IMAGE: " + i.getImage());
                        }else{
                            rows.add("ALTRO: " + data);

                        }
                    }
                }
            };

            PdfTextExtractor.getTextFromPage(p1, strategy);
            for (String row : strategy.rows) {
                System.out.println(row);
            }

            System.out.println("====================================");
            System.out.println("====================================");
            System.out.println("====================================");
            System.out.println("====================================");
            System.out.println(strategy.getResultantText());

            break;
        }


    }
}
