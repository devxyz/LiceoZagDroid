package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForTeacher;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.util.ArrayList;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForTeacher_5oreAlGiorno extends CheckForTeacher {
    @Override
    protected String check(BitOrarioGrigliaOrario l, String nomeDocente) {
        StringBuilder sb = new StringBuilder();
        for (EGiorno e : EGiorno.values()) {
            if (!e.flagGiornoDiLezione()) continue;
            final ArrayList<BitOrarioOraLezione> lezioneConDocente = l.getLezioneConDocente(nomeDocente, e);
            int count = 0;
            for (BitOrarioOraLezione x : lezioneConDocente) {
                if (x.getClasse() != null)
                    count++;
            }
            if (count > 4) {
                sb.append("\n-" + e + " " + count + " ore di lezione");
            }

        }
        return sb.toString();
    }
}
