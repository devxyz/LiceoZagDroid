package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_activity_splash_update2_xml {
    public final TextView textViewOra;
    public final TextView txtInfo;
    public final TextView textViewMsgUpdate;
    public final TextView textView9;
    public final TextView textView10;
    public final ListView listViewCircolariDelGiorno;
    public final ProgressBar progressBar2;
    public final TextView txtNuoveCircolari;

    public LayoutObjs_activity_splash_update2_xml(Fragment f) {
        View view = f.getView();
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        txtInfo = (TextView) view.findViewById(R.id.txtInfo);
        textViewMsgUpdate = (TextView) view.findViewById(R.id.textViewMsgUpdate);
        textView9 = (TextView) view.findViewById(R.id.textView9);
        textView10 = (TextView) view.findViewById(R.id.textView10);
        listViewCircolariDelGiorno = (ListView) view.findViewById(R.id.listViewCircolariDelGiorno);
        progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
        txtNuoveCircolari = (TextView) view.findViewById(R.id.txtNuoveCircolari);
    }

    public LayoutObjs_activity_splash_update2_xml(Activity view) {
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        txtInfo = (TextView) view.findViewById(R.id.txtInfo);
        textViewMsgUpdate = (TextView) view.findViewById(R.id.textViewMsgUpdate);
        textView9 = (TextView) view.findViewById(R.id.textView9);
        textView10 = (TextView) view.findViewById(R.id.textView10);
        listViewCircolariDelGiorno = (ListView) view.findViewById(R.id.listViewCircolariDelGiorno);
        progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
        txtNuoveCircolari = (TextView) view.findViewById(R.id.txtNuoveCircolari);

    }

    public LayoutObjs_activity_splash_update2_xml(View view) {
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        txtInfo = (TextView) view.findViewById(R.id.txtInfo);
        textViewMsgUpdate = (TextView) view.findViewById(R.id.textViewMsgUpdate);
        textView9 = (TextView) view.findViewById(R.id.textView9);
        textView10 = (TextView) view.findViewById(R.id.textView10);
        listViewCircolariDelGiorno = (ListView) view.findViewById(R.id.listViewCircolariDelGiorno);
        progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
        txtNuoveCircolari = (TextView) view.findViewById(R.id.txtNuoveCircolari);

    }

    public LayoutObjs_activity_splash_update2_xml(Dialog view) {
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        txtInfo = (TextView) view.findViewById(R.id.txtInfo);
        textViewMsgUpdate = (TextView) view.findViewById(R.id.textViewMsgUpdate);
        textView9 = (TextView) view.findViewById(R.id.textView9);
        textView10 = (TextView) view.findViewById(R.id.textView10);
        listViewCircolariDelGiorno = (ListView) view.findViewById(R.id.listViewCircolariDelGiorno);
        progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
        txtNuoveCircolari = (TextView) view.findViewById(R.id.txtNuoveCircolari);

    }
}
   
