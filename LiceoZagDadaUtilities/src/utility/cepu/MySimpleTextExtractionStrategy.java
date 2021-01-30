package utility.cepu;

import com.itextpdf.kernel.geom.LineSegment;
import com.itextpdf.kernel.geom.Vector;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class MySimpleTextExtractionStrategy implements ITextExtractionStrategy {

    private Vector lastStart;
    private Vector lastEnd;
    protected ArrayList<String> rows = new ArrayList<>();

    /**
     * used to store the resulting String.
     */
    protected final StringBuilder result = new StringBuilder();

    @Override
    public void eventOccurred(IEventData data, EventType type) {
        if (type.equals(EventType.RENDER_TEXT)) {
            TextRenderInfo renderInfo = (TextRenderInfo) data;
            boolean firstRender = result.length() == 0;
            boolean hardReturn = false;

            LineSegment segment = renderInfo.getBaseline();
            Vector start = segment.getStartPoint();
            Vector end = segment.getEndPoint();

            if (!firstRender) {
                Vector x1 = lastStart;
                Vector x2 = lastEnd;

                // see http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html
                float dist = (x2.subtract(x1)).cross((x1.subtract(start))).lengthSquared() / x2.subtract(x1).lengthSquared();

                float sameLineThreshold = 1f; // we should probably base this on the current font metrics, but 1 pt seems to be sufficient for the time being
                if (dist > sameLineThreshold)
                    hardReturn = true;

                // Note:  Technically, we should check both the start and end positions, in case the angle of the text changed without any displacement
                // but this sort of thing probably doesn't happen much in reality, so we'll leave it alone for now
            }

            if (hardReturn) {
                //System.out.println("<< Hard Return >>");
                appendTextChunk("\n");
            } else if (!firstRender) {
                if (result.charAt(result.length() - 1) != ' ' && renderInfo.getText().length() > 0 && renderInfo.getText().charAt(0) != ' ') { // we only insert a blank space if the trailing character of the previous string wasn't a space, and the leading character of the current string isn't a space
                    float spacing = lastEnd.subtract(start).length();
                    if (spacing > renderInfo.getSingleSpaceWidth() / 2f) {
                        appendTextChunk(" ");
                        //System.out.println("Inserting implied space before '" + renderInfo.getText() + "'");
                    }
                }
            } else {
                //System.out.println("Displaying first string of content '" + text + "' :: x1 = " + x1);
            }

            //System.out.println("[" + renderInfo.getStartPoint() + "]->[" + renderInfo.getEndPoint() + "] " + renderInfo.getText());
            appendTextChunk(renderInfo.getText());

            lastStart = start;
            lastEnd = end;
        }
    }

    @Override
    public Set<EventType> getSupportedEvents() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(Collections.singletonList(EventType.RENDER_TEXT)));
    }

    /**
     * Returns the result so far.
     *
     * @return a String with the resulting text.
     */
    @Override
    public String getResultantText() {
        return result.toString();
    }

    /**
     * Used to actually append text to the text results.  Subclasses can use this to insert
     * text that wouldn't normally be included in text parsing (e.g. result of OCR performed against
     * image content)
     *
     * @param text the text to append to the text results accumulated so far
     */
    protected final void appendTextChunk(CharSequence text) {

        result.append(text);


        String e = text.toString();
        if (e.trim().length() > 0) {
            if (e.endsWith("\n")) {
                rows.add(e.replace("\n", ""));
            } else {
                rows.set((rows.size() - 1), rows.get(rows.size() - 1) + text);
            }
        }

    }


}