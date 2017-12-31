package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_listview_photolist_group_xml {
    public final ImageView thumb;
    public final TextView title;

    public LayoutObjs_listview_photolist_group_xml(Fragment f) {
        View view = f.getView();
        thumb = (ImageView) view.findViewById(R.id.thumb);
        title = (TextView) view.findViewById(R.id.title);
    }

    public LayoutObjs_listview_photolist_group_xml(Activity view) {
        thumb = (ImageView) view.findViewById(R.id.thumb);
        title = (TextView) view.findViewById(R.id.title);

    }

    public LayoutObjs_listview_photolist_group_xml(View view) {
        thumb = (ImageView) view.findViewById(R.id.thumb);
        title = (TextView) view.findViewById(R.id.title);

    }

    public LayoutObjs_listview_photolist_group_xml(Dialog view) {
        thumb = (ImageView) view.findViewById(R.id.thumb);
        title = (TextView) view.findViewById(R.id.title);

    }
}
   
