package utility.scrutini.engine.data;

import java.util.Objects;

public class RecuperoCarenze_Intestazione {
    public final String materia;
    public final double avgX, avgY;

    @Override
    public String toString() {
        return materia ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecuperoCarenze_Intestazione that = (RecuperoCarenze_Intestazione) o;
        return Double.compare(that.avgX, avgX) == 0 &&
                Double.compare(that.avgY, avgY) == 0 &&
                Objects.equals(materia, that.materia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materia, avgX, avgY);
    }

    public RecuperoCarenze_Intestazione(String materia, double avgX, double avgY) {
        this.materia = materia;
        this.avgX = avgX;
        this.avgY = avgY;
    }
}
