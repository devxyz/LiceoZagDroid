package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stefano on 16/02/2018.
 */
public class NoteVariazioniBitOrarioGrigliaOrario {
    private Map<BitOrarioOraLezione, String> mapNote = new HashMap<>();

    public static NoteVariazioniBitOrarioGrigliaOrario generateDifferenze(BitOrarioGrigliaOrario originale, BitOrarioGrigliaOrario modifica) {
        NoteVariazioniBitOrarioGrigliaOrario ris = new NoteVariazioniBitOrarioGrigliaOrario();

        final ArrayList<BitOrarioOraLezione> lezioni = modifica.getLezioni();
        for (BitOrarioOraLezione l : lezioni) {
            final BitOrarioOraLezione lezioneConDocente = originale.getLezioneConDocente(l.getOra(), l.getGiorno(), l.getDocentePrincipale());
            if (!BitOrarioOraLezione.saveEquals(l, lezioneConDocente)) {

                ris.mapNote.put(l, "** modificato");
            }
        }

        return ris;
    }


    public Map<BitOrarioOraLezione, String> getMapNote() {
        return Collections.unmodifiableMap(mapNote);
    }

    public String getNote(BitOrarioOraLezione o) {
        if (o == null) return null;
        return mapNote.get(o);
    }
}
