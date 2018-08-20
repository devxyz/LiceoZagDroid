package it.gov.scuolesuperioridizagarolo.model;

import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoFlag;

import java.util.Objects;
import java.util.Set;

/**
 * Created by stefano on 18/09/15.
 */
public enum AppUserType {
    DOCENTE("Docente", DataMenuInfoFlag.SHOW_DOCENTI),
    STUDENTE("Studente", DataMenuInfoFlag.SHOW_STUDENTI),
    FAMIGLIA("Genitore", DataMenuInfoFlag.SHOW_GENITORI),
    ATA("Personale ATA", DataMenuInfoFlag.SHOW_ATA),
    ADMIN("Amministratore", DataMenuInfoFlag.SHOW_DOCENTI, DataMenuInfoFlag.SHOW_ADMIN);

    private final String descrizione;
    private final DataMenuInfoFlag[] flag;

    AppUserType(String descrizione, DataMenuInfoFlag... flag) {
        this.descrizione = descrizione;
        this.flag = flag;
    }


    public boolean verifyPassword(String s) {
        switch (this) {
            case ADMIN:
                return Objects.equals("xxx", s);
            case ATA:
                return Objects.equals("yyy", s);

            case DOCENTE:
                return true;
            case FAMIGLIA:
                return true;
            case STUDENTE:
                return true;
            default:
                return false;
        }

    }

    public String getDescrizione() {
        return descrizione;
    }

    //restituisce true se almeno uno dei flag appartiene all'utente
    public boolean showMenuForUser(DataMenuInfo m) {
        Set<DataMenuInfoFlag> _flags = m.getFlags();
        //if (_flags.size() == 0) return true;
        for (DataMenuInfoFlag x : flag) {
            if (_flags.contains(x)) {
                return true;
            }
        }
        return false;
    }

    public DataMenuInfoFlag[] __getUserFlag() {
        return flag;
    }
}
