package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.List;

/**
 * Created by stefano on 17/12/2017.
 */
public class OrarioAulaListAdapter extends AbstractOrarioListAdapter {

    private String aula;

    public OrarioAulaListAdapter(Activity a, String aula, BitOrarioGrigliaOrarioContainer orario, OnlyDate giorno) {
        super(a, orario, giorno, true, true, true);
        this.aula = aula;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }


    @Override
    public List<BitOrarioOraLezione> getItem(BitOrarioGrigliaOrario o, EGiorno giorno, EOra ora) {

        final List<BitOrarioOraLezione> lezioneInClasse = o.getLezioneInAula(ora, giorno, aula);
        if (lezioneInClasse.size() == 0) {
            lezioneInClasse.add(null);
        }
        return lezioneInClasse;
    }


}
