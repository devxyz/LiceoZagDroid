package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_dialog_view_html_xml {
    public final TextView textViewTitle;
    public final WebView textViewDescrizione;
    public final ImageButton buttonClose;
    public final LinearLayout linearLayout2;
    public final Button buttonOK;
    public final ProgressBar progressBar7;

    public LayoutObjs_dialog_view_html_xml(Fragment f) {
        View view = f.getView();
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (WebView) view.findViewById(R.id.textViewDescrizione);
        buttonClose = (ImageButton) view.findViewById(R.id.buttonClose);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        buttonOK = (Button) view.findViewById(R.id.buttonOK);
        progressBar7 = (ProgressBar) view.findViewById(R.id.progressBar7);
    }

    public LayoutObjs_dialog_view_html_xml(Activity view) {
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (WebView) view.findViewById(R.id.textViewDescrizione);
        buttonClose = (ImageButton) view.findViewById(R.id.buttonClose);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        buttonOK = (Button) view.findViewById(R.id.buttonOK);
        progressBar7 = (ProgressBar) view.findViewById(R.id.progressBar7);

    }

    public LayoutObjs_dialog_view_html_xml(View view) {
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (WebView) view.findViewById(R.id.textViewDescrizione);
        buttonClose = (ImageButton) view.findViewById(R.id.buttonClose);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        buttonOK = (Button) view.findViewById(R.id.buttonOK);
        progressBar7 = (ProgressBar) view.findViewById(R.id.progressBar7);

    }

    public LayoutObjs_dialog_view_html_xml(Dialog view) {
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDescrizione = (WebView) view.findViewById(R.id.textViewDescrizione);
        buttonClose = (ImageButton) view.findViewById(R.id.buttonClose);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        buttonOK = (Button) view.findViewById(R.id.buttonOK);
        progressBar7 = (ProgressBar) view.findViewById(R.id.progressBar7);

    }
}
   
