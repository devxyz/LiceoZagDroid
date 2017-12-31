package it.gov.scuolesuperioridizagarolo.fragment;

/**
 * Created by stefano on 23/12/2017.
 */

import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.fragment.api.AbstractWebpageFragment;

@Deprecated
public class LibriTestoLiceoFragment extends AbstractWebpageFragment {


    protected String getURL() {
        return getMainActivity().getString(R.string.url_libri_testo_liceo);
    }

    protected String getTitile() {
        return "Libri di testo LICEO";
    }


}
