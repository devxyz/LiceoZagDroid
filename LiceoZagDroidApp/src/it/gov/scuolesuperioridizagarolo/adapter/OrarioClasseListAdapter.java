package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;

/**
 * Created by stefano on 17/12/2017.
 */
public class OrarioClasseListAdapter extends AbstractOrarioListAdapter {

    private String classe;

    public OrarioClasseListAdapter(Activity a, String classe, BitOrarioGrigliaOrarioContainer orario, OnlyDate giorno) {
        super(a, orario, giorno, true, false, true);
        this.classe = classe;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }


    @Override
    public BitOrarioOraLezione getItem(BitOrarioGrigliaOrario o, int position) {
        int ora = position;
        //lezione
        final EOra ora1 = EOra.values()[ora];
        final BitOrarioOraLezione lezioneInClasse = o.getLezioneInClasse(ora1, giorno.getGiorno(), classe);
        return lezioneInClasse;
    }


}
