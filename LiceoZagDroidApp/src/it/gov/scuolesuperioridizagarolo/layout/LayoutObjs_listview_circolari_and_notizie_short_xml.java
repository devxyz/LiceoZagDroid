package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_listview_circolari_and_notizie_short_xml {
    public final TextView title;

    public LayoutObjs_listview_circolari_and_notizie_short_xml(Fragment f) {
        View view = f.getView();
        title = (TextView) view.findViewById(R.id.title);
    }

    public LayoutObjs_listview_circolari_and_notizie_short_xml(Activity view) {
        title = (TextView) view.findViewById(R.id.title);

    }

    public LayoutObjs_listview_circolari_and_notizie_short_xml(View view) {
        title = (TextView) view.findViewById(R.id.title);

    }

    public LayoutObjs_listview_circolari_and_notizie_short_xml(Dialog view) {
        title = (TextView) view.findViewById(R.id.title);

    }
}
   
