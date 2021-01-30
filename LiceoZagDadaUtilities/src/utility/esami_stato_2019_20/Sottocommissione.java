package utility.esami_stato_2019_20;

import java.util.Collection;
import java.util.TreeSet;

public class Sottocommissione {
    final public String classe;
    final public String nomecommissione;
    final int durata_in_giorni;
    public final TreeSet<Integer> giorniDaScartare;
    final public TreeSet<String> commissari = new TreeSet<>();

    public Sottocommissione(String classe, String nomecommissione, int durata_in_giorni, Collection<Integer> giorniDaScartare) {
        this.classe = classe;
        this.nomecommissione = nomecommissione.trim().toUpperCase();
        this.durata_in_giorni = durata_in_giorni;
        this.giorniDaScartare = new TreeSet<>(giorniDaScartare);
    }

    public boolean conflitto(Sottocommissione c2) {
        if (nomecommissione.equalsIgnoreCase(c2.nomecommissione)) return true;
        for (String s : commissari) {
            if (c2.commissari.contains(s))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sottocommissione{" +
                "classe='" + classe + '\'' +
                ", nomecommissione='" + nomecommissione + '\'' +
                ", durata_in_giorni=" + durata_in_giorni +
                ", giorniDaScartare=" + giorniDaScartare +
                ", commissari=" + commissari +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sottocommissione that = (Sottocommissione) o;

        return classe != null ? classe.equals(that.classe) : that.classe == null;
    }

    @Override
    public int hashCode() {
        return classe != null ? classe.hashCode() : 0;
    }

    public String getClasse() {
        return classe;
    }

    public String getNomecommissione() {
        return nomecommissione;
    }
}
