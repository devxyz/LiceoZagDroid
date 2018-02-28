package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.adapter.OrarioAulaListAdapter;

import java.util.TreeSet;

public class OrarioAulaNonPersistenteFragment extends AbstractOrarioFragment<OrarioAulaListAdapter> {

    @Override
    protected OrarioAulaListAdapter createAbstractOrarioListAdapter() {
        return new OrarioAulaListAdapter(getMainActivity(), filtro, containerOrari, giornoCorrente);
    }

    @Override
    protected String[] getFilterValues() {
        final TreeSet<String> aule = orario.getAule();
        return aule.toArray(new String[aule.size()]);
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
        return false;
    }

    @Override
    protected boolean isTimetableForRooms() {
        return true;
    }

    @Override
    protected boolean normalizeFilterName() {
        return false;
    }

    @Override
    protected void updateAdapter(String filter) {
        super.orarioAdapter.setAula(filter);
    }

    @Override
    protected String getFilterDialogLabel() {
        return "Aula";
    }

    @Override
    protected String getFilterAppLabel() {
        return "";
    }
}



