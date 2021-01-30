package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.ddi;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.*;

//assegna le postazioni disponibili per la DDI
public class AssegnaPostazioniDDIOrario {
    public static class KeyGiornoOra {
        final EGiorno giorno;
        final EOra ora;

        private KeyGiornoOra(EGiorno giorno, EOra ora) {
            this.giorno = giorno;
            this.ora = ora;
        }

        @Override
        public String toString() {
            return "<" +
                    "" + giorno +
                    ", " + ora +
                    '>';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyGiornoOra that = (KeyGiornoOra) o;
            return giorno == that.giorno && ora == that.ora;
        }

        @Override
        public int hashCode() {
            return Objects.hash(giorno, ora);
        }
    }

    /**
     * assegna le postazioni DDI disponibili in ogni ora, seguendo l'ordine .
     * In particolare è possibile specificare piu' volte la stessa postazione se si vuole utilizzarla per piu' docenti
     *
     * @param orario
     * @param postazioniDDiUtilizzabili
     */
    public static void assegna(BitOrarioGrigliaOrario orario, Map<EGiorno, List<RoomData>> postazioniDDiUtilizzabili) {
        //per ogni ora e giorno ho l'elenco delle postazioni utilizzabili
        Map<KeyGiornoOra, LinkedList<RoomData>> postazioniDisponibili = new HashMap<>();
        for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
            for (EOra o : EOra.valuesOreDiLezione()) {
                postazioniDisponibili.put(new KeyGiornoOra(g, o), new LinkedList<>(postazioniDDiUtilizzabili.get(g)));
            }
        }


        ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(orario.getLezioni());
        //controlla solo le lezioni in DDI
        for (BitOrarioOraLezione l : lezioni) {
            if (l.getAula() == null) continue;
            if (!l.getAula().isDDI()) continue;

            EGiorno g = l.getGiorno();
            EOra o = l.getOra();
            LinkedList<RoomData> disp = postazioniDisponibili.get(new KeyGiornoOra(g, o));
            if (disp.size() == 0)
                throw new IllegalArgumentException("Nessuna postazione DDI rimasta per " + g + " " + o + ". Postazioni iniziali: " + disp);
            BitOrarioOraLezione l2 = l.clonaLezioneInAltraAula(disp.removeFirst());
            orario.removeLezione(l);
            orario.addLezione(l2);
        }

    }

    private static boolean adiacenteColPrecedente_docenti(ArrayList<BitOrarioOraLezione> lezioni, int index) {
        if (index == 0) return false;
        BitOrarioOraLezione current = lezioni.get(index);
        BitOrarioOraLezione prec = lezioni.get(index - 1);
        if (
                current.getDocentePrincipale().equals(prec.getDocentePrincipale())
                        &&
                        current.getGiorno().equals(prec.getGiorno())
                        &&
                        current.getOra().prev() == (prec.getOra())
        ) {
            return true;
        }
        return false;
    }

    /**
     * assegna le postazioni DDI disponibili in ogni ora, seguendo l'ordine .
     * In particolare è possibile specificare piu' volte la stessa postazione se si vuole utilizzarla per piu' docenti
     *
     * @param orario
     * @param postazioniDDiUtilizzabili
     */
    public static void assegnaVers2(BitOrarioGrigliaOrario orario, Map<EGiorno, List<RoomData>> postazioniDDiUtilizzabili, Random r, int numTentativi) {
        Comparator<BitOrarioOraLezione> comparatorDocenti = Comparator.comparing(BitOrarioOraLezione::getDocentePrincipale).
                thenComparing(BitOrarioOraLezione::getGiorno).
                thenComparing(BitOrarioOraLezione::getOra);
        int errori__BEST = 0;
        ArrayList<RoomData> auleDDI__BEST = null;
        ArrayList<BitOrarioOraLezione> lezioniDDI__BEST = null;

        for (int tentativi = 0; tentativi < numTentativi; tentativi++) {
            int errori = 0;
            System.out.printf("ALLOCAZIONE AULE DDI - TENTATIVO %d\n", (tentativi + 1));
            //per ogni ora e giorno ho l'elenco delle postazioni utilizzabili
            Map<KeyGiornoOra, LinkedList<RoomData>> postazioniDisponibili = new HashMap<>();
            for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
                for (EOra o : EOra.valuesOreDiLezione()) {
                    //disordina le aule disponibili per ogni giorno
                    ArrayList<RoomData> roomData = new ArrayList<>(postazioniDDiUtilizzabili.get(g));
                    Collections.shuffle(roomData, r);
                    postazioniDisponibili.put(new KeyGiornoOra(g, o), new LinkedList<>(roomData));
                }
            }


            ArrayList<BitOrarioOraLezione> lezioniDDI = new ArrayList<>();
            ArrayList<RoomData> auleDDI = new ArrayList<>();
            for (BitOrarioOraLezione l : orario.getLezioni()) {
                if (l.getAula() == null) continue;
                if (!l.getAula().isDDI()) continue;
                lezioniDDI.add(l);
            }

            //ordina per docente e ora
            Collections.sort(lezioniDDI, comparatorDocenti);


            boolean soluzioneErrata = false;

            //scorre tutte le lezioni e se sono contigue usa, se possibile, la stessa aula
            for (int i = 0; i < lezioniDDI.size() && !soluzioneErrata; i++) {
                BitOrarioOraLezione l = lezioniDDI.get(i);
                EGiorno g = l.getGiorno();
                EOra o = l.getOra();
                LinkedList<RoomData> disp = postazioniDisponibili.get(new KeyGiornoOra(g, o));

                if (adiacenteColPrecedente_docenti(lezioniDDI, i)) {
                    RoomData prec_aula = auleDDI.get(i - 1);
                    if (disp.contains(prec_aula)) {
                        auleDDI.add(prec_aula);
                        disp.remove(prec_aula);
                    } else {
                        errori++;
                        auleDDI.add(disp.removeFirst());
                    }
                } else {
                    if (disp.size() == 0)
                        throw new IllegalArgumentException("Nessuna postazione DDI rimasta per " + g + " " + o + ". Postazioni iniziali: " + disp);
                    //prende la prima aula disponibile
                    auleDDI.add(disp.removeFirst());
                }
            }

            if (lezioniDDI__BEST == null || errori__BEST > errori) {
                lezioniDDI__BEST = lezioniDDI;
                auleDDI__BEST = auleDDI;
                errori__BEST = errori;
                System.out.printf("  Nuovo record > Errori %d\n", errori);
            }

        }

        if (lezioniDDI__BEST != null) {

            //controlla solo le lezioni in DDI
            for (int i = 0; i < lezioniDDI__BEST.size(); i++) {
                BitOrarioOraLezione l = lezioniDDI__BEST.get(i);
                RoomData roomData = auleDDI__BEST.get(i);

                BitOrarioOraLezione l2 = l.clonaLezioneInAltraAula(roomData);
                orario.removeLezione(l);
                orario.addLezione(l2);
            }
        } else
            throw new IllegalArgumentException("Nessuna soluzione trovata");
        //return;

    }
}
