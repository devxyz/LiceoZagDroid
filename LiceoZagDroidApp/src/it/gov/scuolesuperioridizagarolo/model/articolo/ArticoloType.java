package it.gov.scuolesuperioridizagarolo.model.articolo;

import com.google.gson.Gson;
import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

import java.util.Set;
import java.util.TreeSet;

/**
 * dati specifici per tipo di articolo
 */
public abstract class ArticoloType {
    protected final Set<String> paroleLowerCase;

    protected ArticoloType() {
        this.paroleLowerCase = new TreeSet<>();
    }

    public static <T extends ArticoloType> void saveTo(T a, ArticoloDB db) {
        Gson g = new Gson();
        final String s = g.toJson(a);
        db.setJsonContent(s);
        db.setJsonClass(a.getClass().getName());
    }

    public static <T extends ArticoloType> Class<T> getJSonClass(ArticoloDB db) {
        final String jsonClass = db.getJsonClass();
        try {
            return (Class<T>) Class.forName(jsonClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T extends ArticoloType> T loadFrom(Class<T> cl, ArticoloDB db) {
        final String jsonClass = db.getJsonClass();
        if (!cl.getName().equals(jsonClass)) {
            throw new IllegalArgumentException("Classe trovata nel db " + cl.getName() + ", richiesta " + jsonClass);
        }

        Gson g = new Gson();
        final T s = g.fromJson(db.getJsonContent(), cl);

        return s;

    }

    public void addParolaString(String p) {
        if (p != null)
            paroleLowerCase.add(p.trim().toLowerCase());
    }


}
