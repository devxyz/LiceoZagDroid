package it.gov.scuolesuperioridizagarolo.model;

import android.util.Log;
import dada.bitorario.data.BitOrarioGrigliaOrario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by stefano on 31/12/2017.
 */
public class BitOrarioGrigliaOrarioContainer {
    private final ArrayList<BitOrarioGrigliaOrarioItem> sortByremoteIdDesc = new ArrayList<>();
    private boolean sort = false;

    public static void main(String[] args) {
        BitOrarioGrigliaOrarioItem g = new BitOrarioGrigliaOrarioItem(new OnlyDate(8, 1, 2018), new OnlyDate(13, 1, 2018), 32, null);
        System.out.println(g.isValid(new OnlyDate(11, 1, 2018)));
    }

    public void add(Date startDate,
                    Date endDate,
                    long remoteId,
                    BitOrarioGrigliaOrario orario) {
        sortByremoteIdDesc.add(new BitOrarioGrigliaOrarioItem(new OnlyDate(startDate), new OnlyDate(endDate), remoteId, orario));
        sort = false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BitOrarioGrigliaOrarioItem x : sortByremoteIdDesc) {
            sb.append(x.remoteId).append("\n");
        }

        return sb.toString();
    }

    /**
     * restituisce l'orario di default (quello con id minimo)
     *
     * @return
     */
    public BitOrarioGrigliaOrario getOrarioDefault() {
        _sortIfNecessary();

        if (sortByremoteIdDesc.size() == 0) return new BitOrarioGrigliaOrario("default");
        return sortByremoteIdDesc.get(sortByremoteIdDesc.size() - 1).orario;
    }

    public BitOrarioGrigliaOrario getOrario(OnlyDate d) {
        Log.e(getClass().getSimpleName(), "INIZIO CALCOLO ORARIO CORRENTE per " + d);
        _sortIfNecessary();


        for (BitOrarioGrigliaOrarioItem o : sortByremoteIdDesc) {
            Log.e(getClass().getSimpleName(), "xxxxx CONTROLLO " + o.remoteId + " (" + o.startDate + ", " + o.endDate + ")");
            if (o.isValid(d)) {
                Log.e(getClass().getSimpleName(), "xxxxx OK VALIDO " + o.remoteId + " (" + o.startDate + ", " + o.endDate + ")");
                return o.orario;
            }
        }
        Log.e(getClass().getSimpleName(), "xxxxx NESSUN ORARIO TROVATO");
        return new BitOrarioGrigliaOrario("vuoto");
    }

    private void _sortIfNecessary() {
        if (!sort) {
            Log.e(getClass().getSimpleName(), "xxxxx SORT");
            Collections.sort(sortByremoteIdDesc, new Comparator<BitOrarioGrigliaOrarioItem>() {
                @Override
                public int compare(BitOrarioGrigliaOrarioItem a, BitOrarioGrigliaOrarioItem b) {

                    return -new Long(a.remoteId).compareTo(b.remoteId);
                }
            });
            sort = true;
        }
    }

    public BitOrarioGrigliaOrarioItem getOrarioDetails(OnlyDate d) {
        for (BitOrarioGrigliaOrarioItem o : sortByremoteIdDesc) {
            if (o.isValid(d)) return o;
        }
        return null;
    }

    public static class BitOrarioGrigliaOrarioItem {
        public final OnlyDate startDate;
        public final OnlyDate endDate;
        public final long remoteId;
        public final BitOrarioGrigliaOrario orario;

        private BitOrarioGrigliaOrarioItem(OnlyDate startDate, OnlyDate endDate, long remoteId, BitOrarioGrigliaOrario orario) {
            this.startDate = new OnlyDate(startDate);
            this.endDate = new OnlyDate(endDate);
            this.remoteId = remoteId;
            this.orario = orario;
        }

        public boolean isValid(OnlyDate d) {
            return this.startDate.compareTo(d) <= 0 && d.compareTo(endDate) <= 0;
        }


    }
}
