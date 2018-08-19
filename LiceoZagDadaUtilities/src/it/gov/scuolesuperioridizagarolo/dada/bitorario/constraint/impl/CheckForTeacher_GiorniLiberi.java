package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForTeacher;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForTeacher_GiorniLiberi extends CheckForTeacher {
    private TreeMap<EGiorno, Integer> giorni = new TreeMap<>();

    @Override
    protected void onStart(BitOrarioGrigliaOrario l) {
        giorni = new TreeMap<>();
        for (EGiorno x : EGiorno.values()) {
            if (!x.flagGiornoDiLezione()) continue;
            giorni.put(x, 0);
        }
    }


    @Override
    protected String check(BitOrarioGrigliaOrario l, String nomeDocente) {
        StringBuilder sb = new StringBuilder();
        int conta = 0;
        for (EGiorno g : EGiorno.values()) {

            if (!g.flagGiornoDiLezione()) continue;
            final ArrayList<BitOrarioOraLezione> lezioneConDocente = l.getLezioneConDocente(g, nomeDocente);
            if (lezioneConDocente.size() == 0) {
                conta++;
                sb.append("\n- giorno libero " + g);
                giorni.put(g, giorni.get(g) + 1);
            }
        }
        if (conta == 0) {
            return "NESSUN GIORNO LIBERO !!!!!!!!!!!!!!!!!!!!!!!!!!";
        }
        return sb.toString();
    }

    @Override
    protected void onFinish(BitOrarioGrigliaOrario l) {
        System.out.println("GIORNI LIBERI:");
        for (Map.Entry<EGiorno, Integer> e : giorni.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

    }
}
