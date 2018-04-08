package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

/**
 * Created by stefano on 22/03/2018.
 */
public class WrapperArticoloDB<T extends ArticoloType> {
    public final Class<T> classeArticolo;
    public final ArticoloDB articolo;
    public final T articoloDettagli;

    public WrapperArticoloDB(Class<T> classeArticolo, ArticoloDB articolo) {
        this.classeArticolo = classeArticolo;
        this.articolo = articolo;
        if (!classeArticolo.getName().equals(articolo.getJsonClass())) {
            throw new IllegalArgumentException("Invalid class. Found " + classeArticolo + ", required " + articolo.getJsonClass());
        }
        articoloDettagli = ArticoloType.loadFrom(classeArticolo, articolo);
    }

}
