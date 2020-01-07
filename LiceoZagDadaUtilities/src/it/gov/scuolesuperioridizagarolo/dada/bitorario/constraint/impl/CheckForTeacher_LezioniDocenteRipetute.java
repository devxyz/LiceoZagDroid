package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForTeacher;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForTeacher_LezioniDocenteRipetute extends CheckForTeacher {
    @Override
    protected String check(BitOrarioGrigliaOrario l, String nomeDocente) {
        StringBuilder sb = new StringBuilder();

        //per ogni classe il n_classe di segnalazioni
        Map<String, ArrayList<String>> ris = new TreeMap<>();

        for (EGiorno e : EGiorno.values()) {
            if (!e.flagGiornoDiLezione()) continue;

            final ArrayList<BitOrarioOraLezione> lezioneConDocente = l.getLezioneConDocente(e, nomeDocente);
            int count = 0;
            Map<String, Integer> lezioniPerClasse = new TreeMap<>();
            for (int i = 0; i < lezioneConDocente.size(); i++) {
                if (lezioneConDocente.get(i).getClasse() == null) continue;

                final String classe = lezioneConDocente.get(i).getClasse() + "-" + lezioneConDocente.get(i).getMateriaPrincipale();
                int v = lezioniPerClasse.get(classe) == null ? 0 : lezioniPerClasse.get(classe);
                lezioniPerClasse.put(classe, v + 1);
            }

            for (Map.Entry<String, Integer> x : lezioniPerClasse.entrySet()) {
                final String classe = x.getKey();
                String str = null;
                if (x.getValue() == 2) {
                    str = "\n- " + classe + " lezione svolta su " + x.getValue() + " ore (" + e + ")";
                    sb.append(str);
                } else if (x.getValue() > 2) {
                    str = "\n- " + classe + " lezione svolta su " + x.getValue() + " ore !!!!!!!!!!!!!!!!!! (" + e + ")";
                    sb.append(str);
                    count++;
                }
                if (!ris.containsKey(classe)) {
                    ris.put(classe, new ArrayList<String>());
                }
                if (str != null)
                    ris.get(classe).add(str);
            }


        }

        for (Map.Entry<String, ArrayList<String>> x : ris.entrySet()) {
            final ArrayList<String> a = x.getValue();
            Collections.sort(a);

            final String classe = x.getKey().split("-")[0];
            final String materia = x.getKey().split("-")[1];
            int oreTotali = l.getOreTotaliDocenteClasseMateria(nomeDocente, materia, classe);
            if (a.size() == 1) {
                sb.append("\n   ****** IN CLASSE " + classe + " durante la settimana " + x.getValue().size() + " volte ci sono ore raggruppate (da 2 o superiore) - ore totali: " + oreTotali);

            } else {
                if (a.size() > 0) {
                    sb.append("\n   ****** ORE_ECCEDENTI_MATERIA --- IN CLASSE " + classe + " durante la settimana " + x.getValue().size() + " volte ci sono ore raggruppate (da 2 o superiore) - ore totali: " + oreTotali);

                }
            }
        }
        return sb.toString();
    }
}
