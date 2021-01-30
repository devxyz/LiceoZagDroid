package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.*;

public class OrarioDocentiComplessivo {
    final Map<String, OrarioDocenteSettimanale> lezioni;

    public OrarioDocentiComplessivo() {
        this.lezioni = new TreeMap<>();
    }

    public String computeReportDisposizioniDaSpostare(OrarioDocentiComplessivo_SkipAlertReportDisposizioni skip, boolean showCode) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, OrarioDocenteSettimanale> e : lezioni.entrySet()) {
            String docente = e.getKey();
            Set<EGiorno> giorniDispoDaSpostare = new TreeSet<>();
            Set<EGiorno> giorniFullDDI = new TreeSet<>();
            StringBuilder code = new StringBuilder();
            OrarioDocenteSettimanale orarioSettimanale = e.getValue();

            //spostaDisposizioni(orarioTotale, "BORRELLO", EGiorno.VENERDI, EOra.SECONDA, EGiorno.MERCOLEDI, EOra.QUARTA);
            int disposizioniIndividuateDaSpostare = 0;
            for (EGiorno g : orarioSettimanale.getGiorniLezione()) {
                OrarioDocenteGiornaliero orarioGiornaliero = orarioSettimanale.getOrarioGiornaliero(g);
                if (orarioGiornaliero.isFullDDI() && orarioGiornaliero.getNumDISPO() > 0) {

                    for (OrarioDocenteBitOrarioOraLezione l : orarioGiornaliero.lezioni.values()) {
                        if (skip.isIgnored(l.lezione)) continue;

                        giorniDispoDaSpostare.add(g);
                        if (l.lezione.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                            code.append("spostaDisposizioni(orarioTotale, \"" + docente + "\", EGiorno." + g.name() +
                                    ", EOra." + l.lezione.getOra().name() + ", EGiorno., EOra.);\n");
                            disposizioniIndividuateDaSpostare++;
                        }
                    }

                }
                if (orarioGiornaliero.isFullDDI()) {
                    giorniFullDDI.add(g);
                }
            }
            Set<EGiorno> giorniLezioneNonDDI = new TreeSet<>(orarioSettimanale.getGiorniLezione());
            giorniLezioneNonDDI.removeAll(giorniFullDDI);

            if (disposizioniIndividuateDaSpostare > 0) {
                sb.append("  //Docente: " + docente).append("\n");
                sb.append("  // >> GIORNI DISPOSIZIONI DA SPOSTARE: " + giorniDispoDaSpostare).append("\n");
                //sb.append(" >> GIORNI FULL DDI da non utilizzare: " + giorniFullDDI).append("\n");
                sb.append("  // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): " + giorniLezioneNonDDI).append("\n");
                if (showCode)
                    sb.append(code);
            }
        }
        return sb.toString();
    }

    /**
     * numero di lezioni DDI da scuola per giorno e per ora
     *
     * @return
     */
    public MatriceGiorniOre computeDDI_daScuola() {
        MatriceGiorniOre m = new MatriceGiorniOre();
        Set<String> docenti = getDocenti();
        //per ogni docente
        for (String d : docenti) {
            OrarioDocenteSettimanale orarioDocenteSettimanale = getOrarioDocenteSettimanale(d);
            //per ogni giorno
            for (EGiorno g : orarioDocenteSettimanale.getGiorniLezione()) {
                OrarioDocenteGiornaliero orarioGiornaliero = orarioDocenteSettimanale.getOrarioGiornaliero(g);
                //salta se l'insegnante è a casa
                if (orarioGiornaliero.isFullDDI()) continue;

                //per ogni ora di lezione
                for (EOra o : orarioDocenteSettimanale.getOreLezione(g)) {

                    //se ora di lezione in ddi
                    OrarioDocenteBitOrarioOraLezione l = orarioGiornaliero.get(o);
                    if (l.ddi) {
                        m.increment(g, o, 1);
                    }
                }
            }
        }
        return m;
    }

    /**
     * numero di lezioni DDI da casa per giorno e per ora
     *
     * @return
     */
    public MatriceGiorniOre computeDDI_daCasa() {
        MatriceGiorniOre m = new MatriceGiorniOre();
        Set<String> docenti = getDocenti();
        //per ogni docente
        for (String d : docenti) {
            OrarioDocenteSettimanale orarioDocenteSettimanale = getOrarioDocenteSettimanale(d);
            //per ogni giorno
            for (EGiorno g : orarioDocenteSettimanale.getGiorniLezione()) {
                OrarioDocenteGiornaliero orarioGiornaliero = orarioDocenteSettimanale.getOrarioGiornaliero(g);
                //salta se l'insegnante è a casa
                if (!orarioGiornaliero.isFullDDI()) continue;

                //per ogni ora di lezione
                for (EOra o : orarioDocenteSettimanale.getOreLezione(g)) {

                    //se ora di lezione in ddi
                    OrarioDocenteBitOrarioOraLezione l = orarioGiornaliero.get(o);
                    if (l.ddi) {
                        m.increment(g, o, 1);
                    }
                }
            }
        }
        return m;
    }

    public int getGiorniFullDDITotali() {
        Set<String> docenti = getDocenti();
        int docentiInFullDDI = 0;
        for (String d : docenti) {
            OrarioDocenteSettimanale orarioDocente = getOrarioDocenteSettimanale(d);
            //System.out.println(d);
            for (EGiorno g : orarioDocente.getGiorniLezione()) {
                OrarioDocenteGiornaliero lezioniGiorno = orarioDocente.getOrarioGiornaliero(g);
                if (lezioniGiorno.isFullDDI()) {
                    //System.out.println(" " + g);
                    docentiInFullDDI++;
                }
            }
        }

        return docentiInFullDDI;
    }

    public void addAll(Collection<BitOrarioOraLezione> lll) {
        for (BitOrarioOraLezione l : lll) {
            String docente = l.getDocentePrincipale();
            OrarioDocenteSettimanale orarioDocente = lezioni.computeIfAbsent(docente, k -> new OrarioDocenteSettimanale());
            orarioDocente.add(l);
        }
    }

    public OrarioDocenteSettimanale getOrarioDocenteSettimanale(String docente) {
        return lezioni.get(docente);
    }

    public Set<String> getDocenti() {
        return lezioni.keySet();
    }

    public void assignDDI(IClassDataDDI ddi) {
        for (OrarioDocenteSettimanale x : lezioni.values()) {
            x.assignDDI(ddi);
        }
    }
}
