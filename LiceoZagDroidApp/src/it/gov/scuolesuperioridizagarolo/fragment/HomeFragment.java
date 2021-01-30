package it.gov.scuolesuperioridizagarolo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.MenuHomeListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.db.BitOrrioGrigliaOrarioContainerSingleton;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_home_xml;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;

import java.util.Date;

public class HomeFragment extends AbstractFragment {

    public LayoutObjs_fragment_home_xml LAYOUT_OBJs;   //***************************

    public HomeFragment() {
    }

    @Override
    public void showDetails(boolean show) {
        if (show) {
            LAYOUT_OBJs.textViewTipoUtente.setVisibility(View.VISIBLE);

        } else {
            LAYOUT_OBJs.textViewTipoUtente.setVisibility(View.GONE);
        }
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
        Date lastDataUpdate = getMainActivity().getSharedPreferences().getLastDataUpdate();
        String lastUpdate = C_DateUtil.toDDMMYYY_HHMMSS(lastDataUpdate);
        LAYOUT_OBJs.textViewTipoUtente.setText(getMainActivity().getSharedPreferences().getUserType().getDescrizione() + "\n  Aggiornamento:" + lastUpdate);


        //Bitmap b = ScreenUtil.getResourceAsBitmap(this.getMainActivity(), R.drawable.logo_fermi_150x150);
        //LAYOUT_OBJs.imageView.setImageBitmap(ScreenUtil.scaleAndAdapt(b, 300, 300));

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

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                BitOrrioGrigliaOrarioContainerSingleton.getInstance(getMainActivity());
            }
        });
        t.start();

        //Ist.+Tec.+Stat.+E.+Fermi/@41.956178,12.806626
        return rootView;
    }


}
