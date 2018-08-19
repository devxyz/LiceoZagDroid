package it.gov.scuolesuperioridizagarolo.model.bitorario.griglia;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

/**
 * Created by stefano on 01/10/2017.
 */
public class GrigliaOrariaSingoloValore {
    private final BitOrarioOraLezione[][] orario;

    public GrigliaOrariaSingoloValore() {
        this.orario = new BitOrarioOraLezione[EOra.values().length][EGiorno.values().length];
    }

    public BitOrarioOraLezione get(EOra o, EGiorno g) {
        return orario[o.ordinal()][g.ordinal()];
    }

    public BitOrarioOraLezione set(BitOrarioOraLezione l) {

        final BitOrarioOraLezione old = orario[l.getOra().ordinal()][l.getGiorno().ordinal()];
        orario[l.getOra().ordinal()][l.getGiorno().ordinal()] = l;
        return old;
    }

}
