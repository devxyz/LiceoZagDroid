package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.enum_values.EGiorno;
import dada.bitorario.data.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;

import java.util.Collections;
import java.util.List;

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
    public List<BitOrarioOraLezione> getItem(BitOrarioGrigliaOrario o, EGiorno giorno, EOra ora) {
        final BitOrarioOraLezione lezioneInClasse = o.getLezioneConDocente(ora, giorno, docente);
        return Collections.singletonList(lezioneInClasse);

    }
}
