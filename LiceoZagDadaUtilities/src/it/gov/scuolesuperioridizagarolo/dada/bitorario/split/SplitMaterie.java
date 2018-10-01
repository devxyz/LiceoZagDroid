package it.gov.scuolesuperioridizagarolo.dada.bitorario.split;

import it.gov.scuolesuperioridizagarolo.model.bitorario.Classroom;
import it.gov.scuolesuperioridizagarolo.model.bitorario.ClassroomFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by stefano on 25/06/2017.
 */
public class SplitMaterie {
    public final String materiaComposta;
    //la materia 1 e' sempre quella con ore maggiori
    public final String materia1;
    public final int orario1;
    public final String materia2;
    public final int orario2;

    public final Classroom classe;
    public int orario2_usato;
    public int orario1_usato;

    public SplitMaterie(Classroom classe, String materiaComposta, String materia1, int orario1, String materia2, int orario2) {

        this.classe = classe;
        this.materiaComposta = materiaComposta;

        if (orario1 > orario2) {

            this.materia1 = materia1;
            this.orario1 = orario1;

            this.materia2 = materia2;
            this.orario2 = orario2;
        } else {

            this.materia1 = materia2;
            this.orario1 = orario2;

            this.materia2 = materia1;
            this.orario2 = orario1;
        }
        orario1_usato = orario2_usato = 0;
    }


    public static void main(String[] args) {
        String materia = "ITAL-LAT";
        String classe = "IIC";
        final List<SplitMaterie> split = SplitMaterieUtil.build();
        for (SplitMaterie x : split) {
            System.out.println(x);
            if (x.compatibile(classe, materia))
                System.out.println("   SI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            else
                System.out.println("   NO");
        }
    }

    public static List<SplitMaterie> splitMaterie(ClassroomFilter filter, String materiaComposta, String materia1, int orario1, String materia2, int orario2) {
        return splitMaterie(filter.filter(), materiaComposta, materia1, orario1, materia2, orario2);
    }

    public static List<SplitMaterie> splitMaterie(Collection<Classroom> classi, String materiaComposta, String materia1, int orario1, String materia2, int orario2) {
        List<SplitMaterie> ris = new ArrayList<>();

        for (Classroom s : new TreeSet<>(classi)) {
            ris.add(new SplitMaterie(s, materiaComposta, materia1, orario1, materia2, orario2));
        }

        return ris;
    }


    @Override
    public String toString() {
        return "SplitMaterie{" +
                "classe=" + classe + ", " +
                "materiaComposta='" + materiaComposta + '\'' +
                ", materia1='" + materia1 + '\'' +
                ", orario1=" + orario1 + "(free " + rimanenti1() + ")" +
                ", materia2='" + materia2 + '\'' +
                ", orario2=" + orario2 + "(free " + rimanenti2() + ")" +
                '}';
    }

    public boolean compatibile(String classe, String materia) {
        if (classe == null || materia == null) return false;

        return (this.classe.label).equalsIgnoreCase(classe.trim()) && materiaComposta.equalsIgnoreCase(materia.trim());
    }

    public int rimanenti1() {
        return orario1 - orario1_usato;
    }

    public int rimanenti2() {
        return orario2 - orario2_usato;
    }

    //assegna
    public String assegna(int ore) {
        //2 ore cerca di assegnarle all'orario piu' grande
        if (ore == 2 && rimanenti1() >= ore) {
            orario1_usato += ore;
            return materia1;
        } else {
            if (rimanenti2() >= ore) {
                orario2_usato += ore;
                return materia2;
            } else {
                if (rimanenti1() >= ore) {
                    orario1_usato += ore;
                    return materia1;
                } else {
                    //impossibile assegnare
                    return null;
                }
            }

        }

    }


}
