package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Set;
import java.util.TreeSet;

public class OrarioDocentiComplessivo_SkipAlertReportDisposizioni {
    private Set<String> docente_giorno_ora_da_ignorare = new TreeSet<>();

    public void ignore(String docente, EGiorno giorno, EOra ora) {
        docente_giorno_ora_da_ignorare.add(composeValue(docente, giorno, ora));
    }

    public String composeValue(String docente, EGiorno giorno, EOra ora) {
        return docente.trim().toUpperCase() + "_" + giorno.name() + "_" + ora.name();
    }


    public void ignore(BitOrarioOraLezione... ll) {
        for (BitOrarioOraLezione l : ll) {
            if (l.getTipoLezione() != BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                throw new IllegalArgumentException("Lezione " + l + " non di disposizione");
            }
            ignore(l.getDocentePrincipale(), l.getGiorno(), l.getOra());

        }
    }

    public boolean isIgnored(BitOrarioOraLezione l) {
        return isIgnored(l.getDocentePrincipale(), l.getGiorno(), l.getOra());
    }

    public boolean isIgnored(String docente, EGiorno giorno, EOra ora) {
        return docente_giorno_ora_da_ignorare.contains(composeValue(docente, giorno, ora));
    }
}
