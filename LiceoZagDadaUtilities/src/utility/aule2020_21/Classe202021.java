package utility.aule2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.util.ArrayList;
import java.util.Comparator;

public class Classe202021 implements Comparable<Classe202021> {
    final String classe;
    final int numerosita;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classe202021 that = (Classe202021) o;

        if (numerosita != that.numerosita) return false;
        return classe != null ? classe.equals(that.classe) : that.classe == null;
    }

    public ClassData toClassData() {
        return ClassData.search(classe);
    }


    @Override
    public int hashCode() {
        int result = classe != null ? classe.hashCode() : 0;
        result = 31 * result + numerosita;
        return result;
    }

    public String getClasse() {
        return classe;
    }

    public int getNumerosita() {
        return numerosita;
    }

    public Classe202021(String classe, int capienza) {
        this.classe = classe;
        this.numerosita = capienza;
    }

    public Classe202021(ClassData c) {
        this.classe = c.className;
        this.numerosita = c.numberOfStudents;
    }

    @Override
    public String toString() {
        return classe + '[' + numerosita + ']';
    }

    public static String toString(ArrayList<Classe202021> a) {
        StringBuilder sb = new StringBuilder();
        for (Classe202021 y : a) {
            sb.append(y).append("\n");
        }
        return sb.toString();
    }

    static Comparator<Classe202021> cmp = Comparator.comparing(Classe202021::getClasse).thenComparing(Classe202021::getNumerosita);

    @Override
    public int compareTo(Classe202021 o) {
        return cmp.compare(this, o);
    }
}
