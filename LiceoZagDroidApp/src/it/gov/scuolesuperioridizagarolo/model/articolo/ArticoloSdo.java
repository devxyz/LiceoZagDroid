package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloSdo<T extends ArticoloDetails> {
    public final ArticoloDB articolo;

    public ArticoloSdo(ArticoloDB articolo, Class<T> cl) {
        this.articolo = articolo;
    }


    @Override
    public String toString() {
        return "ArticoloSdo{" +
                "articolo=" + articolo +
                '}';
    }

    public T getDetails() {
        //noinspection unchecked
        return (T) articolo.getDetails();
    }


}
