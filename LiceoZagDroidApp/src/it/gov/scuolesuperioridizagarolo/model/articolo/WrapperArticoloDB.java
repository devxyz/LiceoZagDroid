package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails;

import java.util.Set;

/**
 * Created by stefano on 22/03/2018.
 */
public class WrapperArticoloDB<T extends ArticoloDetails> {
    public final Class<T> classeArticolo;
    public final ArticoloDB articolo;


    public WrapperArticoloDB(Class<T> classeArticolo, ArticoloDB articolo) {
        this.classeArticolo = classeArticolo;
        this.articolo = articolo;
        final ArticoloDetails details = articolo.getDetails();
        if (details != null) {
            if (!details.getClass().equals(classeArticolo))
                throw new IllegalArgumentException("Invalid class: found " + details.getClass() + ", expected " + classeArticolo);
        }
    }

    public T getDetails() {
        return (T) articolo.getDetails();
    }

    public Set<String> getParoleLowerCase() {
        final T details = getDetails();
        return details.getParoleLowerCase();
    }

}
