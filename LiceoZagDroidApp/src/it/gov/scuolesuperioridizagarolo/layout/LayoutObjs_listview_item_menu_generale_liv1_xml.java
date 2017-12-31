package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_listview_item_menu_generale_liv1_xml {
    public final ImageView icon;
    public final TextView title;
    public final TextView counter;
    public final ImageView imageView3;

    public LayoutObjs_listview_item_menu_generale_liv1_xml(Fragment f) {
        View view = f.getView();
        icon = (ImageView) view.findViewById(R.id.icon);
        title = (TextView) view.findViewById(R.id.title);
        counter = (TextView) view.findViewById(R.id.counter);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
    }

    public LayoutObjs_listview_item_menu_generale_liv1_xml(Activity view) {
        icon = (ImageView) view.findViewById(R.id.icon);
        title = (TextView) view.findViewById(R.id.title);
        counter = (TextView) view.findViewById(R.id.counter);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);

    }

    public LayoutObjs_listview_item_menu_generale_liv1_xml(View view) {
        icon = (ImageView) view.findViewById(R.id.icon);
        title = (TextView) view.findViewById(R.id.title);
        counter = (TextView) view.findViewById(R.id.counter);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);

    }

    public LayoutObjs_listview_item_menu_generale_liv1_xml(Dialog view) {
        icon = (ImageView) view.findViewById(R.id.icon);
        title = (TextView) view.findViewById(R.id.title);
        counter = (TextView) view.findViewById(R.id.counter);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);

    }
}
   
