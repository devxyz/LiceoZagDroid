package utility.scrutini;

import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;

public class LeggiScrutini_ColonnaVotoAss {
    public LeggiScrutini_ColonnaVotoAss(TextRenderInfo l) {
        this.testo = l.getText();
        this.mediaX = LeggiScrutini.getAvgX(l);
        this.mediaY = LeggiScrutini.getAvgY(l);
    }

    public LeggiScrutini_ColonnaVotoAss(String testo, double mediaX, double mediaY) {
        this.testo = testo;
        this.mediaX = mediaX;
        this.mediaY = mediaY;
    }

    public final String testo;
    public final double mediaX;
    public final double mediaY;

    @Override
    public String toString() {
        return testo + " (" + Math.round(mediaX) + "," + Math.round(mediaY)+")";
    }
}
