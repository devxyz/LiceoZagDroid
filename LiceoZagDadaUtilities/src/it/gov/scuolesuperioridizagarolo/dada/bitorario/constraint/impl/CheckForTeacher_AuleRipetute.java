package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForTeacher;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForTeacher_AuleRipetute extends CheckForTeacher {
    @Override
    protected String check(BitOrarioGrigliaOrario l, String nomeDocente) {
        StringBuilder sb = new StringBuilder();

        //per ogni classe il numero di segnalazioni

        for (EGiorno e : EGiorno.values()) {
            if (!e.flagGiornoDiLezione()) continue;
            for (EOra o : EOra.values()) {
                if (!o.flagOraDiLezione()) continue;

                final BitOrarioOraLezione lezione = l.getLezioneConDocente(o, e, nomeDocente);
                final BitOrarioOraLezione lezioneSuccessiva = l.getLezioneConDocente(o.next(), e, nomeDocente);
                if (lezione == null || lezioneSuccessiva == null) continue;
                if (lezione.getClasse() == null || lezioneSuccessiva.getClasse() == null) continue;
                if (lezione.getNomeAula() == null || lezioneSuccessiva.getNomeAula() == null) continue;
                if (
                        lezione.getClasse().equalsIgnoreCase(lezioneSuccessiva.getClasse()) &&
                        lezione.getMateriaPrincipale().equalsIgnoreCase(lezioneSuccessiva.getMateriaPrincipale()) &&
                                !lezione.getNomeAula().equalsIgnoreCase(lezioneSuccessiva.getNomeAula())
                        ) {
                    sb.append("Lezioni di " + nomeDocente + " della classe " + lezione.getClasse() + "(" + e + " " + o + " e " + o.next() + " ora) consecutive in aule differenti: " +
                            lezione.getNomeAula() + " e " + lezioneSuccessiva.getNomeAula());

                }

            }


        }


        return sb.toString();
    }
}
