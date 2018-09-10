package it.gov.scuolesuperioridizagarolo.dao.customType;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * dati specifici per tipo di articolo
 */
public abstract class ArticoloDetails {
    protected final Set<String> paroleLowerCase;

    protected ArticoloDetails() {
        this.paroleLowerCase = new TreeSet<>();
    }

    public Set<String> getParoleLowerCase() {
        return Collections.unmodifiableSet(paroleLowerCase);
    }

    public void addParolaString(String p) {
        if (p != null)
            paroleLowerCase.add(p.trim().toLowerCase());
    }

    public void addParolaString(Collection<String> p) {
        for (String s : p) {
            addParolaString(s);
        }

    }


    @Override
    public String toString() {
        return "ArticoloType{" +
                "paroleLowerCase=" + paroleLowerCase +
                '}';
    }
}
