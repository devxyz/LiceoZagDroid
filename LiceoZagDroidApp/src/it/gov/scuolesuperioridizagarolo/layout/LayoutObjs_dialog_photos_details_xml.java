package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_dialog_photos_details_xml {
    public final TextView textViewTitle;
    public final TextView textViewDescrizione;
    public final ScrollView scrollView;
    public final ImageView image;
    public final ImageButton buttonChiud;

    public LayoutObjs_dialog_photos_details_xml(Fragment f) {
        View view = f.getView();
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (TextView) view.findViewById(R.id.textViewDescrizione);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        image = (ImageView) view.findViewById(R.id.image);
        buttonChiud = (ImageButton) view.findViewById(R.id.buttonChiud);
    }

    public LayoutObjs_dialog_photos_details_xml(Activity view) {
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (TextView) view.findViewById(R.id.textViewDescrizione);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        image = (ImageView) view.findViewById(R.id.image);
        buttonChiud = (ImageButton) view.findViewById(R.id.buttonChiud);

    }

    public LayoutObjs_dialog_photos_details_xml(View view) {
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (TextView) view.findViewById(R.id.textViewDescrizione);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        image = (ImageView) view.findViewById(R.id.image);
        buttonChiud = (ImageButton) view.findViewById(R.id.buttonChiud);

    }

    public LayoutObjs_dialog_photos_details_xml(Dialog view) {
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (TextView) view.findViewById(R.id.textViewDescrizione);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        image = (ImageView) view.findViewById(R.id.image);
        buttonChiud = (ImageButton) view.findViewById(R.id.buttonChiud);

    }
}
   
