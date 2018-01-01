package it.gov.scuolesuperioridizagarolo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_barcode_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;

/**
 * Created by stefano on 01/01/2018.
 */
public class BarCodeFragment extends AbstractFragment {
    private LayoutObjs_fragment_barcode_xml LAYOUT_OBJs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_barcode, container, false);

        LAYOUT_OBJs = new LayoutObjs_fragment_barcode_xml(rootView);

        LAYOUT_OBJs.button2.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {

            }
        });


        return rootView;
    }

}
