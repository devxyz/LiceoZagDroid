package utility.scrutini;

import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;

public class LeggiScrutini_Testo {
    public final String testo;
    public final double mediaX, mediaY;

    public LeggiScrutini_Testo(TextRenderInfo testo) {
        this.testo = testo.getText();
        mediaX = LeggiScrutini.getAvgX(testo);
        mediaY = LeggiScrutini.getAvgY(testo);
    }
}
