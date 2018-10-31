package it.gov.scuolesuperioridizagarolo.model.bitorario.griglia;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stefano on 01/10/2017.
 */
public class GrigliaOrariaMultiValore implements Cloneable {
    private final ArrayList<BitOrarioOraLezione>[][] orario;



    public GrigliaOrariaMultiValore clone(){
        try {
            return (GrigliaOrariaMultiValore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public GrigliaOrariaMultiValore() {
        this.orario = new ArrayList[EOra.values().length][EGiorno.values().length];
        for (EOra o : EOra.values()) {
            for (EGiorno g : EGiorno.values()) {
                orario[o.ordinal()][g.ordinal()] = new ArrayList<>();
            }
        }
    }

    /**
     * elenco di tutte le lezioni
     *
     * @return
     */
    public List<BitOrarioOraLezione> get() {
        List<BitOrarioOraLezione> ris = new ArrayList<>();
        for (ArrayList<BitOrarioOraLezione>[] riga : orario) {
            if (riga != null)
                for (ArrayList<BitOrarioOraLezione> v : riga) {
                    if (v != null)
                        for (BitOrarioOraLezione bitOrarioOraLezione : v) {
                            if (bitOrarioOraLezione != null)
                                ris.add(bitOrarioOraLezione);
                        }
                }
        }
        return ris;
    }

    public List<BitOrarioOraLezione> get(EOra o, EGiorno g) {
        return Collections.unmodifiableList(orario[o.ordinal()][g.ordinal()]);
    }

    public void clear() {
        for (ArrayList<BitOrarioOraLezione>[] x : orario) {
            for (ArrayList<BitOrarioOraLezione> y : x) {
                y.clear();
            }
        }
    }
    public void clear(EOra o, EGiorno g) {
        orario[o.ordinal()][g.ordinal()].clear();
    }

    public void add(BitOrarioOraLezione l) {
        orario[l.getOra().ordinal()][l.getGiorno().ordinal()].add(l);
    }

    public void remove(BitOrarioOraLezione l) {
        final boolean b = orario[l.getOra().ordinal()][l.getGiorno().ordinal()].remove(l);
        if (!b){
            throw new IllegalArgumentException("Lezione "+l.toStringComplete()+" non presente");
        }
    }

}
