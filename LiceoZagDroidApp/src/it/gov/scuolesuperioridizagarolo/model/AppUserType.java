package it.gov.scuolesuperioridizagarolo.model;

import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoFlag;

/**
 * Created by stefano on 18/09/15.
 */
public enum AppUserType {
    DOCENTE("Docente", DataMenuInfoFlag.SHOW_DOCENTI),
    STUDENTE("Studente", DataMenuInfoFlag.SHOW_STUDENTI),
    FAMIGLIA("Genitore", DataMenuInfoFlag.SHOW_GENITORI),
    ATA("Personale ATA", DataMenuInfoFlag.SHOW_ATA);

    private final String descrizione;
    private final DataMenuInfoFlag flag;

    AppUserType(String descrizione, DataMenuInfoFlag flag) {
        this.descrizione = descrizione;
        this.flag = flag;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public DataMenuInfoFlag getUserFlag() {
        return flag;
    }
}
