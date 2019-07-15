package utility.scrutini;

public class LeggiScrutini_Termine {
    public final LeggiScrutini_Termine parent;
    public final int numeroRiga;
    public final double avgX, avgY;
    public final String text;

    public LeggiScrutini_Termine(int numeroRiga, double avgX, double avgY, String text) {
        this(null, numeroRiga, avgX, avgY, text);
    }

    public LeggiScrutini_Termine(LeggiScrutini_Termine parent, int numeroRiga, double avgX, double avgY, String text) {
        this.parent = parent;
        this.numeroRiga = numeroRiga;
        this.avgX = avgX;
        this.avgY = avgY;
        this.text = text;
    }

    public static LeggiScrutini_Termine union(LeggiScrutini_Termine t1, LeggiScrutini_Termine t2) {

        if (t1.numeroRiga != t2.numeroRiga)
            throw new IllegalArgumentException("Numero riga non uguale: " + t1.numeroRiga + "," + t2.numeroRiga);
        LeggiScrutini_Termine t3 = new LeggiScrutini_Termine(
                Math.min(t1.numeroRiga, t2.numeroRiga),
                (t1.avgX + t2.avgX) / 2,
                (t1.avgY + t2.avgY) / 2,
                (t1.text + " " + t2.text).trim()

        );
        return t3;

    }
}
