package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by stefano on 27/09/2017.
 */
public abstract class CheckForClass {
    public Map<String, String> check(BitOrarioGrigliaOrario l) {
        final TreeSet<String> classi = l.getClassi();
        Map<String, String> ris = new TreeMap<>();
        for (String d : classi) {
            final String check = check(l, d);
            if (check == null || check.trim().length() == 0) continue;
            ris.put(d, check);
        }
        return ris;
    }

    protected void onStart(BitOrarioGrigliaOrario l) {

    }

    protected void onFinish(BitOrarioGrigliaOrario l) {

    }

    public void printReport(BitOrarioGrigliaOrario l) {
        onStart(l);
        System.out.println("\n\n\n==========================================================================================");
        System.out.println(" " + getClass().getSimpleName());
        System.out.println("==========================================================================================");
        final Map<String, String> check = check(l);
        for (Map.Entry<String, String> e : check.entrySet()) {
            System.out.println(e.getKey() + ":\n" + e.getValue().trim());
            System.out.println("============================================");
        }
        onFinish(l);
    }

    protected abstract String check(BitOrarioGrigliaOrario l, String nomeClasse);
}
