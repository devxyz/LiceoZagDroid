package it.gov.scuolesuperioridizagarolo.model.bitorario.griglia;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.*;

/**
 * Created by stefano on 01/10/2017.
 */
public class IndexedGrigliaOrariaMultiValore<K extends Comparable<K>> implements Cloneable {
    private final TreeMap<K, GrigliaOrariaMultiValore> griglia = new TreeMap<>();

    public boolean add(K key) {
        if (griglia.containsKey(key)) return false;
        griglia.put(key, new GrigliaOrariaMultiValore());
        return true;
    }

    /**
     * cancella chiavi non usate
     */
    public void cleanupUnusedKeys() {
        for (Iterator<Map.Entry<K, GrigliaOrariaMultiValore>> iterator = griglia.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<K, GrigliaOrariaMultiValore> e = iterator.next();
            GrigliaOrariaMultiValore value = e.getValue();
            List<BitOrarioOraLezione> bitOrarioOraLeziones = value.get();
            if (bitOrarioOraLeziones.size() == 0) {
                iterator.remove();
            }
        }
    }

    public void trim() {
        final Set<K> ks = new TreeSet<>(griglia.keySet());
        for (K k : ks) {
            final GrigliaOrariaMultiValore grigliaOrariaMultiValore = griglia.get(k);
            if (grigliaOrariaMultiValore.get().size() == 0) {
                griglia.remove(k);
            }

        }
    }

    public IndexedGrigliaOrariaMultiValore<K> clone() {
        try {
            return (IndexedGrigliaOrariaMultiValore<K>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public SortedSet<K> keys() {
        return Collections.unmodifiableSortedSet(griglia.navigableKeySet());
    }

    public GrigliaOrariaMultiValore get(K key) {
        return griglia.get(key);
    }

    public void add(K key, BitOrarioOraLezione l) {
        add(key);
        get(key).add(l);
    }

    public void remove(K key, BitOrarioOraLezione l) {

        final GrigliaOrariaMultiValore grigliaOrariaMultiValore = get(key);
        if (grigliaOrariaMultiValore != null) {
            grigliaOrariaMultiValore.remove(l);
        }
    }

    public void clear(K key, EOra o, EGiorno g) {
        add(key);
        get(key).clear(o, g);
    }

    public List<BitOrarioOraLezione> get(K key, EOra o, EGiorno g) {
        final GrigliaOrariaMultiValore gm = get(key);
        if (gm == null) return null;
        return gm.get(o, g);
    }
}
