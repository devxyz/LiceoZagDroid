package it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values;

/**
 * Created by stefano on 24/11/2017.
 */
public enum ERoomArea {
    AREA_A("Piano Terra (Area BLU)"),
    AREA_B("Piano Terra (Area ARANCIO)"),
    AREA_C("Piano Primo (Area VERDE)"),
    AREA_D("Piano Secondo (Area ROSSA)"),
    AREA_E("Piano Primo (Area GIALLA)"),
    AREA_F("Piano Seminterrato (Area GRIGIA)"),
    ;

    public final String description;

    ERoomArea(String description) {
        this.description = description;
    }

    public static ERoomArea searchByDescription(String d) {
        for (ERoomArea roomLocation : values()) {
            if (d.equalsIgnoreCase(roomLocation.description)) return roomLocation;
        }
        return null;
    }
}
