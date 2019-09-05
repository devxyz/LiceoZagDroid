package utility.scrutini.engine.data;

import java.util.Objects;

public class Scrutini_Intestazione {
    public final String materia;
    public final String tipologia;// Ass - U - ""
    public final Scrutini_TipoValoreEnum tipologiaEnum;// Ass - U - ""
    public final double avgX, avgY;


    public Scrutini_Intestazione(String materia, Scrutini_TipoValoreEnum etichettaTipologia, double avgX, double avgY) {
        this.materia = materia;
        this.tipologia = etichettaTipologia.name();
        this.avgX = avgX;
        this.avgY = avgY;
        tipologiaEnum = etichettaTipologia;
    }
    public Scrutini_Intestazione(String materia, String etichettaTipologia, double avgX, double avgY) {
        this.materia = materia;
        this.tipologia = etichettaTipologia;
        this.avgX = avgX;
        this.avgY = avgY;
        tipologiaEnum = Scrutini_TipoValoreEnum.searchBy(materia, etichettaTipologia);
    }

    @Override
    public String toString() {
        return materia + "(" + tipologiaEnum + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scrutini_Intestazione that = (Scrutini_Intestazione) o;
        return Double.compare(that.avgX, avgX) == 0 &&
                Double.compare(that.avgY, avgY) == 0 &&
                Objects.equals(materia, that.materia) &&
                Objects.equals(tipologia, that.tipologia) &&
                tipologiaEnum == that.tipologiaEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(materia, tipologia, tipologiaEnum, avgX, avgY);
    }

}
