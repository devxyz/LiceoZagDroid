package it.gov.scuolesuperioridizagarolo.fragment;

/**
 * Created by stefano on 23/12/2017.
 */

import it.gov.scuolesuperioridizagarolo.R;

@Deprecated
public class LibriTestoIpiaFragment extends AbstractWebpageFragment {


    protected String getURL() {
        return getMainActivity().getString(R.string.url_libri_testo_ipia);
    }

    protected String getTitile() {
        return "Libri di testo LICEO";
    }


}
