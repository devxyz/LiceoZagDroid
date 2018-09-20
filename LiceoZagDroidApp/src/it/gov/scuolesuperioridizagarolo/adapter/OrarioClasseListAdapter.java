package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Collections;
import java.util.List;

/**
 * Created by stefano on 17/12/2017.
 */
public class OrarioClasseListAdapter extends AbstractOrarioListAdapter {

    private ClassData classe;

    public OrarioClasseListAdapter(Activity a, ClassData classe, BitOrarioGrigliaOrarioContainer orario, OnlyDate giorno) {
        super(a, orario, giorno, true, false, true);
        this.classe = classe;
    }

    public ClassData getClasse() {
        return classe;
    }

    public void setClasse(ClassData classe) {
        this.classe = classe;
    }


    @Override
    public List<BitOrarioOraLezione> getItem(BitOrarioGrigliaOrario o, EGiorno giorno, EOra ora) {
        final BitOrarioOraLezione lezioneInClasse = o.getLezioneInClasse(ora, giorno, classe);
        return Collections.singletonList(lezioneInClasse);
    }
}
