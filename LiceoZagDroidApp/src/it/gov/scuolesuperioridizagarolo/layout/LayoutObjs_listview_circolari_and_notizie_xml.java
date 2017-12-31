package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_listview_circolari_and_notizie_xml {
    public final TextView title;
    public final ImageView image;
    public final TextView date;
    public final TextView descrizione;

    public LayoutObjs_listview_circolari_and_notizie_xml(Fragment f) {
        View view = f.getView();
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.image);
        date = (TextView) view.findViewById(R.id.date);
        descrizione = (TextView) view.findViewById(R.id.descrizione);
    }

    public LayoutObjs_listview_circolari_and_notizie_xml(Activity view) {
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.image);
        date = (TextView) view.findViewById(R.id.date);
        descrizione = (TextView) view.findViewById(R.id.descrizione);

    }

    public LayoutObjs_listview_circolari_and_notizie_xml(View view) {
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.image);
        date = (TextView) view.findViewById(R.id.date);
        descrizione = (TextView) view.findViewById(R.id.descrizione);

    }

    public LayoutObjs_listview_circolari_and_notizie_xml(Dialog view) {
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.image);
        date = (TextView) view.findViewById(R.id.date);
        descrizione = (TextView) view.findViewById(R.id.descrizione);

    }
}
   
