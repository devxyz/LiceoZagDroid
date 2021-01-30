package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

public abstract class FastSetRoomDataGrid {

    //giorno - ora
    private FastSetRoomData[][] orario;

    public FastSetRoomDataGrid() {
        this.orario = new FastSetRoomData[EGiorno.values().length][EOra.values().length];
        for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
            for (EOra o : EOra.valuesOreDiLezione()) {
                orario[g.ordinal()][o.ordinal()] = new FastSetRoomData_ImplTreeSet();
            }
        }
    }

    public FastSetRoomData get(EGiorno g, EOra o) {
        return orario[g.ordinal()][o.ordinal()];
    }

    public FastSetRoomData[][] getOrario() {
        return orario;
    }

    protected abstract FastSetRoomData newInstance();

}

