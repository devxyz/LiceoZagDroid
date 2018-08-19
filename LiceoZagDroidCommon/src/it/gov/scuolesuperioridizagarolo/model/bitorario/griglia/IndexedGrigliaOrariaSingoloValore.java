package it.gov.scuolesuperioridizagarolo.model.bitorario.griglia;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * Created by stefano on 01/10/2017.
 */
public class IndexedGrigliaOrariaSingoloValore<K extends Comparable<K>> {
    private final TreeMap<K, GrigliaOrariaSingoloValore> griglia = new TreeMap<>();

    public boolean add(K key) {
        if (griglia.containsKey(key)) return false;
        griglia.put(key, new GrigliaOrariaSingoloValore());
        return true;
    }

    public GrigliaOrariaSingoloValore get(K key) {
        return griglia.get(key);
    }


    public SortedSet<K> keys() {
        return Collections.unmodifiableSortedSet(griglia.descendingKeySet());
    }


    public BitOrarioOraLezione get(K key, EOra o, EGiorno g) {
        final GrigliaOrariaSingoloValore gx = griglia.get(key);
        if (gx == null) return null;
        return gx.get(o, g);
    }

    public BitOrarioOraLezione set(K key, BitOrarioOraLezione l) {
        add(key);
        return get(key).set(l);
    }
}
