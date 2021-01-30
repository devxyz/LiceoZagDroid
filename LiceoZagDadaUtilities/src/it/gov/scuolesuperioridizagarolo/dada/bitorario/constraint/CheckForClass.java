package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by stefano on 27/09/2017.
 */
public abstract class CheckForClass {
    public Map<ClassData, String> check(BitOrarioGrigliaOrario l) {
        final TreeSet<ClassData> classi = l.getClassi();
        Map<ClassData, String> ris = new TreeMap<>();
        for (ClassData d : classi) {
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
        System.out.println("Test "+getClass().getSimpleName());
        final Map<ClassData, String> check = check(l);
        for (Map.Entry<ClassData, String> e : check.entrySet()) {
            System.out.println(" > "+e.getKey() + ":\n" + e.getValue().trim());
        }
        onFinish(l);
    }

    protected abstract String check(BitOrarioGrigliaOrario l, ClassData nomeClasse);
}
