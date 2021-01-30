package it.gov.scuolesuperioridizagarolo.fragment;

/**
 * Created by stefano on 23/12/2017.
 */

import it.gov.scuolesuperioridizagarolo.R;

@Deprecated
public class SitoWebFragment extends AbstractWebpageFragment {


    protected String getURL() {
        return getMainActivity().getString(R.string.url_website_scuola);
    }

    protected String getTitile() {
        return "Sito WEB";
    }


}
