package utility.scrutini.engine.data;

import java.util.*;

public class ScrutiniCarenze_Termine {
    public final double avgX, avgY;
    public final String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScrutiniCarenze_Termine that = (ScrutiniCarenze_Termine) o;
        return Double.compare(that.avgX, avgX) == 0 &&
                Double.compare(that.avgY, avgY) == 0 &&
                Objects.equals(text, that.text);
    }

    public double distanza(ScrutiniCarenze_Termine t2) {
        return Math.sqrt(
                (t2.avgX - this.avgX) * (t2.avgX - this.avgX) +
                        (t2.avgY - this.avgY) * (t2.avgY - this.avgY)
        );
    }

    @Override
    public String toString() {
        return text +
                "(X:" + avgX +
                ", Y:" + avgY +
                ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(avgX, avgY, text);
    }

    public ScrutiniCarenze_Termine(double avgX, double avgY, String text) {
        this.avgX = avgX;
        this.avgY = avgY;
        this.text = text;
    }

    public static ScrutiniCarenze_Termine unione(ScrutiniCarenze_Termine t1, ScrutiniCarenze_Termine t2) {
        ScrutiniCarenze_Termine t3 = new ScrutiniCarenze_Termine(
                (t1.avgX + t2.avgX) / 2,
                (t1.avgY + t2.avgY) / 2,
                (t1.text.trim() + " " + t2.text.trim()).trim()
        );
        return t3;
    }

    public static ScrutiniCarenze_Termine unione(List<ScrutiniCarenze_Termine> ll) {
        if (ll.size() == 0) return null;
        double x = 0, y = 0;
        StringBuilder sb = new StringBuilder();
        List<ScrutiniCarenze_Termine> ll2 = new ArrayList<>(ll);

        for (ScrutiniCarenze_Termine termine : ll2) {
            sb.append(termine.text.trim()).append(" ");
            x += termine.avgX;
            y += termine.avgY;
        }

        ScrutiniCarenze_Termine t3 = new ScrutiniCarenze_Termine(
                (x) / ll.size(),
                (y) / ll.size(),
                sb.toString().trim()
        );
        return t3;
    }



}
