package it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values;

/**
 * Created by stefano on 24/11/2017.
 */
public enum ERoomArea {
    AREA_A("Piano Terra (lato Segreteria)"),
    AREA_B("Piano Terra (lato Presidenza)"),
    AREA_C("Piano Primo"),
    AREA_D("Piano Secondo"),
    AREA_E("Piano Primo Esterno"),
    AREA_F("Piano Seminterrato"),
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
