package it.gov.scuolesuperioridizagarolo.model;

import android.util.Base64;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoFlag;

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

    public String getAdditionalFieldName() {
        switch (this) {
            case ADMIN:
                return null;
            case ATA:
                return null;
            case DOCENTE:
                return "Nome Docente";
            case FAMIGLIA:
                return "Classe";
            case STUDENTE:
                return "Classe";
            default:
                return null;
        }
    }


    public String getPassword(String additionalField) {
        switch (this) {
            case ADMIN:
                return "Stefano123$";
            case ATA:
                return "Ata123";
            case DOCENTE:
                return Base64.encodeToString(additionalField.getBytes(), Base64.DEFAULT);
            case FAMIGLIA:
                return null;
            case STUDENTE:
                return null;
            default:
                return null;
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
