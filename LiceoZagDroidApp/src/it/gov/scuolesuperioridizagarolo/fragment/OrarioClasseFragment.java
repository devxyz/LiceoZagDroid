package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.adapter.OrarioClasseListAdapter;
import it.gov.scuolesuperioridizagarolo.fragment.api.AbstractOrarioFragment;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

import java.util.TreeSet;

public class OrarioClasseFragment extends AbstractOrarioFragment<OrarioClasseListAdapter> {

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
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        p.setUltimaClasse(filtro);
    }

    @Override
    protected String getSavedFiltrerValue() {
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        return p.getUltimaClasse();
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



