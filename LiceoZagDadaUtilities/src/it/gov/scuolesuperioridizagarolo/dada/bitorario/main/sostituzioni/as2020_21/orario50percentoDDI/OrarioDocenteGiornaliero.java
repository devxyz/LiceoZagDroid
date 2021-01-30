package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class OrarioDocenteGiornaliero {
    final Map<EOra, OrarioDocenteBitOrarioOraLezione> lezioni;
    private int numDDI = 0;

    public int getNumDDI() {
        return numDDI;
    }

    public int getNumDISPO() {
        return numDISPO;
    }

    private int numDISPO = 0;

    public boolean isFullDDI() {
        return numDDI + numDISPO == lezioni.size() && lezioni.size() > 0 && numDDI > 0;
    }

    OrarioDocenteGiornaliero() {
        lezioni = new TreeMap<>();
    }

    public void add(BitOrarioOraLezione l) {
        lezioni.put(l.getOra(), new OrarioDocenteBitOrarioOraLezione(l));
    }

    public Set<EOra> getOre() {
        return lezioni.keySet();
    }

    public OrarioDocenteBitOrarioOraLezione get(EOra o) {
        return lezioni.get(o);
    }

    public void assignDDI(IClassDataDDI ddi) {
        numDDI = 0;
        numDISPO = 0;
        for (OrarioDocenteBitOrarioOraLezione l : lezioni.values()) {

            if (l.lezione.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                l.ddi = false;
                numDISPO++;
                continue;
            }

            BitOrarioOraLezione lx = l.lezione;
            if (ddi.isDDI(lx.getClasse(), lx.getGiorno())) {
                l.ddi = true;
                numDDI++;
            } else {
                l.ddi = false;
            }
        }
    }
}
