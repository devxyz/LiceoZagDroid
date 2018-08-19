package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;

import java.util.ArrayList;

/**
 * Created by stefano on 31/07/2018.
 */
public class RegolaCambioAula {
    public final ArrayList<BitOrarioOraLezione> nuoveLezioniDaAggiungere;
    public final ArrayList<BitOrarioOraLezione> vecchieLezioniDaRimuovere;
    public final String regola;

    @Override
    public String toString() {
        return "RegolaCambioAula:" +
                "\n  vecchieLezioniDaRimuovere=" + vecchieLezioniDaRimuovere +
                "\n  nuoveLezioniDaAggiungere=" + nuoveLezioniDaAggiungere ;
    }

    public RegolaCambioAula(String regola) {
        this.regola = regola;
        nuoveLezioniDaAggiungere = new ArrayList<>();
        vecchieLezioniDaRimuovere = new ArrayList<>();
    }

    public boolean check(BitOrarioGrigliaOrario o, LessonConstraintContainer l) {
        return l.checkAll(nuoveLezioniDaAggiungere, o);
    }

    public void apply(BitOrarioGrigliaOrario o) {
        for (BitOrarioOraLezione x : vecchieLezioniDaRimuovere) {
            o.removeLezione(x);
        }

        for (BitOrarioOraLezione x : nuoveLezioniDaAggiungere) {
            o.addLezione(x);
        }

    }

    public void removeLezione(BitOrarioOraLezione o) {
        vecchieLezioniDaRimuovere.add(o);
    }

    public void addLezione(BitOrarioOraLezione o) {
        nuoveLezioniDaAggiungere.add(o);
    }
}
