package it.gov.scuolesuperioridizagarolo.model;

/**
 * Created by stefano on 18/09/15.
 */
public enum AppUserType {
    DOCENTE("Docente"), STUDENTE("Studente"), FAMIGLIA("Genitore"), ATA("Personale ATA");

    public String getDescrizione() {
        return descrizione;
    }

    private final String descrizione;

    AppUserType(String descrizione) {
        this.descrizione = descrizione;
    }
}
