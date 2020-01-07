package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForTeacher;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.util.ArrayList;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForTeacher_OreBucheTotali extends CheckForTeacher {
    @Override
    protected String check(BitOrarioGrigliaOrario l, String nomeDocente) {
        StringBuilder sb = new StringBuilder();
        int buche = 0;
        for (EGiorno e : EGiorno.values()) {
            if (!e.flagGiornoDiLezione()) continue;
            final ArrayList<BitOrarioOraLezione> lezioneConDocente = l.getLezioneConDocente(e, nomeDocente);
            int count = 0;
            BitOrarioOraLezione prec = null;
            for (BitOrarioOraLezione x : lezioneConDocente) {
                if (x.getClasse() != null)
                    count++;

                if (prec != null) {
                    buche += x.getOra().getProgressivOra() - prec.getOra().getProgressivOra() - 1;

                }

                prec = x;
            }

        }
        if (buche >= 3) {
            sb.append("\n- " + buche + " ore di buco settimanali");
        }
        return sb.toString();
    }
}
