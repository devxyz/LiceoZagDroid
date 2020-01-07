package utility.scrutini.engine.data;

public enum EsitoScrutinioEnum {
    NON_PRESENTE(false, "non presente"),
    NON_AMMESSO(false, "non ammesso"),
    AMMESSO(true, "ammesso"),
    MANCATA_VALIDITA(false, "mancata validita'"),
    AMMESSO_DOPO_SOSP_GIUDIZIO(true, "Ammesso dopo sospens.di giudizio", "Ammesso dopo sospens.di"),
    AMMESSO_ALLESAME(true, "AMMESSO ALL'ESAME DI"),
    SCONOSCIUTO(false);

    private final String[] etichetta;
    private final boolean annoSuperato;

    EsitoScrutinioEnum(boolean annoSuperato, String... etichetta) {
        this.etichetta = etichetta;
        this.annoSuperato = annoSuperato;
    }

    public boolean isAnnoSuperato() {
        return annoSuperato;
    }

    public static EsitoScrutinioEnum searchByEtichetta(String etichetta) {
        if (etichetta == null)
            return SCONOSCIUTO;
        etichetta = etichetta.toLowerCase();
        for (EsitoScrutinioEnum value : values()) {
            for (String e : value.etichetta) {
                if (e != null && e.toLowerCase().startsWith(etichetta)) return value;
            }
        }
        return SCONOSCIUTO;
    }
}
