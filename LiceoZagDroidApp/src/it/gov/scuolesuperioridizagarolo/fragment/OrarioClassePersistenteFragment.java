package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

public class OrarioClassePersistenteFragment extends OrarioClasseNonPersistenteFragment {

    @Override
    protected void saveFiltrerValue(String filtro) {
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        //p.setUltimaClasse(filtro);
    }

    @Override
    protected String getSavedFiltrerValue() {
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        return null ;//p.getUltimaClasse();
    }
}



