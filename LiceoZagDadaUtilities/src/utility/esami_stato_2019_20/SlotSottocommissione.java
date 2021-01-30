package utility.esami_stato_2019_20;

import java.util.Comparator;
import java.util.TreeSet;

public class SlotSottocommissione implements Comparable<SlotSottocommissione> {
    public int giornoInizio;
    public final Sottocommissione sottocommissione;


    public SlotSottocommissione(int giornoInizio, Sottocommissione sottocommissione) {
        this.giornoInizio = giornoInizio;
        this.sottocommissione = sottocommissione;
    }

    private boolean giorniInComune(SlotSottocommissione s1, SlotSottocommissione s2) {
        TreeSet<Integer> giorni1 = s1.giorniImpegnati();
        TreeSet<Integer> giorni2 = s2.giorniImpegnati();
        for (Integer g : giorni2) {
            if (giorni1.contains(g)) return true;
        }

        return false;
    }

    public boolean conflitto(SlotSottocommissione s1) {
        if (!sottocommissione.conflitto(s1.sottocommissione)) return false;
        if (giorniInComune(this, s1))
            return true;
        return false;
    }

    public TreeSet<Integer> giorniImpegnati() {
        TreeSet<Integer> ris = new TreeSet<>();
        int g = giornoInizio-1;
        for (int i = 0; i < sottocommissione.durata_in_giorni; i++) {
            do {
                g++;
            }
            while (sottocommissione.giorniDaScartare.contains(g));
            ris.add(g);

        }
        return ris;
    }

    @Override
    public String toString() {
        return "SlotSottocommissione{" +
                "giornoInizio=" + giornoInizio +
                ", sottocommissione=" + sottocommissione +
                '}';
    }

    static Comparator<Sottocommissione> sottocommissioneComparator = Comparator.comparing(Sottocommissione::getNomecommissione)
            .thenComparing(Sottocommissione::getClasse);

    @Override
    public int compareTo(SlotSottocommissione o) {
        return sottocommissioneComparator.compare(this.sottocommissione, o.sottocommissione);
    }

}
