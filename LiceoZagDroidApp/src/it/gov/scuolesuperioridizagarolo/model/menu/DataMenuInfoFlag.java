package it.gov.scuolesuperioridizagarolo.model.menu;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by stefano on 09/09/15.
 */
public enum DataMenuInfoFlag implements Serializable{
    DONT_SHOW_IN_HOME,
    SUB_ITEM,
    NOT_ACTIVE//se nascosto
    ,;

    public static Set<DataMenuInfoFlag> valueOf(String[] ss) {
        Set<DataMenuInfoFlag> ris = new TreeSet<>();
        for (String s : ss) {
            final String trim = s.trim();
            if (trim.length()==0)continue;
            try {
                ris.add(valueOf(trim));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error with label:" + s, e);
            }
        }
        return ris;
    }
}
