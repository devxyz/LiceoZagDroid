package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForTeacher;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.util.*;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForTeacher_LezioniMateriaRipetute extends CheckForTeacher {

    private static class Chiave implements Comparable<Chiave> {
        final ClassData classe;
        final EGiorno giorno;

        private Chiave(ClassData classe, EGiorno giorno) {
            this.classe = classe;
            this.giorno = giorno;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Chiave chiave = (Chiave) o;

            if (classe != chiave.classe) return false;
            return giorno == chiave.giorno;
        }


        @Override
        public int compareTo(Chiave o) {
            int i = this.classe.compareTo(o.classe);
            if (i != 0) return i;
            return this.giorno.compareTo(o.giorno);
        }
    }

    @Override
    protected String check(BitOrarioGrigliaOrario l, String nomeDocente) {
        StringBuilder sb = new StringBuilder();

        //per ogni classe il n_classe di segnalazioni

        Map<Chiave, ArrayList<BitOrarioOraLezione>> ris = new TreeMap<>();


        final ArrayList<BitOrarioOraLezione> lezioneConDocente = l.getLezioneConDocente(nomeDocente);
        for (BitOrarioOraLezione ll : lezioneConDocente) {
            if (ll.getClasse() == null) continue;
            Chiave c = new Chiave(ll.getClasse(), ll.getGiorno());
            ArrayList<BitOrarioOraLezione> lezioni = ris.get(c);
            if (lezioni == null) {
                ris.put(c, lezioni = new ArrayList<>());
            }
            lezioni.add(ll);
        }

        for (Map.Entry<Chiave, ArrayList<BitOrarioOraLezione>> e : ris.entrySet()) {
            Chiave c = e.getKey();
            if (e.getValue().size() > 2) {
                sb.append("LEZIONE " + c.classe + " " + c.giorno + ": " + e.getValue().size() + " ore " + e.getValue() + "\n");
            }
        }

        return sb.toString();
    }
}
