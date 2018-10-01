package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.consigliDiClasse;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by stefano on 24/09/2018.
 */
public class DocentiComuniCdC {
    private final Map<ClassData, Set<ClassData>> cdcSeparati;

    public DocentiComuniCdC(BitOrarioGrigliaOrario orario) {
        cdcSeparati = new TreeMap<>();

        final TreeSet<ClassData> classi = orario.getClassi();
        for (ClassData x : classi) {
            cdcSeparati.put(x, new TreeSet<ClassData>());
        }

        for (ClassData c1 : classi) {
            for (ClassData c2 : classi) {
                if (c1 == c2) continue;
                if (!docentiInComune(orario, c1, c2)) {
                    cdcSeparati.get(c1).add(c2);
                }
            }
        }
    }

    public boolean docentiInComune(ClassData c1, ClassData c2) {
        return !cdcSeparati.get(c1).contains(c2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<ClassData, Set<ClassData>> e : cdcSeparati.entrySet()) {
            sb.append(e.getKey().className).append(": ");
            for (ClassData classData : e.getValue()) {
                sb.append(" ").append(classData.className);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean docentiInComune(BitOrarioGrigliaOrario o, ClassData c1, ClassData c2) {
        final Set<String> d1 = o.getDocentiPerClasse(c1);
        final Set<String> d2 = o.getDocentiPerClasse(c2);
        //controlla i docenti in comune
        for (String docente : d1) {
            if (d2.contains(docente))
                return true;
        }

        return false;
    }


}
