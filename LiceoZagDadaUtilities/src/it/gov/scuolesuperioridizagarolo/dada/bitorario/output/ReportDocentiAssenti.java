package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ReportDocentiAssenti {
    public static Map<EOra, ArrayList<BitOrarioOraLezione>> report(BitOrarioGrigliaOrario orarioTotale, EGiorno giorno, String... assenti) {
        Map<EOra, ArrayList<BitOrarioOraLezione>> lezioniDaCoprire = new TreeMap<>();
        for (EOra value : EOra.values()) {
            if (value.flagOraDiLezione())
                lezioniDaCoprire.put(value, new ArrayList<>());
        }

        for (String a : assenti) {
            for (EOra value : EOra.values()) {
                if (value.flagOraDiLezione()) {
                    BitOrarioOraLezione lezione = orarioTotale.getLezioneConDocente(a, giorno, value);
                    if (lezione != null && lezione.getTipoLezione() != BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                        lezioniDaCoprire.get(value).add(lezione);
                    }
                }
            }

        }
        return lezioniDaCoprire;
    }

    public static String stampaReport(Map<EOra, ArrayList<BitOrarioOraLezione>> r) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<EOra, ArrayList<BitOrarioOraLezione>> e : r.entrySet()) {
            sb.append(e.getKey()).append("\n");
            for (BitOrarioOraLezione lezione : e.getValue()) {
                sb.append("  "+lezione.toStringShort()).append("\n");
            }
        }
        return sb.toString();
    }
}
