package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.organico;

import java.util.ArrayList;

public class Cattedra2019_2020 implements Comparable<Cattedra2019_2020> {
    public final ArrayList<MateriaOraria2019_2020> materie = new ArrayList<>();
    public int numOreTotaliAssegnate = 0;

    public boolean completa() {
        return numOreTotaliAssegnate == 18;
    }

    public int oreMancanti() {
        return 18 - numOreTotaliAssegnate;
    }

    @Override
    public String toString() {
        return "Cattedra2019_2020{" +
                "numOreTotaliAssegnate=" + numOreTotaliAssegnate +
                '}';
    }

    public String toStringLong() {
        return "Cattedra2019_2020{" +
                "materie=" + materie +
                ", numOreTotaliAssegnate=" + numOreTotaliAssegnate +
                '}';
    }


    public boolean add(MateriaOraria2019_2020 m) {
        if (m.ore + numOreTotaliAssegnate > 18) return false;
        materie.add(m);
        numOreTotaliAssegnate += m.ore;
        return true;
    }

    @Override
    public int compareTo(Cattedra2019_2020 o) {
        int i = new Integer(numOreTotaliAssegnate).compareTo(o.numOreTotaliAssegnate);
        return i;
    }
}
