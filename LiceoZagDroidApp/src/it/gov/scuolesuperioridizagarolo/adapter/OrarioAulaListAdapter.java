package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;

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
    public BitOrarioOraLezione getItem(BitOrarioGrigliaOrario o, int position) {
        int ora = position;
        //lezione
        final EOra ora1 = EOra.values()[ora];
        final List<BitOrarioOraLezione> lezioneInClasse = o.getLezioneInAula(ora1, giorno.getGiorno(), aula);
        if (lezioneInClasse.size()==0)return null;
        if (lezioneInClasse.size()==1)return lezioneInClasse.get(0);

        //@todo completare???
        return null;// lezioneInClasse;
    }


}
