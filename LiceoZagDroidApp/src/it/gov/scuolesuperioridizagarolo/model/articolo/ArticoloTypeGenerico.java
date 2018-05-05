package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloTypeGenerico extends ArticoloType {


    public ArticoloTypeGenerico() {
    }


    public static ArticoloTypeGenerico loadFrom(ArticoloDB db) {
        return ArticoloType.loadFrom(ArticoloTypeGenerico.class, db);
    }

}
