package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_fragment_cerca_circolari_by_date_xml {
    public final TextView date;
    public final ListView listView;
    public final ImageButton imageButton3;
    public final ImageButton imageButton_left;
    public final ImageButton imageButton_right;
    public final CheckBox checkBox;
    public final TextView textViewListaVuota;
    public final TextView textView17;

    public LayoutObjs_fragment_cerca_circolari_by_date_xml(Fragment f) {
        View view = f.getView();
        date = (TextView) view.findViewById(R.id.date);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        imageButton_left = (ImageButton) view.findViewById(R.id.imageButton_left);
        imageButton_right = (ImageButton) view.findViewById(R.id.imageButton_right);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView17 = (TextView) view.findViewById(R.id.textView17);
    }

    public LayoutObjs_fragment_cerca_circolari_by_date_xml(Activity view) {
        date = (TextView) view.findViewById(R.id.date);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        imageButton_left = (ImageButton) view.findViewById(R.id.imageButton_left);
        imageButton_right = (ImageButton) view.findViewById(R.id.imageButton_right);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView17 = (TextView) view.findViewById(R.id.textView17);

    }

    public LayoutObjs_fragment_cerca_circolari_by_date_xml(View view) {
        date = (TextView) view.findViewById(R.id.date);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        imageButton_left = (ImageButton) view.findViewById(R.id.imageButton_left);
        imageButton_right = (ImageButton) view.findViewById(R.id.imageButton_right);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView17 = (TextView) view.findViewById(R.id.textView17);

    }

    public LayoutObjs_fragment_cerca_circolari_by_date_xml(Dialog view) {
        date = (TextView) view.findViewById(R.id.date);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        imageButton_left = (ImageButton) view.findViewById(R.id.imageButton_left);
        imageButton_right = (ImageButton) view.findViewById(R.id.imageButton_right);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView17 = (TextView) view.findViewById(R.id.textView17);

    }
}
   
