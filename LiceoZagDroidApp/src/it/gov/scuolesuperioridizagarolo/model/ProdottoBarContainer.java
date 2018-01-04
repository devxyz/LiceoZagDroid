package it.gov.scuolesuperioridizagarolo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by stefano on 04/01/2018.
 */
public class ProdottoBarContainer implements Serializable {
    private final ArrayList<ProdottoBar> lista;

    public ProdottoBarContainer() {
        lista = new ArrayList<>();
    }

    public List<ProdottoBar> getLista() {
        return new ArrayList<>(lista);
    }

    public void add(ProdottoBar b) {
        lista.add(b);
    }

    public void add(List<ProdottoBar> b) {
        lista.addAll(b);
    }

    public LinkedHashSet<String> getNomiUtenti() {
        LinkedHashSet<String> ris = new LinkedHashSet<>();
        for (ProdottoBar x : lista) {
            ris.add(x.nomeUtente);
        }
        return ris;
    }

    public List<ProdottoBar> filter(ProdottoBarContainerFilter f) {
        List<ProdottoBar> ris = new ArrayList<>();
        for (ProdottoBar p : lista) {
            if (f.accept(p))
                ris.add(p);
        }
        return ris;
    }

    public void remove(ProdottoBarContainerFilter f) {
        for (Iterator<ProdottoBar> iterator = lista.iterator(); iterator.hasNext(); ) {
            ProdottoBar p = iterator.next();
            if (f.accept(p))
                iterator.remove();
        }
    }

    public int getNumeroProdotti() {
        int count = 0;
        for (ProdottoBar x : lista) {
            count += x.quantita;
        }
        return count;
    }

    public double getPrezzoTotale() {
        double count = 0;
        for (ProdottoBar x : lista) {
            count += x.prezzounitario * x.quantita;
        }
        return count;
    }


    public static interface ProdottoBarContainerFilter {
        public boolean accept(ProdottoBar b);
    }
}
