package it.gov.scuolesuperioridizagarolo.model.menu;

import java.io.Serializable;

/**
 * Created by stefano on 09/09/15.
 */
public interface DataMenuInfoBuilder extends Serializable{
    public DataMenuInfoType type();

    public Object build();
}
