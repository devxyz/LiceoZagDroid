package it.gov.scuolesuperioridizagarolo.dao.customType;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloDetailsGenerico extends ArticoloDetails {

    public String oggetto;

    public void check() throws IllegalArgumentException{
        if (oggetto==null)throw new IllegalArgumentException("Oggetto null");
    }

    public ArticoloDetailsGenerico() {
    }


}
