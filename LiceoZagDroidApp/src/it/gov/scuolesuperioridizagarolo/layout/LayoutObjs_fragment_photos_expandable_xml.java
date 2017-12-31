package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ExpandableListView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_fragment_photos_expandable_xml {
    public final ExpandableListView listView2;

    public LayoutObjs_fragment_photos_expandable_xml(Fragment f) {
        View view = f.getView();
        listView2 = (ExpandableListView) view.findViewById(R.id.listView2);
    }

    public LayoutObjs_fragment_photos_expandable_xml(Activity view) {
        listView2 = (ExpandableListView) view.findViewById(R.id.listView2);

    }

    public LayoutObjs_fragment_photos_expandable_xml(View view) {
        listView2 = (ExpandableListView) view.findViewById(R.id.listView2);

    }

    public LayoutObjs_fragment_photos_expandable_xml(Dialog view) {
        listView2 = (ExpandableListView) view.findViewById(R.id.listView2);

    }
}
   
