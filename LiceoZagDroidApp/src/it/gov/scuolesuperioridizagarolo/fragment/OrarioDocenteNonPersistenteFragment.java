package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.adapter.OrarioDocenteListAdapter;

import java.util.TreeSet;

/**
 * Created by stefano on 23/12/2017.
 */
public class OrarioDocenteNonPersistenteFragment extends AbstractOrarioFragment<OrarioDocenteListAdapter, String> {
    @Override
    protected OrarioDocenteListAdapter createAbstractOrarioListAdapter() {
        return new OrarioDocenteListAdapter(getMainActivity(), filtro, containerOrari, giornoCorrente);
    }

    @Override
    protected String[] getFilterValues() {
        final TreeSet<String> docenti = new TreeSet<>(containerOrari.getDocenti());
        return docenti.toArray(new String[docenti.size()]);
    }

    @Override
    protected void persistFiltrerValue(String filtro) {

    }

    @Override
    protected String retrievePersistedFiltrerValue() {
        return null;
    }

    @Override
    protected boolean isTimetableForTeacher() {
        return true;
    }

    @Override
    protected boolean isTimetableForStudents() {
        return false;
    }

    @Override
    protected boolean isTimetableForRooms() {
        return false;
    }

    @Override
    protected String filterToLabel(String filter) {
        return filter;
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
