package it.gov.scuolesuperioridizagarolo.fragment;

import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.MenuHomeListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_home_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

public class HomeFragment extends AbstractFragment {

    private LayoutObjs_fragment_home_xml LAYOUT_OBJs;   //***************************

    public HomeFragment() {
    }

    @Override
    protected Integer getHelpScreen() {
        return R.drawable.help_home_fragment;
    }

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_home_xml(rootView);
        //**************************
        //**************************

        //Bitmap b = ScreenUtil.getResourceAsBitmap(this.getMainActivity(), R.drawable.logo_fermi_150x150);
        //LAYOUT_OBJs.imageView.setImageBitmap(ScreenUtil.scaleAndAdapt(b, 300, 300));

        LAYOUT_OBJs.imageView.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            public void onClickImpl(View v) {

                getMainActivity().openMenu();

            }
        });

        LAYOUT_OBJs.textViewTipoUtente.setText(getMainActivity().getSharedPreferences().getUserType().getDescrizione());
        final MenuHomeListAdapter adapter = new MenuHomeListAdapter(getMainActivity());
        LAYOUT_OBJs.listView4.setAdapter(adapter);

        LAYOUT_OBJs.listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DataMenuInfo dataMenuInfo = adapter.getDataMenuInfo(position);
                getMainActivity().doAction(dataMenuInfo, null);
                adapter.update();

            }
        });


        //Ist.+Tec.+Stat.+E.+Fermi/@41.956178,12.806626
        return rootView;
    }



}
