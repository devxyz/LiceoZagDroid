package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

public class OrarioClassePersistenteFragment extends OrarioClasseNonPersistenteFragment {

    @Override
    protected void persistFiltrerValue(ClassData filtro) {
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        p.setUltimaClasse(filtro);
    }

    @Override
    protected ClassData retrievePersistedFiltrerValue() {
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        return p.getUltimaClasse();
    }
}



