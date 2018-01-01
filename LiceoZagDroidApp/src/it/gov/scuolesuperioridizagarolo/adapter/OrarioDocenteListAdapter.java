package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.enum_values.EGiorno;
import dada.bitorario.data.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.adapter.api.AbstractOrarioListAdapter;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;

/**
 * Created by stefano on 17/12/2017.
 */
public class OrarioDocenteListAdapter extends AbstractOrarioListAdapter {

    private String docente;

    public OrarioDocenteListAdapter(Activity a, String docente, BitOrarioGrigliaOrarioContainer orario, OnlyDate giorno) {
        super(a, orario, giorno, false, true, true);
        this.docente = docente;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    @Override
    public BitOrarioOraLezione getItem(int position) {
        int ora = position;
        //lezione
        final EOra ora1 = EOra.values()[ora];
        final BitOrarioOraLezione lezioneInClasse = orario.getLezioneConDocente(ora1, giorno.getGiorno(), docente);
        return lezioneInClasse;
    }


}
