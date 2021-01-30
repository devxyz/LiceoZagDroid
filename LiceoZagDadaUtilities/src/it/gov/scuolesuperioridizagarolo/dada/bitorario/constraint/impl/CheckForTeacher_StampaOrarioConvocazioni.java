package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForTeacher;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForTeacher_StampaOrarioConvocazioni extends CheckForTeacher {
    @Override
    protected String check(BitOrarioGrigliaOrario l, String nomeDocente) {
        StringBuilder sb = new StringBuilder();

        //per ogni classe il n_classe di segnalazioni
        Map<ClassData, ArrayList<String>> ris = new TreeMap<>();

        for (EGiorno e : EGiorno.values()) {

            if (!e.flagGiornoDiLezione()) continue;
            final ArrayList<BitOrarioOraLezione> lezioneConDocente = l.getLezioneConDocente(nomeDocente, e);

            Map<ClassData, Integer> lezioniPerClasse = new TreeMap<>();
            for (int i = 0; i < lezioneConDocente.size(); i++) {
                if (lezioneConDocente.get(i).getClasse() == null) continue;

                final ClassData classe = lezioneConDocente.get(i).getClasse();
                int v = lezioniPerClasse.get(classe) == null ? 0 : lezioniPerClasse.get(classe);
                lezioniPerClasse.put(classe, v + 1);
            }


        }

        return sb.toString();
    }
}
