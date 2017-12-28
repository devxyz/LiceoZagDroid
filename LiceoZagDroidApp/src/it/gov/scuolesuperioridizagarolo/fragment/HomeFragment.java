package it.gov.scuolesuperioridizagarolo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.MenuHomeListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.dao.*;
import it.gov.scuolesuperioridizagarolo.db.ManagerCircolare;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_home_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

public class HomeFragment extends AbstractFragment {

    private LayoutObjs_fragment_home_xml LAYOUT_OBJs;   //***************************
    private int numNotizieNonLette = 0;
    private int numCircolariNonLette = 0;
    private int numCircolariInEvidenzaOggi = 0;

    public HomeFragment() {
    }

    @Override
    protected Integer getHelpScreen() {
        return R.drawable.help_home_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        ScuolaAppDbHelper f = new ScuolaAppDbHelper(getMainActivity());
        try {
            f.runInTransaction(new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {
                    QueryBuilder<NewsDB> q1 = session.getNewsDBDao().queryBuilder().where(NewsDBDao.Properties.FlagContenutoLetto.eq(false));
                    QueryBuilder<CircolareDB> q2 = session.getCircolareDBDao().queryBuilder().where(CircolareDBDao.Properties.FlagContenutoLetto.eq(false));
                    ManagerCircolare c = new ManagerCircolare(session);
                    final List<CircolareDB> circolariDiOggi = c.circolariByDate(new Date());

                    numNotizieNonLette = q1.list().size();
                    numCircolariNonLette = q2.list().size();
                    numCircolariInEvidenzaOggi = circolariDiOggi.size();
                }
            });
        } catch (Throwable e) {

        } finally {
            f.close();
        }


        LAYOUT_OBJs.textViewTipoUtente.setText(getMainActivity().getSharedPreferences().getUserType().getDescrizione());


        final MenuHomeListAdapter adapter = new MenuHomeListAdapter(getMainActivity(), numCircolariNonLette, numNotizieNonLette, numCircolariInEvidenzaOggi);
        LAYOUT_OBJs.listView4.setAdapter(adapter);

        LAYOUT_OBJs.listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DataMenuInfo dataMenuInfo = adapter.getDataMenuInfo(position);
                getMainActivity().doAction(dataMenuInfo);
                adapter.update();

            }
        });




        //Ist.+Tec.+Stat.+E.+Fermi/@41.956178,12.806626
        return rootView;
    }


}
