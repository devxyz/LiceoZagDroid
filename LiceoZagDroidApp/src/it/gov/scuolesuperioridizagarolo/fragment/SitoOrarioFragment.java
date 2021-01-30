package it.gov.scuolesuperioridizagarolo.fragment;

/**
 * Created by stefano on 23/12/2017.
 */

import it.gov.scuolesuperioridizagarolo.R;

@Deprecated

public class SitoOrarioFragment extends AbstractWebpageFragment {


    protected String getURL() {
        return getMainActivity().getString(R.string.url_website_orario);
    }

    protected String getTitile() {
        return "Orario WEB";
    }


}
