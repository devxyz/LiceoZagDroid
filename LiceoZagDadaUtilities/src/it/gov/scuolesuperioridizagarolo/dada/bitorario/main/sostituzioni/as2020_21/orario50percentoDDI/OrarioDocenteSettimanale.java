package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class OrarioDocenteSettimanale {
    private final Map<EGiorno, OrarioDocenteGiornaliero> lezioniDocente;

    public OrarioDocenteSettimanale() {
        this.lezioniDocente = new TreeMap<>();
    }

    public void add(BitOrarioOraLezione l) {
        EGiorno giorno = l.getGiorno();
        OrarioDocenteGiornaliero l1 = lezioniDocente.computeIfAbsent(giorno, k -> new OrarioDocenteGiornaliero());
        l1.add(l);
    }

    final static OrarioDocenteGiornaliero defaultValue = new OrarioDocenteGiornaliero();

    public OrarioDocenteBitOrarioOraLezione getOrarioLezione(EGiorno g, EOra ora) {
        return lezioniDocente.getOrDefault(g, defaultValue).get(ora);
    }

    public OrarioDocenteGiornaliero getOrarioGiornaliero(EGiorno g) {
        return lezioniDocente.getOrDefault(g, defaultValue);
    }

    public Set<EOra> getOreLezione(EGiorno g) {
        return lezioniDocente.getOrDefault(g, defaultValue).lezioni.keySet();
    }

    public Set<EGiorno> getGiorniLezione() {
        return lezioniDocente.keySet();
    }

    public void assignDDI(IClassDataDDI ddi) {
        for (OrarioDocenteGiornaliero x : lezioniDocente.values()) {
            x.assignDDI(ddi);
        }
    }
}
