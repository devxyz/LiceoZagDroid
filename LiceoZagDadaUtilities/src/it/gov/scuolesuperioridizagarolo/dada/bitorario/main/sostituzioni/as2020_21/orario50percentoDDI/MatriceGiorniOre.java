package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Arrays;

public class MatriceGiorniOre {
    private int[][] valori;

    public MatriceGiorniOre() {
        valori = new int[EGiorno.values().length][EOra.values().length];
    }

    public int get(EGiorno g, EOra o) {
        return valori[g.ordinal()][o.ordinal()];
    }

    public void increment(EGiorno g, EOra o, int val) {
        valori[g.ordinal()][o.ordinal()] += val;
    }

    public void reset() {
        for (int i = 0; i < valori.length; i++) {
            for (int j = 0; j < valori[0].length; j++) {
                valori[i][j] = 0;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("xxxxxx");
        for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
            sb.append("\t").append(g);
        }
        sb.append("\n");
        for (EOra o : EOra.valuesOreDiLezione()) {
            sb.append(o);
            for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
                sb.append("\t").append(get(g, o));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        MatriceGiorniOre mm = new MatriceGiorniOre();
        System.out.println(mm);
    }
}
