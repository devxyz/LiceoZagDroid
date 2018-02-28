package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.adapter.OrarioClasseListAdapter;

import java.util.TreeSet;

public class OrarioClasseNonPersistenteFragment extends AbstractOrarioFragment<OrarioClasseListAdapter> {

    @Override
    protected OrarioClasseListAdapter createAbstractOrarioListAdapter() {
        return new OrarioClasseListAdapter(getMainActivity(), filtro, containerOrari, giornoCorrente);
    }

    @Override
    protected String[] getFilterValues() {
        final TreeSet<String> classi = orario.getClassi();
        return classi.toArray(new String[classi.size()]);
    }

    @Override
    protected void saveFiltrerValue(String filtro) {
    }

    @Override
    protected String getSavedFiltrerValue() {
        return null;
    }

    @Override
    protected boolean isTimetableForTeacher() {
        return false;
    }

    @Override
    protected boolean isTimetableForStudents() {
        return true;
    }

    @Override
    protected boolean isTimetableForRooms() {
        return false;
    }

    @Override
    protected boolean normalizeFilterName() {
        return false;
    }

    @Override
    protected void updateAdapter(String filter) {
        super.orarioAdapter.setClasse(filter);
    }

    @Override
    protected String getFilterDialogLabel() {
        return "Classe";
    }

    @Override
    protected String getFilterAppLabel() {
        return "Classe";
    }
}



