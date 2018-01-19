package it.gov.scuolesuperioridizagarolo.fragment;

import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

/**
 * Created by stefano on 23/12/2017.
 */
public class OrarioDocentePersistenteFragment extends OrarioDocenteNonPersistenteFragment {
    @Override
    protected void saveFiltrerValue(String filtro) {
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        p.setUltimoDocente(filtro);

    }

    @Override
    protected String getSavedFiltrerValue() {
        final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());
        return p.getUltimoDocente();
    }


}
