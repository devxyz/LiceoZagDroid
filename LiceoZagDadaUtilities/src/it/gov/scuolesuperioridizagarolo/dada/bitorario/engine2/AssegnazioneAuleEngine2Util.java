package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine2;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezioneManipulation;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AssegnazioneAuleEngine2Util {
    static class BitOrarioOraLezione_AuleCompatibili {
        public BitOrarioOraLezione getLezione() {
            return lezione;
        }

        public EGiorno getGiorno() {
            EGiorno giorno = lezione.getGiorno();
            if (giorno == null) throw new NullPointerException(this.toString());
            return giorno;
        }

        public EOra getOra() {
            EOra ora = lezione.getOra();
            if (ora == null) throw new NullPointerException(this.toString());
            return ora;
        }

        public int getAuleSize() {
            return aule.size();
        }

        public ArrayList<RoomData> getAule() {
            return aule;
        }

        final BitOrarioOraLezione lezione;
        final ArrayList<RoomData> aule = new ArrayList<>();

        BitOrarioOraLezione_AuleCompatibili(BitOrarioOraLezione lezione) {
            this.lezione = lezione;
        }

        public void sortByCapacity() {
            Collections.sort(aule, Comparator.comparing(RoomData::getMaxStudents).thenComparing(RoomData::simpleName));
        }
    }

    /**
     * per ogni lezione restituisce le aule compatibili
     *
     * @param lezioni
     * @param vincoliStandard
     * @return
     */
    static ArrayList<BitOrarioOraLezione_AuleCompatibili> auleCompatibili(
            BitOrarioGrigliaOrario orario,
            ArrayList<BitOrarioOraLezione> lezioni,
            LessonConstraintContainer vincoliStandard) {

        ArrayList<BitOrarioOraLezione_AuleCompatibili> ris = new ArrayList<>();
        for (BitOrarioOraLezione l : lezioni) {

            //eventuale aula gia' assegnata
            if (l.getAula() != null) {
                if (!vincoliStandard.checkAll(l, orario)) {
                    //aula assegnata non valida, viene annullata e si cerca quella compatibile
                    BitOrarioOraLezioneManipulation.setAula(l, null);
                } else {
                    //aula forzata (non serve generare altre aule
                    ris.add(new BitOrarioOraLezione_AuleCompatibili(l));
                    continue;
                }
            }


            BitOrarioOraLezione_AuleCompatibili aule_compatibili = new BitOrarioOraLezione_AuleCompatibili(l);
            ris.add(aule_compatibili);

            for (RoomData r : RoomData.values()) {
                if (r.isAulaLaboratorioPalestra())
                    continue;

                if (r.isAulaFittizia())
                    continue;

                BitOrarioOraLezioneManipulation.setAula(l, r);
                if (vincoliStandard.checkAll(l, orario)) {
                    aule_compatibili.aule.add(r);
                }
                BitOrarioOraLezioneManipulation.setAula(l, null);
            }
            aule_compatibili.sortByCapacity();

        }
        return ris;
    }

    static boolean adiacenti(BitOrarioOraLezione prev, BitOrarioOraLezione lezione) {

        if (prev.getGiorno() != lezione.getGiorno()) return false;
        if (prev.getOra().getProgressivOra() != lezione.getOra().getProgressivOra() - 1) return false;
        if (!prev.getDocentePrincipale().equals(lezione.getDocentePrincipale())) return false;
        if (prev.getAula() == null) return false;

        if (prev.getAula().isAulaLaboratorioPalestra()) return false;

        return true;

    }
}
