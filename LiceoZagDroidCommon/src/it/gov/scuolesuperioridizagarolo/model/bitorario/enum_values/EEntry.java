package it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values;

/**
 * Created by stefano on 24/11/2017.
 */
public enum EEntry {
    ENTRY_NONE(0, "-"),
    ENTRY_1(1, "Entrata Principale"),
    ENTRY_2(2, "Entrata Principale"),
    ENTRY_3(3, "Entrata Principale"),
    ENTRY_4(4, "Entrata Primo Piano Ala Est"),
    ENTRY_5(5, "Entrata Piano Terra Ala Est"),
    ;

    private int number;
    public final String description;

    EEntry(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int number() {
        return number;
    }

    public static EEntry searchByDescription(String d) {
        for (EEntry roomLocation : values()) {
            if (d.equalsIgnoreCase(roomLocation.description)) return roomLocation;
        }
        return null;
    }
}
