package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.adapter.OrarioDocenteListAdapter;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

import java.util.TreeSet;

/**
 * Created by stefano on 23/12/2017.
 */
public class OrarioDocenteNonPersistenteFragment extends AbstractOrarioFragment<OrarioDocenteListAdapter> {
    @Override
    protected OrarioDocenteListAdapter createAbstractOrarioListAdapter() {
        return new OrarioDocenteListAdapter(getMainActivity(), filtro, containerOrari, giornoCorrente);
    }

    @Override
    protected String[] getFilterValues() {
        final TreeSet<String> docenti = orario.getDocenti();
        return docenti.toArray(new String[docenti.size()]);
    }

    @Override
    protected void saveFiltrerValue(String filtro) {

    }

    @Override
    protected String getSavedFiltrerValue() {
        return null;
    }

    @Override
    protected boolean normalizeFilterName() {
        return true;
    }

    @Override
    protected void updateAdapter(String filter) {
        orarioAdapter.setDocente(filter);
    }

    @Override
    protected String getFilterDialogLabel() {
        return "docente";
    }

    @Override
    protected String getFilterAppLabel() {
        return "";
    }

}
